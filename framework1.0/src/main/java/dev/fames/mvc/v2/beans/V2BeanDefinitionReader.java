package dev.fames.mvc.v2.beans;

import dev.fames.mvc.v2.beans.config.V2BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

// 对配置文件进行查找，读取，解析
public class V2BeanDefinitionReader {

    private List<String> registyBeanClasses = new ArrayList<>();
    private Properties config = new Properties();

    // 固定配置文件中的 key
    private final String SCAN_PACKAGE = "scanPackage";

    public V2BeanDefinitionReader(String[] configLocations) {
        InputStream inputStream = getClassLoader().getResourceAsStream(
                configLocations[0].replace("classpath*:", "")
                        .replace("classpath:", ""));

        try {
            config.load(inputStream);
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

        doScanner(config.getProperty(SCAN_PACKAGE));

    }

    private void doScanner(String scanPackage) {
        URL url = getClassLoader().getResource(File.separator + scanPackage.replaceAll("\\.", File.separator));
        File classPath = new File(url.getFile());

        for (File file : classPath.listFiles()) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + fileName);
            } else {
                if (!fileName.endsWith(".class")) {
                    continue;
                }
                String className = (scanPackage + "." + fileName.replace(".class", ""));
                registyBeanClasses.add(className);
            }
        }
    }

    private ClassLoader getClassLoader() {
        return this.getClass().getClassLoader();
    }

    // 把配置文件中扫描到所有的配置转换为 BeanDefinition 对象
    public List<V2BeanDefinition> loadBeanDefinitions() {
        List<V2BeanDefinition> result = new ArrayList<>();

        try {
            for (String className : registyBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) {
                    continue;
                }

                String simpleName = toLowerFirstCase(beanClass.getSimpleName());
                V2BeanDefinition beanDefinition = doCreateBeanDefinition(simpleName, beanClass.getName());
                result.add(beanDefinition);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    private V2BeanDefinition doCreateBeanDefinition(String simpleName, String beanClassName) {
        V2BeanDefinition beanDefinition = new V2BeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(simpleName);
        return beanDefinition;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public Properties getConfig() {
        return this.config;
    }
}
