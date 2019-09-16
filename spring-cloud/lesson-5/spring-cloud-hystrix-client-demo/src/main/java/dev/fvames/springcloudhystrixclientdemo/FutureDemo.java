package dev.fvames.springcloudhystrixclientdemo;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Future Demo
 *
 * @version 2019/9/16 17:26
 */

public class FutureDemo {

	public static void main(String[] args) {

		Random random = new Random();
		ExecutorService executorService = Executors.newFixedThreadPool(1);

		Future<String> submit = executorService.submit(() -> {
			int value = random.nextInt(200);
			System.out.printf("future sleep %s ms. \n", value);

			Thread.sleep(value);

			return "Hello, World";
		});

		try {
			String result = submit.get(100, TimeUnit.MILLISECONDS);
			System.out.printf("future result: %s .\n", result);
		} catch (Exception e) {
			System.out.println("超时保护。");
		}

		executorService.shutdown();
	}
}
