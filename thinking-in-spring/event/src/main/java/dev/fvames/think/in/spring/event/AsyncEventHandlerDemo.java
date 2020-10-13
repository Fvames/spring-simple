package dev.fvames.think.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.ErrorHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * 异步事件处理 示例
 *
 * @author
 * @version 2020/10/13 17:47
 */

public class AsyncEventHandlerDemo {

	public static void main(String[] args) {
		GenericApplicationContext context = new GenericApplicationContext();
		context.addApplicationListener(new MySpringEventListener());
		context.refresh();

		ApplicationEventMulticaster multicaster = context.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
		if (multicaster instanceof SimpleApplicationEventMulticaster) {
			SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) multicaster;

			ExecutorService taskExecutor = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool"));
			simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);

			// when container close, shutdown thread pool factory
			simpleApplicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
				@Override
				public void onApplicationEvent(ContextClosedEvent event) {
					if (!taskExecutor.isShutdown()) {
						taskExecutor.shutdown();
					}
				}
			});

			// 异常处理
			simpleApplicationEventMulticaster.setErrorHandler(new ErrorHandler() {
				@Override
				public void handleError(Throwable t) {
					System.err.println("Spring 事件执行异常原因： " + t.getMessage());
				}
			});
		}

		context.addApplicationListener(new ApplicationListener<MySpringEvent>() {
			@Override
			public void onApplicationEvent(MySpringEvent event) {
				throw new RuntimeException("故意抛出异常");
			}
		});
		context.publishEvent(new MySpringEvent("Hello, World."));

		context.close();
	}
}
