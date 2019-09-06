package dev.fvames.springcloudconfigclientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootApplication
public class SpringCloudConfigClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigClientDemoApplication.class, args);
	}

	private final ContextRefresher contextRefresher;

	private final Environment environment;

	public SpringCloudConfigClientDemoApplication(ContextRefresher contextRefresher, Environment environment) {
		this.contextRefresher = contextRefresher;
		this.environment = environment;
	}

	@Scheduled(fixedRate = 5 * 1000, initialDelay = 3 * 1000)
	public void autoRefresh() {

		Set<String> updatedPropertyNames = contextRefresher.refresh();
		updatedPropertyNames.forEach(propertyName ->
				System.err.printf("[Thread :%s] 当前配置已更新，具体 Key: %s，Value: %s \n",
						Thread.currentThread().getName(),
						propertyName,
						environment.getProperty(propertyName))
		);
	}
}
