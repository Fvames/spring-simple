package dev.fvames.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * 启动引导类
 *
 * @version 2019/9/10 9:59
 */
@SpringBootApplication
@PropertySource("classpath:rabbitmq.properties")
public class SpringBootRabbitMQDemoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringBootRabbitMQDemoApplication.class, args);
	}
}
