package dev.fvames.thinking.in.spring.configuration.metadata;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 基于 Java 注解 spring ioc 容器元信息配置
 * 优先加载顺序 @Import -> @Bean -> @ImportResource
 * @author
 * @version 2020/4/16 16:55
 */
@ImportResource("META-INF/dependency-lookup-context.xml")
// 以全类名作为 bean id
@Import(User.class)
@PropertySource("META-INF/user.properties")
public class AnnotatedIoCContainerMetadateConfigurationDemo {

	@Bean
	public User proertiesUser(@Value("${user.id}") String id, @Value("${user.name}") String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(AnnotatedIoCContainerMetadateConfigurationDemo.class);

		applicationContext.refresh();

		Map<String, User> userMap = applicationContext.getBeansOfType(User.class);
		for (Map.Entry<String, User> entry : userMap.entrySet()) {
			System.out.printf("User Bean name: %s, content: %s \n", entry.getKey(), entry.getValue());
		}

		applicationContext.close();
	}
}
