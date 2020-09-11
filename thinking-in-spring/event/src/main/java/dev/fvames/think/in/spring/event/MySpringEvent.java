package dev.fvames.think.in.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * TODO 类描述
 *
 * @author
 * @version 2020/9/11 17:11
 */

public class MySpringEvent extends ApplicationEvent {
	/**
	 * Create a new {@code ApplicationEvent}.
	 *
	 * @param source the object on which the event initially occurred or with
	 * which the event is associated (never {@code null})
	 */
	public MySpringEvent(Object source) {
		super(source);
	}
}
