package dev.fvames.starter.autoconfiguration;

import dev.fvames.starter.HelloFormatTemplate;
import dev.fvames.starter.format.FormatProcess;
import dev.fvames.starter.properties.HelloProperteies;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * spi
 * @version 2019/7/11 16:57
 */
@Configuration
@EnableConfigurationProperties(HelloProperteies.class)
@Import(value = FormatAutoConfiguration.class)
public class HelloAutoConfiguration {

	@Bean
	public HelloFormatTemplate formatTemplate(HelloProperteies helloProperteies, FormatProcess formatProcess) {
		return new HelloFormatTemplate(helloProperteies, formatProcess);
	}
}
