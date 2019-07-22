package dev.fvames.springcloudconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @version 2019/7/22 17:42
 */
@SpringBootApplication
public class SpringCloudConfigClient {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SpringCloudConfigClient.class);
		//application.setEnvironment(true);

		application.run(args);
	}
}
