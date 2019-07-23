package dev.fvames.springcloudconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @version 2019/7/22 17:42
 */
@SpringBootApplication
public class SpringCloudConfigClient {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SpringCloudConfigClient.class);
		//application.setEnvironment(true);  // 老版本
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);
	}
}
