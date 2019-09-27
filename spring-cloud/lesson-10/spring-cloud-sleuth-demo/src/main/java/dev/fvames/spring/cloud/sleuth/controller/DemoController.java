package dev.fvames.spring.cloud.sleuth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO 类描述
 *
 * @version 2019/9/27 10:50
 */
@RestController
public class DemoController {
private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

	@Value("${zuul-server-name}")
	private String zuulServerName;
	@Autowired
	private RestTemplate restTemplate;


	@GetMapping("")
	public String index() {

		String returnValue = "Hello, World";

		LOGGER.info("{} index() : {}", getClass().getSimpleName(), returnValue);

		return returnValue;
	}

	@GetMapping("/to/zuul/person-client/person/find/all")
	public Object toZuul(String sourceName) {
		LOGGER.info("spring-cloud-sleuth#toZuul()");

		String url = "http://"+zuulServerName+"/person-client/person/find/all";
		return restTemplate.getForObject(url, Object.class);
	}
}
