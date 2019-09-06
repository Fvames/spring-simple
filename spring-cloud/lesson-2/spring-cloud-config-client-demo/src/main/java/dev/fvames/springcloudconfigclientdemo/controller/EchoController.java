package dev.fvames.springcloudconfigclientdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @version 2019/9/6 14:06
 */
@RestController
// 1.在更新范围内，调用 /refresh 时可以加载新数据
@RefreshScope
public class EchoController {

	@Value("${my.name}")
	private String myName;

	@GetMapping("/my-name")
	public String getMyName() {

		return myName;
	}
}
