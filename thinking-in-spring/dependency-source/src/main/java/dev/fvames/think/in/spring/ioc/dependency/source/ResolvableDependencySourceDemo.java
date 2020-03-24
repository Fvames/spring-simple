package dev.fvames.think.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖来源
 *
 * @author
 * @version 2020/3/24 16:16
 */

public class ResolvableDependencySourceDemo {

	@Autowired
	private String value;

	@PostConstruct
	public void init() {
		System.out.println("value: " + value);
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(ResolvableDependencySourceDemo.class);

		applicationContext.addBeanFactoryPostProcessor(beanFactory ->
				beanFactory.registerResolvableDependency(String.class, "Hello, world."));

		applicationContext.refresh();

		applicationContext.close();
	}

}
