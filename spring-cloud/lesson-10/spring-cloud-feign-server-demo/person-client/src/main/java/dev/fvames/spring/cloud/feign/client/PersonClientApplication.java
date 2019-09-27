package dev.fvames.spring.cloud.feign.client;

import dev.fvames.spring.cloud.feign.api.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @version 2019/9/27 14:12
 */
@SpringBootApplication
@EnableFeignClients(clients = PersonService.class)
@EnableDiscoveryClient
public class PersonClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonClientApplication.class, args);
	}
}
