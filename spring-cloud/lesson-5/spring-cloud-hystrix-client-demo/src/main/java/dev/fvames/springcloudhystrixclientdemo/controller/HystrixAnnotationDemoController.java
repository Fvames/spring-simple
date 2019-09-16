package dev.fvames.springcloudhystrixclientdemo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Hystrix 熔斷
 *
 * @version 2019/9/16 17:08
 */
@RestController
public class HystrixAnnotationDemoController {

	/**
	 * 当调用 {@link #helloWorld()} 超时或失败时，
	 * fallbackMethod {@link #errorContent()} 作为替代返回结果
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/hello-world")
	@HystrixCommand(
			fallbackMethod = "errorContent",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
			}
	)
	public String helloWorld()throws Exception {

		Random random = new Random();
		int value = random.nextInt(200);
		System.out.printf("helloWorld() sleep %d ms \n", value);

		TimeUnit.MILLISECONDS.sleep(value);
		return "Hello, World.";
	}

	public String errorContent() {
		return "Fault";
	}
}
