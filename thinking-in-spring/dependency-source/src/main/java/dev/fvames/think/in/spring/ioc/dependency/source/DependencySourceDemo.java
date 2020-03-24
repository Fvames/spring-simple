package dev.fvames.think.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源
 *	{@link BeanFactory}、{@link ResourceLoader}、{@link ApplicationContext}、{@link ApplicationEventPublisher} 仅能依赖注入，不能依赖查找
 * @author
 * @version 2020/3/24 15:25
 */

public class DependencySourceDemo {

	/**
	 * 注入在 {@link AutowiredAnnotationBeanPostProcessor#postProcessProperties}，早于 setter 方法，也早于 {@link PostConstruct}
 	 */
	@Autowired
	private BeanFactory beanFactory;
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@PostConstruct
	public void initByInject() {
		// false
		System.out.println("beanFactory == applicationContext: " +(beanFactory == applicationContext));
		// true
		System.out.println("beanFactory == application.getBeanFactory(): " +(beanFactory == applicationContext.getAutowireCapableBeanFactory()));
		// true
		System.out.println("resourceLoader == applicationContext: " +(resourceLoader == applicationContext));
		// true
		System.out.println("applicationContext == applicationEventPublisher: " +(applicationContext == applicationEventPublisher));
	}

	/**
	 * 依赖查找失败
	 */
	@PostConstruct
	public void initByLookup() {
		getBean(BeanFactory.class);
		getBean(ResourceLoader.class);
		getBean(ApplicationContext.class);
		getBean(ApplicationEventPublisher.class);
	}

	private <T> T getBean(Class<T> beanType) {
		try {
			return this.beanFactory.getBean(beanType);
		} catch (NoSuchBeanDefinitionException e) {
			System.err.println("当前类型 " + beanType.getName() + " 无法在 BeanFactory 中查找");
		}
		return null;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(DependencySourceDemo.class);

		applicationContext.refresh();


		applicationContext.close();
	}
}
