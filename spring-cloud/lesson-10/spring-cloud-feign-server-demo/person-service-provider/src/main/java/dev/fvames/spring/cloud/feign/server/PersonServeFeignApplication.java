package dev.fvames.spring.cloud.feign.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * @version 2019/9/27 14:00
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PersonServeFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonServeFeignApplication.class, args);
	}
}
