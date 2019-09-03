package dev.fvames.springcloudconfigclient;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @version 2019/7/24 17:02
 */
@Configurable
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyPropertySourceLocator implements PropertySourceLocator {

	@Override
	public PropertySource<?> locate(Environment environment) {
		Map<String, Object> source = new HashMap<>();
		source.put("server.port", "9090");

		MapPropertySource propertySource = new MapPropertySource("my-property-source", source);
		return propertySource;
	}
}
