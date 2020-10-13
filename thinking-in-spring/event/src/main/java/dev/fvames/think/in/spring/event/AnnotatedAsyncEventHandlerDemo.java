package dev.fvames.think.in.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 注解驱动异步事件 示例
 *
 * @author
 * @version 2020/10/13 17:17
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AnnotatedAsyncEventHandlerDemo.class);
		context.refresh();

		context.publishEvent(new MySpringEvent("Hello, Word."));

		context.close();
	}

	@Async
	@EventListener
	public void onEvent(MySpringEvent event) {
		System.out.printf("[线程 ： %s] onEvent方法监听到事件 : %s\n", Thread.currentThread().getName(), event);
	}
}
