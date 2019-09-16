package dev.fvames.springcloudhystrixclientdemo.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Hystrix 编程方式熔断
 *
 * @version 2019/9/16 17:43
 */
@RestController
public class HystricCommandDemoController {

	Random random = new Random();

	@GetMapping("/hello-world-2")
	public String helloWorld2() {
		return new HelloWorldCommand().execute();
	}

	private class HelloWorldCommand extends HystrixCommand<String> {

		protected HelloWorldCommand() {
			super(HystrixCommandGroupKey.Factory.asKey("aaaaa"), 100);
		}

		@Override
		protected String run() throws Exception {
			int value = random.nextInt(200);
			System.out.printf("helloWorld2() sleep %s ms. \n", value);

			TimeUnit.MILLISECONDS.sleep(value);
			return "Hello World 2";
		}

		@Override
		protected String getFallback() {
			return "Fault";
		}
	}

}
