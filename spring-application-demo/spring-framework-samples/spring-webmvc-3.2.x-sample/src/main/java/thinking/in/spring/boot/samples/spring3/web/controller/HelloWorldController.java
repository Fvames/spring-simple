package thinking.in.spring.boot.samples.spring3.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 2019/10/18 10:10
 */
@Controller
public class HelloWorldController {

	@RequestMapping
	@ResponseBody
	public String helloWorld(String name) {
		return name + "Hello, World.";
	}
}
