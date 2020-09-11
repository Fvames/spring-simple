package dev.fvames.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * TODO 类描述
 *
 * @author
 * @version 2020/9/11 17:14
 */

public class MySpringEventListener implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.printf("接收 Spring 事件 [%s] 的数据 [%s]\n", event.getClass().getSimpleName(), event.getSource());
	}
}
