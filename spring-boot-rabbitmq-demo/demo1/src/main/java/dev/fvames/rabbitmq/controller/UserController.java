package dev.fvames.rabbitmq.controller;

import dev.fvames.rabbitmq.provider.MyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 类描述
 *
 * @version 2019/9/16 10:18
 */
@RestController
public class UserController {

	@Autowired
	private MyProvider myProvider;

	@GetMapping("/user/send")
	public String userSend(String name) {

		myProvider.sendToThird();
		return "success";
	}
}
