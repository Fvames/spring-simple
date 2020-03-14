package dev.fvames.thinking.in.spring.ioc.dependency.injection;

import dev.fvames.thinking.in.spring.ioc.dependency.injection.domain.UserHolder;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 Java 注解 setter 和 构造方法 的依赖注入
 */
public class AnnotationDependencySetterAndConstructorInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencySetterAndConstructorInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        // 依赖查找并且创建 Bean
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }

    /**
     *
     * @param user 根据类型注入，多个时以 primary 为主
     * @return
     */
    @Bean
    public UserHolder userHolder(User user) {
        //UserHolder userHolder = new UserHolder();
        // setter 注入
        //userHolder.setUser(user);

        // 构造器注入
        UserHolder userHolder = new UserHolder(user);
        return userHolder;
    }
}
