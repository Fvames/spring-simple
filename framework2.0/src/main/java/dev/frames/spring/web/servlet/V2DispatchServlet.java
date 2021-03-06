package dev.frames.spring.web.servlet;

import com.sun.istack.internal.Nullable;
import dev.frames.spring.annotation.Controller;
import dev.frames.spring.annotation.RequestMapping;
import dev.frames.spring.context.V2ApplicationContext;
import dev.frames.spring.web.V2View;
import dev.frames.spring.web.V2ViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 2019/5/9 15:15
 */
public class V2DispatchServlet extends HttpServlet {

    public static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private List<V2HandlerMapping> handlerMappings = new ArrayList<>();

    /** List of HandlerAdapters used by this servlet. */
    @Nullable
    private Map<V2HandlerMapping, V2HandlerAdapter> handlerAdapters = new HashMap<>();

    private List<V2ViewResolver> viewResolvers = new ArrayList<V2ViewResolver>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        V2ApplicationContext applicationContext = new V2ApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));

        initStrategies(applicationContext);

        System.out.println(">>>>>>> init mvc");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        V2HandlerMapping handlerMapping = getHandlerMapping(req);
        if (null == handlerMapping) {
            processDispatchResult(req, resp, new V2ModelAndView("/404"));
        }

        // 准备调用方法的参数
        V2HandlerAdapter handlerAdapter = getHandleAdapter(handlerMapping);
        V2ModelAndView mv = handlerAdapter.handle(req, resp, handlerMapping);

        // 输出
        processDispatchResult(req, resp, mv);
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, V2ModelAndView mv) throws IOException {
        if (null == mv || this.viewResolvers.isEmpty()) {
            return;
        }

        for (V2ViewResolver viewResolver : this.viewResolvers) {
            V2View v2View = viewResolver.resolveViewName(mv.getView());
            if (null != v2View) {

                v2View.render(mv.getModel(), req, resp);
                return;
            }
        }
    }

    private V2HandlerAdapter getHandleAdapter(V2HandlerMapping handlerMapping) {
        return handlerAdapters.get(handlerMapping);
    }

    private V2HandlerMapping getHandlerMapping(HttpServletRequest req) {

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String url = uri.replace(contextPath, "");

        for (V2HandlerMapping handlerMapping : handlerMappings) {
            Matcher matcher = handlerMapping.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handlerMapping;
        }

        return null;
    }

    protected void initStrategies(V2ApplicationContext context) {
        //initMultipartResolver(context);
        //initLocaleResolver(context);
        //initThemeResolver(context);
        initHandlerMappings(context);
        initHandlerAdapters(context);
        //initHandlerExceptionResolvers(context);
        //initRequestToViewNameTranslator(context);
        initViewResolvers(context);
        //initFlashMapManager(context);
    }

    private void initViewResolvers(V2ApplicationContext context) {
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        for (File file : new File(templateRootPath).listFiles()) {
            viewResolvers.add(new V2ViewResolver(new File(templateRootPath)));
        }
    }

    private void initHandlerMappings(V2ApplicationContext context) {
        List<String> beanDefinitionNames = context.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object controller = context.getBean(beanDefinitionName);
            Class<?> clazz = controller.getClass();

            if (!clazz.isAnnotationPresent(Controller.class)) {
                continue;
            }

            String baseUrl = "";
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                baseUrl = requestMapping.value();
            }

            // 获取 method 配置
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }

                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                String methodUrl = requestMapping.value();

                StringBuilder url = new StringBuilder("/");
                if (!"".equals(baseUrl)) {
                    url.append(baseUrl);
                }

                url.append("/" + methodUrl.replaceAll("\\*", ".*"));
                String regex = url.toString().replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);

                handlerMappings.add(new V2HandlerMapping(controller, method, pattern));

                System.out.println(">>>>>> init mapping " + regex + ", method: " + method);
            }
        }
    }

    private void initHandlerAdapters(V2ApplicationContext context) {
        for (V2HandlerMapping handlerMapping : handlerMappings) {
            handlerAdapters.put(handlerMapping, new V2HandlerAdapter());
        }
    }

}
