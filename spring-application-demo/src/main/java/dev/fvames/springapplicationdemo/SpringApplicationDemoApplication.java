package dev.fvames.springapplicationdemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringApplicationDemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringApplicationDemoApplication.class, args);

		new SpringApplicationBuilder(SpringApplicationDemoApplication.class)
				.listeners(evnet -> {
					System.err.println("监听到事件：" + evnet);
				})
				.run(args)
				.close();
	}

}
