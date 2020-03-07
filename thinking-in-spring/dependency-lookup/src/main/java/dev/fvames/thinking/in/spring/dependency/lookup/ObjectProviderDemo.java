package dev.fvames.thinking.in.spring.dependency.lookup;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 {@link ObjectProvider} 进行依赖查找
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        String helloWorld = applicationContext.getBean(String.class);
        System.out.println(helloWorld);
        // 安全查找
        lookupIfAvailable(applicationContext);

        // NoUniqueBeanDefinitionException
        lookupByObjectProvider(applicationContext);
        // 集合查找
        lookupByStreamOps(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        //Iterable<String> iterable = objectProvider;
        //for (String s : iterable) {
        //    System.out.println(s);
        //}

        // stream -> method reference
        objectProvider.forEach(System.out::println);

    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println("objectProvider.getObject() : " + objectProvider.getObject());
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        //User user = userObjectProvider.getIfAvailable(() -> User.createUser());
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前 User 对象： " + user);
    }

    @Bean
    @Primary
    public String helloWorld() {
        return "Hello, world";
    }

    @Bean
    public String message() {
        return "Message";
    }
}
