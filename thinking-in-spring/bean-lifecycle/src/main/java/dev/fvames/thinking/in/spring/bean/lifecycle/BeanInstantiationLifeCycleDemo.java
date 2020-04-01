package dev.fvames.thinking.in.spring.bean.lifecycle;

import dev.fvames.thinking.in.spring.ioc.overview.domain.SuperUser;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Bean 实例化生命周期
 *
 * @author
 * @version 2020/3/30 16:49
 */

public class BeanInstantiationLifeCycleDemo implements InstantiationAwareBeanPostProcessor {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(BeanInstantiationLifeCycleDemo.class);
		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(applicationContext);

		Resource resource = new ClassPathResource("META-INF/dependency-lookup-context.xml");
		definitionReader.loadBeanDefinitions(resource);

		applicationContext.refresh();

		User user = applicationContext.getBean("user", User.class);
		System.out.println(user);

		SuperUser superUser = applicationContext.getBean("superUser", SuperUser.class);
		System.out.println(superUser);

		applicationContext.close();
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if ("superUser".equals(beanName) && beanClass.equals(SuperUser.class)) {
			return new SuperUser();
		}
		return null;
	}
}
