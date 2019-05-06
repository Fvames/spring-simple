package dev.frames.spring.beans.factory;

import dev.frames.spring.beans.config.V2BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

// 读取配置文件，扫描类文件保存 className
public class V2BeanDefinitionReader {
    private List<String> regiterBeanClass = new ArrayList<String>();
    private Properties properties = new Properties();

    public static final String SCAN_PACKAGE = "scanPackage";

    public V2BeanDefinitionReader(String configLocation) throws Exception {

        String path = configLocation.replace("classpath*:", "").replace("classpath:", "");
        InputStream inputStream = getClassLoader().getResourceAsStream(path);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        doScan(properties.getProperty(SCAN_PACKAGE));

    }

    private void doScan(String scanPackage) {
        // todo .getResource("/") 从根路径下查找，此处加了 "/" 会找不到，不加才是从 target/class 目录下查找
        String path = scanPackage.replaceAll("\\.", File.separator);
        File file = new File(getClassLoader().getResource(path).getFile());
        for (File f : file.listFiles()) {
            String fileName = f.getName();
            if (f.isDirectory()) {
                doScan(scanPackage + "." + fileName);
            } else {
                if (!fileName.endsWith(".class")) {
                    continue;
                }
                regiterBeanClass.add(path + "." + fileName.replace(".class", ""));
            }
        }

    }

    public List<V2BeanDefinition> loaderBeanDefinitions() {
        List<V2BeanDefinition> beanDefinitions = new ArrayList<>();

        // 遍历 class 集合
        try {
            for (String beanClass : regiterBeanClass) {
                Class<?> clazz = Class.forName(beanClass);

                V2BeanDefinition beanDefinition = new V2BeanDefinition();
                beanDefinition.setBeanClassName(clazz.getName());
                beanDefinition.setFactoryBeanName(toLowerFirstCase(clazz.getSimpleName()));

                beanDefinitions.add(beanDefinition);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 实例化 beanDefinition 并返回
        return beanDefinitions;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private ClassLoader getClassLoader() {
        return this.getClass().getClassLoader();
    }
}
