package dev.fvames.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 定义外部化配置
 * @version 2019/7/11 17:58
 */
@ConfigurationProperties(prefix = HelloProperteies.HELLO_FORMAT_PREFFIX)
public class HelloProperteies {

	public static final String HELLO_FORMAT_PREFFIX = "hello.format";
	private Map<String, Object> info;

	public Map<String, Object> getInfo() {
		return info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}
}
