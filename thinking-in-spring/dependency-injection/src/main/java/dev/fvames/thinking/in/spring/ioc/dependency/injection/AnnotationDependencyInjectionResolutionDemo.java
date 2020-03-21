package dev.fvames.thinking.in.spring.ioc.dependency.injection;

import dev.fvames.thinking.in.spring.ioc.dependency.injection.annotation.InjectedUser;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;

/**
 * 注解驱动的依赖注入处理过程
 * 1.单一 bean 注入，{@link Lazy} 注解处理
 * 2.集合类型注入 map、collection
 * 3.optional
 * 4.{@link Inject}
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    private User user;

    /*@Autowired
    @Lazy
    private User lazyUser;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private Optional<User> optionalUser;*/

    /*@Bean(name = AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // @Autowired + @Inject +  新注解 @InjectedUser
        Set<Class<? extends Annotation>> autowiredAnnotationTypes =
                new LinkedHashSet<>(asList(Autowired.class, Inject.class, InjectedUser.class));
        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
        return beanPostProcessor;
    }*/

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    @Scope
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "META-INF/dependency-lookup-context.xml";
        definitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        // 依赖注入
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println("demo.user: " + demo.user);
        //System.out.println("demo.lazyUser: " + demo.lazyUser);
        //System.out.println("demo.users: " + demo.users);
        //System.out.println("demo.optionalUser: " + demo.optionalUser);

        applicationContext.close();
    }

}
