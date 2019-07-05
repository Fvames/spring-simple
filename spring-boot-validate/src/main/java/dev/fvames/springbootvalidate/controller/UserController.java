package dev.fvames.springbootvalidate.controller;

import dev.fvames.springbootvalidate.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 * @version 2019/7/5 17:13
 */
@RestController
public class UserController {


	@PostMapping("/web/user1")
	public User user1(@RequestBody User user) {
		// Assert 验证
		//Assert.hasText(user.getCardNumber(), "卡号不能为空");

		assert user.getId() == 100;

		return user;
	}

	@PostMapping("/web/user2")
	public User user2(@Valid @RequestBody User user) {

		return user;
	}
}
