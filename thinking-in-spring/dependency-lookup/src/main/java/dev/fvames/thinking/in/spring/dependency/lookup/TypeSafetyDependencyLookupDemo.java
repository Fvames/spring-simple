package dev.fvames.thinking.in.spring.dependency.lookup;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全依赖查找
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        applicationContext.refresh();

        // 非安全： BeanFactory#getBean
        displayBeanFactoryGetBean(applicationContext);
        // 非安全： ObjectFactory#getObject
        displayObjectFactoryGetObject(applicationContext);

        // 安全
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        displayObjectProviderIfAvailable(applicationContext);
        displayObjectProviderStreamOps(applicationContext);

        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () -> userObjectProvider.forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayListableBeanFactoryGetBeansOfType", () -> applicationContext.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", () -> userObjectProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", () -> userObjectFactory.getObject());
    }

    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayBeanFactoryGetBean", () -> applicationContext.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("========================");
        System.out.println("Source from: " + source);
        try {

            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }
}
