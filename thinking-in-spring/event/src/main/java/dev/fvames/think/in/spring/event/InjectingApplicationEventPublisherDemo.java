package dev.fvames.think.in.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

/**
 * 注入 {@link org.springframework.context.ApplicationEventPublisher}
 *
 * @author
 * @version 2020/9/11 17:09
 */

public class InjectingApplicationEventPublisherDemo implements ApplicationEventPublisherAware, ApplicationContextAware {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		//# 3
		applicationEventPublisher.publishEvent(new MySpringEvent("The event from @Autowired ApplicationEventPublisher"));
		// #4
		applicationContext.publishEvent(new MySpringEvent("The event from @Autowired ApplicationContext"));
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(InjectingApplicationEventPublisherDemo.class);
		context.addApplicationListener(new MySpringEventListener());
		context.refresh();

		context.close();
	}

	@Order(2) // @Order 无效，由 org.springframework.context.support.ApplicationContextAwareProcessor.invokeAwareInterfaces 控制顺序
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		// # 1
		applicationEventPublisher.publishEvent(new MySpringEvent("The event from ApplicationEventPublisher."));
	}

	@Order(1)
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// # 2
		applicationContext.publishEvent(new MySpringEvent("The event from ApplicationContext."));
	}
}
