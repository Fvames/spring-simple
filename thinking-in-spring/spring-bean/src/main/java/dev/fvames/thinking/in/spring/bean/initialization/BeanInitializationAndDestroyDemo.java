package dev.fvames.thinking.in.spring.bean.initialization;

import dev.fvames.thinking.in.spring.bean.factory.DefaultUserFactory;
import dev.fvames.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化
 */
@Configuration
public class BeanInitializationAndDestroyDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationAndDestroyDemo.class);

        // 刷新应用上下文
        applicationContext.refresh();
        // 延迟初始化时先打印，然后打印初始化
        System.out.println("Spring 应用上下文已启动 \n");

        // 依赖查找 UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory + "\n");

        System.out.println("Spring 应用上下文准备关闭 \n");
        // 关闭应用上下文
        applicationContext.close();
        System.out.println("Spring 应用上下文已关闭");
    }

    @Lazy(value = false)
    @Bean(initMethod = "initUserFactory", destroyMethod = "destroyUserFactory")
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }
}
