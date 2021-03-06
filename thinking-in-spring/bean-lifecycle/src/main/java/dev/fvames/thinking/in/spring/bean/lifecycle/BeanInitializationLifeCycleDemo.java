package dev.fvames.thinking.in.spring.bean.lifecycle;

import dev.fvames.thinking.in.spring.ioc.overview.domain.SuperUser;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Bean 初始化生命周期
 *
 * @author
 * @version 2020/3/30 16:49
 */

public class BeanInitializationLifeCycleDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		// 解决 @PostConstruct 无法执行
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
		Resource resource = new ClassPathResource("META-INF/bean-constructor-dependency-injection.xml");
		definitionReader.loadBeanDefinitions(resource);

		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
		System.out.println(superUser +"\n");

		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		System.out.println("\n" + userHolder);
	}
}
