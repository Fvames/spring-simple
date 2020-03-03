package dev.fvames.thinking.in.spring.bean.instantiation;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("MATE-INF/bean-instantiation-context.xml");

        User user = applicationContext.getBean("user-by-static-method", User.class);
        User userByFactoryBean = applicationContext.getBean("user-by-factory-bean", User.class);
        User userByInstanceMethod = applicationContext.getBean("user-by-instance-method", User.class);

        System.out.println("user: "+ user);
        System.out.println("userByFactoryBean: " + userByFactoryBean);
        System.out.println("userByInstanceMethod: " + userByInstanceMethod);

        System.out.println(user == userByFactoryBean);
        System.out.println(user == userByInstanceMethod);
    }
}
