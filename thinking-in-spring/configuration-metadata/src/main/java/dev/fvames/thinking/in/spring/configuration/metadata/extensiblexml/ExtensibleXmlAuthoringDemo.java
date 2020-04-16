package dev.fvames.thinking.in.spring.configuration.metadata.extensiblexml;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Spring xml 元素扩展
 *
 * @author
 * @version 2020/4/16 17:22
 */

public class ExtensibleXmlAuthoringDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions("META-INF/user-context.xml");

		User user = beanFactory.getBean(User.class);
		System.out.println(user);
	}
}
