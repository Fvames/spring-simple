package dev.fvames.starter.autoconfiguration;

import dev.fvames.starter.format.JsonFormatProcess;
import dev.fvames.starter.format.StringFormatProcess;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Java 配置模式
 * TODO ConditionalOnMissingBean 无效
 * @version 2019/7/11 16:54
 */
@Configuration
public class FormatAutoConfiguration {

	@Bean
	@Primary
	@ConditionalOnMissingClass("com.alibaba.fastjson.JSON")
	//@ConditionalOnMissingBean(JSON.class)
	public StringFormatProcess stringFormatProcess() {
		return new StringFormatProcess();
	}

	@Bean
	@ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
	//@ConditionalOnBean(JSON.class)
	public JsonFormatProcess jsonFormatProcess() {
		return new JsonFormatProcess();
	}
}
