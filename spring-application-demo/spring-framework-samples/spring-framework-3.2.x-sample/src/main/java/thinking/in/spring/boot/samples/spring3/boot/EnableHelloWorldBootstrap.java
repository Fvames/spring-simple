package thinking.in.spring.boot.samples.spring3.boot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import thinking.in.spring.boot.samples.spring3.annotation.EnableHelloWorld;

/**
 * @Import 引入 configuration 类
 * @version 2019/10/17 10:35
 */
@EnableHelloWorld
@Configuration
public class EnableHelloWorldBootstrap {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(EnableHelloWorldBootstrap.class);
		context.refresh();

		String helloWorld = context.getBean("helloWorld", String.class);
		System.out.println("helloWorld = " + helloWorld);
		context.close();
	}
}
