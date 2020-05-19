package dev.fvames.thinking.in.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 注入 {@link ResourceLoader} 实现
 *
 *
 */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {
    // 方式 1：注解 Autowired
    @Autowired
    private ResourceLoader annotationResourceLoader;

    // 方式 2：实现 ResourceLoaderAware 接口
    private ResourceLoader resourceLoader;
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    // 方式 3：注入 ApplicationContext
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        System.out.println("resourceLoader == annotationResourceLoader: " + (resourceLoader == annotationResourceLoader));
        System.out.println("resourceLoader == applicationContext: " + (resourceLoader == applicationContext));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingResourceLoaderDemo.class);

        context.refresh();
        context.close();
    }
}
