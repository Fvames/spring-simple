package dev.fvames.thinking.in.spring.bean.lifecycle;

import dev.fvames.thinking.in.spring.ioc.overview.domain.SuperUser;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Bean Definition Merged
 *
 * @author
 * @version 2020/3/30 16:49
 */

public class BeanDefinitionMergedDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);

		Resource resource = new ClassPathResource("META-INF/dependency-lookup-context.xml");
		definitionReader.loadBeanDefinitions(resource);

		User user = beanFactory.getBean(User.class);
		System.out.println(user);

		SuperUser superUser = beanFactory.getBean(SuperUser.class);
		System.out.println(superUser);
	}
}
