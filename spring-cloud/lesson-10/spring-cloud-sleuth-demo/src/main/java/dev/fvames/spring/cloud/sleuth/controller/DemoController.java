package dev.fvames.spring.cloud.sleuth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 类描述
 *
 * @version 2019/9/27 10:50
 */
@RestController
public class DemoController {
private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

	@GetMapping("")
	public String index() {

		String returnValue = "Hello, World";

		LOGGER.info("{} index() : {}", getClass().getSimpleName(), returnValue);

		return returnValue;
	}
}
