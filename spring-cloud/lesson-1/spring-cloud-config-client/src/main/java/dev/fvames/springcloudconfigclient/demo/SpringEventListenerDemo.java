package dev.fvames.springcloudconfigclient.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @version 2019/7/22 17:15
 */

public class SpringEventListenerDemo {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {
			/**
			 * 监听事件
			 * @param event
			 */
			public void onApplicationEvent(MyApplicationEvent event) {
				System.out.printf("接收到的事件：%s	@	%s", event.getSource(), event.getApplicationContext());
			}
		});

		// 发布事件
		context.refresh();
		context.publishEvent(new MyApplicationEvent(context,"Hello World."));
	}

	public static class MyApplicationEvent extends ApplicationEvent{

		private final ApplicationContext applicationContext;
		/**
		 * Create a new ApplicationEvent.
		 *
		 * @param source the object on which the event initially occurred (never {@code null})
		 */
		public MyApplicationEvent(ApplicationContext applicationContext, Object source) {
			super(source);
			this.applicationContext = applicationContext;
		}

		public ApplicationContext getApplicationContext() {
			return applicationContext;
		}
	}
}
