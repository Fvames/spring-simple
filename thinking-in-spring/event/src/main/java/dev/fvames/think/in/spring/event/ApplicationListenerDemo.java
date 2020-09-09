package dev.fvames.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link ApplicationListener} 示例
 *
 * @author
 * @version 2020/9/8 16:33
 */
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ApplicationListenerDemo.class);

		// 1.直接注册
		context.addApplicationListener((ApplicationListener<ApplicationEvent>) event
				-> printThread(">>>> ApplicationListener - 接收到 Spring ApplicationEvent 事件: "
					+ event.getClass().getSimpleName() + " -> "+event.getSource()));
		// 2.间接注册
		context.register(MyApplicationListener.class);

		context.refresh();
		context.close();
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		applicationEventPublisher.publishEvent(new ApplicationEvent("Spring Event Publisher say: Hello, world.") {
		});
		applicationEventPublisher.publishEvent("你好");
	}

	static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

		@Override
		public void onApplicationEvent(ContextRefreshedEvent event) {
			printThread(">>>>>>> MyApplicationListener - 接收到 Spring ContextRefreshedEvent 事件: " + event.getSource());
		}
	}

	@EventListener
	@Order(2)
	public void onApplicationEvent(ContextRefreshedEvent eventObject) {
		printThread("@EventListener(onApplicationEvent) 接收到 ContextRefreshedEvent 事件");
	}

	@EventListener
	@Order(1)
	public void onApplicationEvent1(ContextRefreshedEvent eventObject) {
		printThread("@EventListener(onApplicationEvent1) 接收到 ContextRefreshedEvent 事件");
	}

	@EventListener
	@Async // 搭配 @EnableAsync 注解开启异步
	public void onApplicationEventAsync(ContextRefreshedEvent eventObject) {
		printThread("@EventListener(异步) 接收到 ContextRefreshedEvent 事件");
	}

	public static void printThread(String msg) {
		System.out.printf("[线程 %s] : %s \n", Thread.currentThread().getName(), msg);
	}
}
