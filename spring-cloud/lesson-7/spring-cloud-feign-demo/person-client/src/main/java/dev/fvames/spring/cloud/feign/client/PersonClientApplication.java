package dev.fvames.spring.cloud.feign.client;

import dev.fvames.spring.cloud.feign.api.service.PersonService;
import dev.fvames.spring.cloud.feign.client.ribbon.FirstServerForeverRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

/**
 * 客户端启动类
 *
 * @version 2019/9/18 13:43
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableFeignClients(clients = PersonService.class)
//@RibbonClient(value = "person-service", configuration = PersonClientApplication.class)
@EnableHystrix
public class PersonClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonClientApplication.class, args);
	}

	@Bean
	public FirstServerForeverRule firstServerForeverRule() {
		return new FirstServerForeverRule();
	}
}
