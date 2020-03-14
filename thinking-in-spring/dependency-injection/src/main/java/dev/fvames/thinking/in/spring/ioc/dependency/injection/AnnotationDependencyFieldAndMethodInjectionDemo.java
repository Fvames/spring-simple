package dev.fvames.thinking.in.spring.ioc.dependency.injection;

import dev.fvames.thinking.in.spring.ioc.dependency.injection.domain.UserHolder;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于 Java 注解 字段 和 方法 的依赖注入
 */
public class AnnotationDependencyFieldAndMethodInjectionDemo {

    //@Autowired
    private UserHolder userHolder;
    //@Resource
    private UserHolder userHolder2;

    @Autowired
    public void init1(UserHolder userHolder) {
        this.userHolder = userHolder;
    }
    @Resource
    public void init2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldAndMethodInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        // 依赖查找 bean
        AnnotationDependencyFieldAndMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldAndMethodInjectionDemo.class);

        UserHolder userHolder = demo.userHolder;
        UserHolder userHolder2 = demo.userHolder2;
        System.out.println(userHolder);
        System.out.println(userHolder == userHolder2);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
