package dev.fvames.thinking.in.spring.bean.instantiation;

import dev.fvames.thinking.in.spring.bean.factory.DefaultUserFactory;
import dev.fvames.thinking.in.spring.bean.factory.UserFactory;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊的 Bean 实例化
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/special-bean-instantiation-context.xml");
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        //
        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);

        displayServiceLoader(serviceLoader);

        DefaultUserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());

        //demoServiceLoader();
    }

    private static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            User user = userFactory.createUser();
            System.out.println(user.toString());
        }
    }
}
