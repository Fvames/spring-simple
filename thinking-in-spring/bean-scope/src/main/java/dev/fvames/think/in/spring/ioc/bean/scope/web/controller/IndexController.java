package dev.fvames.think.in.spring.ioc.bean.scope.web.controller;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页 Spring mvc Controller
 *
 * @author
 * @version 2020/3/31 15:15
 */
@Controller
public class IndexController {
	@Autowired
	private User user;

	@GetMapping("/index.html")
	public String index() {
		return "index";
	}
}
