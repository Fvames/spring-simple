package dev.fvames.springcloudhystrixclientdemo;

import rx.Observer;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Random;

/**
 * RxJava 编程模式
 *
 * @version 2019/9/16 17:54
 */

public class RxJavaDemo {

	public static void main(String[] args) {
		Random random = new Random();

		Single.just("Hello, World") // 返回结果
				.subscribeOn(Schedulers.immediate()) //订阅的线程池 immediate = Thread.currentThread()
				.subscribe(new Observer<String>() {
					@Override
					public void onCompleted() {
						System.out.println("正常执行结束。");
					}

					@Override
					public void onError(Throwable throwable) {
						System.out.println("熔断保护。");
					}

					@Override
					public void onNext(String s) {
						int value = random.nextInt(200);

						if (value > 100) {
							throw new RuntimeException("Timeout!");
						}

						System.out.println("helloWorld() costs " + value + " ms.");
					}
				});
	}
}
