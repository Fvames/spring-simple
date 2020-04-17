package dev.fvames.thinking.in.spring.configuration.metadata.env;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 基于 XML 资源的 YAML 外部化配置
 *
 * @author
 * @version 2020/4/17 16:45
 */

public class XmlBasedYamlPropertySourceDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
		definitionReader.loadBeanDefinitions("META-INF/yaml-property-source-context.xml");

		Map yamlMap = beanFactory.getBean("yamlMap", Map.class);
		System.out.println(yamlMap);
	}
}
