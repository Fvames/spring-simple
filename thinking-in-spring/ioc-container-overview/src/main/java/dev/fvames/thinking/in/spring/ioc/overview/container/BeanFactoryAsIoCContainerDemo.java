package dev.fvames.thinking.in.spring.ioc.overview.container;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * {@link BeanFactory} 作为 IoC 容器
 *
 * @author
 * @version 2020/2/28 11:46
 */

public class BeanFactoryAsIoCContainerDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		int beansCount = reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

		System.out.println("Bean 定义加载的数量： " + beansCount);

		lookupCollectionByType(beanFactory);

	}

	private static void lookupCollectionByType(DefaultListableBeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			Map<String, User> users = beanFactory.getBeansOfType(User.class);
			System.out.println("查找到的所有 User 对象的集合： " + users);
		}
	}
}
