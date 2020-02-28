package dev.fvames.thinking.in.spring.ioc.overview.container;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 注解 {@link AnnotationConfigApplicationContext} 作为 IoC 容器
 *
 * @author
 * @version 2020/2/28 15:43
 */
//@Configuration
public class AnnotationApplicationContextAsIoCContainerDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
		applicationContext.refresh();

		lookupByType(applicationContext);

		applicationContext.close();
	}

	private static void lookupByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("查找到的所有 User 对象的集合： "+ users);
		}
	}

	/**
	 * 通过 Java 注解的方式，定义了一个 Bean
	 */
	@Bean
	public User user() {
		User user = new User();
		user.setId("1");
		user.setName("王嘉尔");
		return user;
	}
}
