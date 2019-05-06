package dev.fames.mvc.v1;

import dev.fames.mvc.annotation.Autowired;
import dev.fames.mvc.annotation.Controller;
import dev.fames.mvc.annotation.RequestMapping;
import dev.fames.mvc.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class DispatcherServlet extends HttpServlet {

    private static final String CONTEXT_CONFIG = "contextConfig";
    public static final String SCAN_PACKAGE = "scanPackage";
    private static Map<String, Object> IOC = new ConcurrentHashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        Properties properties = new Properties();

        InputStream inputStream = null;

        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter(CONTEXT_CONFIG));
            properties.load(inputStream);
            String scanPackage = properties.getProperty(SCAN_PACKAGE);
            System.out.println("scanPackage: " + scanPackage);
            // 加载
            doScan(scanPackage);
            // 实例化
            doInstance();
            // 依赖注入配置
            doDI();

            System.out.println("------------ start success --------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doDI() {
        for (Object value : IOC.values()) {
            if (null == value) {
                continue;
            }

            Class clazz = value.getClass();
            if (clazz.isAnnotationPresent(Controller.class)) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (!field.isAnnotationPresent(Autowired.class)) {
                        continue;
                    }
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String beanName = autowired.value();
                    if ("".equals(beanName)) {
                        beanName = field.getType().getName();
                    }
                    field.setAccessible(true);
                    try {
                        field.set(IOC.get(clazz.getName()), IOC.get(beanName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void doInstance() throws Exception {
        for (String className : IOC.keySet()) {
            if (!className.contains(".")) {
                continue;
            }

            Class<?> clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(Controller.class)) {
                IOC.put(className, clazz.newInstance());

                String baseUrl = "";
                if (clazz.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    // 优化：无requestMapping 的 pulic 方法默认以方法名为导航路径
                    if (!method.isAnnotationPresent(RequestMapping.class)) {
                        continue;
                    }
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    String path = (baseUrl + requestMapping.value().replaceAll("/+", "/"));
                    IOC.put(path, method);

                    System.out.println("Mapped url[" + path + "], method: " + method);
                }
            }

            if (clazz.isAnnotationPresent(Service.class)) {

                Service service = clazz.getAnnotation(Service.class);
                String beanName = service.value();
                if ("".equals(beanName)) {
                    beanName = clazz.getName();
                }

                IOC.put(beanName, clazz.newInstance());

            }

        }
    }

    private void doScan(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File file = new File(url.getFile());
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()) {
                doScan(scanPackage + "." + listFile.getName());
            } else {
                if (!listFile.getName().endsWith(".class")) {
                    continue;
                }
                String clazzName = scanPackage + "." + listFile.getName().replace(".class", "");
                IOC.put(clazzName, "");
                System.out.println("scan class name: " + clazzName);
            }
        }
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uri = req.getRequestURI();
        String contextPaht = req.getContextPath();
        String url = uri.replace(contextPaht, "").replace("/+", "/");
        if (!IOC.containsKey(url)) {
            resp.getWriter().write("404");
            return;
        }

        Method method = (Method) IOC.get(url);
        Map<String, String[]> params = req.getParameterMap();
        method.invoke(IOC.get(method.getDeclaringClass().getName()), new Object[]{req, resp, params.get("name")[0]});
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            doDispatcher(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
