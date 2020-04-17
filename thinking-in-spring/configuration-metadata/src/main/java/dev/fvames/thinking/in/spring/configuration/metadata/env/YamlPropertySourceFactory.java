package dev.fvames.thinking.in.spring.configuration.metadata.env;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * 解析 yaml 配置為 propertySource
 *
 * @author
 * @version 2020/4/17 16:57
 */

public class YamlPropertySourceFactory implements PropertySourceFactory {
	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
		YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
		yamlPropertiesFactoryBean.setResources(resource.getResource());
		Properties yamlProperties = yamlPropertiesFactoryBean.getObject();
		return new PropertiesPropertySource(name, yamlProperties);
	}
}
