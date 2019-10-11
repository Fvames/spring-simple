package dev.fvames.springapplicationdemo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * @version 2019/10/11 14:32
 */
//@Configuration
public class SpringApplicationDemo {
	public static void main(String[] args) {
		/*SpringApplication application = new SpringApplication(SpringApplicationDemo.class);

		Map<String, Object> map = new HashMap<>();
		map.put("server.port", 0);

		application.setDefaultProperties(map);
		application.run(args);*/

		ApplicationListener listener = new ApplicationListener() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				System.out.println("接收到事件：" + event);
			}
		};

		ApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
		multicaster.addApplicationListener(listener);
		multicaster.multicastEvent(new MyEvent("事件1"));

	}

	static class MyEvent extends ApplicationEvent{

		/**
		 * Create a new ApplicationEvent.
		 *
		 * @param source the object on which the event initially occurred (never {@code null})
		 */
		public MyEvent(Object source) {
			super(source);
		}


	}
}
