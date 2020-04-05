package dev.fvames.thinking.in.spring.bean.lifecycle;

import dev.fvames.thinking.in.spring.ioc.overview.domain.SuperUser;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean 实例化生命周期
 *
 * @author
 * @version 2020/3/30 16:49
 */

public class BeanInstantiationLifeCycleDemo {

	public static void main(String[] args) {
		//executeBeanFactory();

		System.out.println("---------------");

		executeApplicationContext();
	}

	private static void executeBeanFactory() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 方法一：添加 BeanPostProcess 的实现
		// 方法二：将 BeanPostProcess 的实现类作为 Bean 注册
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

		// 基于 XML 资源 BeanDefinitionReader 实现
		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
		Resource resource = new ClassPathResource("META-INF/bean-constructor-dependency-injection.xml");
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		definitionReader.loadBeanDefinitions(encodedResource);

		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
		System.out.println(superUser);

		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);

	}

	private static void executeApplicationContext() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.setConfigLocation("META-INF/bean-constructor-dependency-injection.xml");

		applicationContext.refresh();

		User user = applicationContext.getBean("user", User.class);
		System.out.println(user);

		SuperUser superUser = applicationContext.getBean("superUser", SuperUser.class);
		System.out.println(superUser);

		UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);

		applicationContext.close();
	}
}
