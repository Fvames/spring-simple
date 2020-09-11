package dev.fvames.think.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 层次性 Spring 事件传播实例
 *
 * @author
 * @version 2020/9/11 15:59
 */

public class HierarchicalSpringEventPropagateDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
		parentContext.setId("parent-context");
		parentContext.register(MyListener.class);

		AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
		currentContext.setId("current-context");
		currentContext.register(MyListener.class);
		currentContext.setParent(parentContext);

		parentContext.refresh();
		currentContext.refresh();


		currentContext.close();
		parentContext.close();

	}


	public static class MyListener implements ApplicationListener<ApplicationContextEvent> {

		public static Set<ApplicationContextEvent> cache = new LinkedHashSet<>();

		@Override
		public void onApplicationEvent(ApplicationContextEvent event) {

			if (cache.add(event)) {

				System.out.printf("监听到 Spring 应用上下文 [ID: %s] 事件： %s \n",
						event.getApplicationContext().getId(), event.getClass().getSimpleName());
			}
		}
	}
}
