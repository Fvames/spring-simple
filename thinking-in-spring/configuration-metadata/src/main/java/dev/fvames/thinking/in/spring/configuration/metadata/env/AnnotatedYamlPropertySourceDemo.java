package dev.fvames.thinking.in.spring.configuration.metadata.env;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 外部化配置：yaml
 *
 * @author
 * @version 2020/4/17 16:10
 */
@PropertySource(name = "yamlPropertySource", value = "META-INF/user.yaml", factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

	@Bean
	public User user(@Value("${user.id:0}")String id, @Value("${user.name}") String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();

		Map<String, Object> propertyMap = new HashMap<>();
		propertyMap.put("user.name", "廖佳一");

		org.springframework.core.env.PropertySource propertySource = new MapPropertySource("first-property-source", propertyMap);
		// user.name 参数默认会取系统值，addFrist 设置
		propertySources.addFirst(propertySource);

		applicationContext.register(AnnotatedYamlPropertySourceDemo.class);

		applicationContext.refresh();
		Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
		for (Map.Entry<String, User> entry : beansOfType.entrySet()) {
			System.out.printf("User bean name: %s, content: %s \n", entry.getKey(), entry.getValue());
		}

		System.out.println("propertySources: " + propertySources);
		applicationContext.close();
	}
}
