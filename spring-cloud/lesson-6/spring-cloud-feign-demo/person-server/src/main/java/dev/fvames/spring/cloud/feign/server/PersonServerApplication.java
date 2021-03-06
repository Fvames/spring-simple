package dev.fvames.spring.cloud.feign.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务启动类
 *
 * @version 2019/9/18 14:53
 */
@SpringBootApplication
@EnableEurekaClient
public class PersonServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonServerApplication.class, args);
	}
}
