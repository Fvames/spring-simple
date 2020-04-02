package dev.fvames.thinking.in.spring.bean.lifecycle;

import dev.fvames.thinking.in.spring.ioc.overview.domain.SuperUser;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

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

		Resource resource = new ClassPathResource("META-INF/bean-constructor-dependency-injection.xml");
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		definitionReader.loadBeanDefinitions(encodedResource);

		applicationContext.refresh();

		User user = applicationContext.getBean("user", User.class);
		System.out.println(user);

		SuperUser superUser = applicationContext.getBean("superUser", SuperUser.class);
		System.out.println(superUser);

		UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);

		applicationContext.close();
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if ("superUser".equals(beanName) && beanClass.equals(SuperUser.class)) {
			// 提前实例化 superUser 对象，且不进行后面赋值等操作
			return new SuperUser();
		}
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if ("user".equals(beanName) && bean.getClass().equals(User.class)) {
			User user = (User) bean;
			user.setId("2");
			user.setName("实例化后，属性赋值前");
			// user 对象不允许属性赋值/填入（配置元信息 -> 属性值）
			return false;
		}
		return true;
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && bean.getClass().equals(UserHolder.class)) {
			MutablePropertyValues propertyValues;
			if (pvs instanceof MutablePropertyValues) {
				propertyValues = (MutablePropertyValues) pvs;
			} else {
				propertyValues = new MutablePropertyValues();
			}

			// <property name = "number" value = "1" / >
			propertyValues.add("number", 1);
			// <property name="description" value="This is holder"/>
			if (propertyValues.contains("description")) {
				propertyValues.removePropertyValue("description");
				propertyValues.addPropertyValue("description", "This is holder V2");
			}
			return propertyValues;
		}
		return null;
	}
}
