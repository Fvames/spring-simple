package dev.fvames.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * 启动引导类
 *
 * @author op_lisj@essence.com.cn
 * @version 2019/9/10 9:59
 */
@SpringBootApplication
public class SpringBootRabbitMQDemoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringBootRabbitMQDemoApplication.class, args);
	}
}
