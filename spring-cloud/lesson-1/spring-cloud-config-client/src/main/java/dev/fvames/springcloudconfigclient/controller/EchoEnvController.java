package dev.fvames.springcloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 2019/7/22 17:49
 */
@RestController
public class EchoEnvController {

	@Value("${server.port}")
	private Integer port;

	private final Environment environment;

	@Autowired
	public EchoEnvController(Environment environment) {
		this.environment = environment;
	}

	@GetMapping("/echo/server-port")
	public Integer echoServerPort() {

		return port;
	}

	@GetMapping("/echo/env")
	public String echoEnv() {
		String port = environment.getProperty("server.port");
		return port;
	}
}
