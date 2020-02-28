package dev.fvames.thinking.in.spring.bean.definition;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link BeanDefinition} 构建示例
 *
 * @author
 * @version 2020/2/28 16:00
 */

public class BeanDefinitionCreationDemo {
	public static void main(String[] args) {
		// 1.通过 BeanDefinitionBuilder 构建
		beanDefinitionBuilder();
		// 2.通过 AbstractBeanDefinition 以及派生类构建
		genericBeanDefinition();
	}

	private static void genericBeanDefinition() {
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(User.class);

		MutablePropertyValues propertyValues = new MutablePropertyValues();
		//propertyValues.add("id", "1");
		//propertyValues.add("name", "王嘉尔");

		propertyValues
				.add("id", "1")
				.add("name", "王嘉尔");

		beanDefinition.setPropertyValues(propertyValues);
	}

	private static void beanDefinitionBuilder() {
		BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		definitionBuilder
				.addPropertyValue("id", "1")
				.addPropertyValue("name", "王嘉尔");
		BeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();

	}
}
