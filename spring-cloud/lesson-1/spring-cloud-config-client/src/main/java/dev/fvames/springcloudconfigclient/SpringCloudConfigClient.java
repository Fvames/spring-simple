package dev.fvames.springcloudconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 *
 * @version 2019/7/22 17:42
 */
@SpringBootApplication
@Import(MyPropertySourceLocator.class)
public class SpringCloudConfigClient {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SpringCloudConfigClient.class);
		application.setWebEnvironment(true);  // 老版本
		//application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);
	}

}
