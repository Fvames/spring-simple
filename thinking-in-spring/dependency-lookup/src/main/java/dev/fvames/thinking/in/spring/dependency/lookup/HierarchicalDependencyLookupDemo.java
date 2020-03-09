package dev.fvames.thinking.in.spring.dependency.lookup;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 层次性依赖查找
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        HierarchicalBeanFactory parentBeanFactory = new DefaultListableBeanFactory();
        ((DefaultListableBeanFactory) parentBeanFactory).registerSingleton("user", User.createUser());
        beanFactory.setParentBeanFactory(parentBeanFactory);
        //applicationContext.register(HierarchicalDependencyLookupDemo.class);

        applicationContext.refresh();
        System.out.println("当前 applicationContext 的 Parent BeanFactory： " +applicationContext.getParentBeanFactory());
        System.out.println("当前 BeanFactory 的 Parent BeanFactory： " +beanFactory.getParentBeanFactory());

        displayContainsBean(beanFactory, "user");
        displayContainsBean(parentBeanFactory, "user");

        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");

        applicationContext.close();
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Local Bean[name: %s]: %s \n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Bean[name: %s]: %s \n", beanFactory, beanName, containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }

        return beanFactory.containsBean(beanName);
    }

    @Bean
    public User user(){
        return User.createUser();
    }
}
