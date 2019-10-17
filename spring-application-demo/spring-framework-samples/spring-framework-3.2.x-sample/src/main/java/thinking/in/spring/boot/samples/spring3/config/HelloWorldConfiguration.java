package thinking.in.spring.boot.samples.spring3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 2019/10/17 10:33
 */
@Configuration
public class HelloWorldConfiguration {

	@Bean
	public String helloWorld() {
		return "Hello World";
	}
}
