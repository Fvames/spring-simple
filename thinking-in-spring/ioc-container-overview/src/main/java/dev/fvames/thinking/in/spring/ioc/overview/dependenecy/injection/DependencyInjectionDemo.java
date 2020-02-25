package dev.fvames.thinking.in.spring.ioc.overview.dependenecy.injection;

import dev.fvames.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入
 *
 * @author
 * @version 2020/2/25 11:03
 */

public class DependencyInjectionDemo {

	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

		// 依赖来源 1：自定义 bean
		UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);
		//whoIsIOCContainer(userRepository, applicationContext);
		System.out.println(userRepository.getUsers());

		// 依赖来源 2：依赖注入（内建依赖）
		System.out.println(userRepository.getBeanFactory());
		ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
		System.out.println(objectFactory.getObject() == applicationContext);

		// 依赖查找（错误）
		try {
			System.out.println(beanFactory.getBean(BeanFactory.class));
		}catch (Exception e){
			System.err.println(e.getMessage());
		}

		// 依赖来源 3：容器内建 bean
		Environment environment = applicationContext.getBean(Environment.class);
		System.out.println("获取容器内建的 Environment 类型的 Bean： " + environment);
	}

	private static void whoIsIOCContainer(UserRepository userRepository, ApplicationContext applicationContext) {
		// beanFactory -> applicationContext -> ConfigurableApplicationContext

		System.out.println(userRepository.getBeanFactory() == applicationContext); // false
		System.out.println("userRepository.getBeanFactory: " + userRepository.getBeanFactory());
		System.out.println("application: " + applicationContext);
	}
}
