package dev.fvames.thinking.in.spring.ioc.dependency.injection;

import dev.fvames.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link Qualifier} 注解依赖注入
 * 1.根据名称过滤注入
 * 2.分组
 */
public class QualifierAnnotationDependencyInjectDemo {

    @Autowired
    private User user;
    @Autowired
    @Qualifier(value = "user")
    private User nameUser;

    @Autowired
    private Collection<User> allUsers; // 2 Beans = user + superUser

    @Autowired
    @Qualifier  // USER5 + USER6
    private Collection<User> qualifiedUsers;

    @Autowired
    @UserGroup // user7 + user8
    private Collection<User> groupUsers;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectDemo.class);

        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "META-INF/dependency-lookup-context.xml";
        definitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectDemo.class);
        // superUser
        System.out.println("demo.user: " + demo.user + "\n");
        // user
        System.out.println("demo.nameUser: " + demo.nameUser+ "\n");
        // user + superUser,受 xml 配置文件影响
        System.out.println("demo.allUsers: "+demo.allUsers+ "\n");
        // user5 + user6
        System.out.println("demo.qualifiedUsers: "+demo.qualifiedUsers+ "\n");
        // user7 + user8
        System.out.println("demo.groupUsers: "+demo.groupUsers+ "\n");

        System.out.println(applicationContext.getBeansOfType(User.class).size());


        applicationContext.close();
    }

    @Bean
    @Qualifier
    public User user5() {
        return createUser("5");
    }
    @Bean
    @Qualifier
    public User user6() {
        return createUser("6");
    }

    @Bean
    @UserGroup
    public User user7() {
        return createUser("7");
    }
    @Bean
    @UserGroup
    public User user8() {
        return createUser("8");
    }

    private static User createUser(String id) {
        User user = new User();
        user.setId(id);
        return user;
    }
}
