package dev.fvames.springapplicationdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @version 2019/10/9 17:54
 */
@Configuration
public class SpringAnnotationDemo {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(SpringAnnotationDemo.class);
		context.refresh();

		System.out.println(context.getBean(SpringAnnotationDemo.class));
	}
}
