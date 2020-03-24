package dev.fvames.think.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置作为依赖来源
 *
 * @author
 * @version 2020/3/24 16:25
 */
@Configuration
@PropertySource("classpath:META-INF/default-user.properties")
public class ExternalConfigurationDependencySourceDemo {

	@Value("${user.id: -1}")
	private Long id;

	//user.name 默认会获取主机的用户名，而非配置文件的配置值
	@Value("${use.name}")
	private String userName;

	@Value("${user.resource: classpath://default-user.properties}")
	private Resource resource;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

		applicationContext.refresh();

		ExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);
		System.out.println("demo.id: " + demo.id);
		System.out.println("demo.userName: " + demo.userName);
		System.out.println("demo.resouce: " + demo.resource);

		applicationContext.close();
	}
}
