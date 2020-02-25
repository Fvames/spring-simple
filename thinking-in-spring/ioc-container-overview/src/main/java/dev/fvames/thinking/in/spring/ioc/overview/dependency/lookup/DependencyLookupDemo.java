package dev.fvames.thinking.in.spring.ioc.overview.dependency.lookup;

import dev.fvames.thinking.in.spring.ioc.overview.annotation.Super;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找
 *
 * @author
 * @version 2020/2/25 10:07
 */

public class DependencyLookupDemo {

	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("/META-INF/dependency-lookup-context.xml");

		lookByAnnotationType(beanFactory);

		//lookCollectionByType(beanFactory);
		//lookByType(beanFactory);

		//lookInRealTime(beanFactory);
		//lookInLazy(beanFactory);
	}

	private static void lookByAnnotationType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
			System.out.println("查找标注 @Super 的所有 User 集合对象： " + users);
		}
	}

	private static void lookCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("查找到所有 User 类型的集合对象： " + users);
		}
	}

	private static void lookByType(BeanFactory beanFactory) {
		User user = beanFactory.getBean(User.class);
		// primary="true"，同一类型多个对象时优先展示
		System.out.println("根据类型实时查找： " + user);
	}

	private static void lookInLazy(BeanFactory beanFactory) {
		// ObjectFactory 返回一个来自 BeanFactory 的 bean，避免违反 ioc 依赖注入的原则
		ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
		User user = objectFactory.getObject();
		System.out.println("延迟查找： " + user);
	}

	private static void lookInRealTime(BeanFactory beanFactory) {
		User user = (User) beanFactory.getBean("user");
		System.out.println("实时查找： " + user);
	}
}
