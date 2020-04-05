package dev.fvames.thinking.in.spring.bean.lifecycle;

import dev.fvames.thinking.in.spring.ioc.overview.domain.SuperUser;
import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.concurrent.TimeUnit;

/**
 * Bean 初始化生命周期
 *
 * @author
 * @version 2020/3/30 16:49
 */

public class BeanLifeCycleDemo {

	public static void main(String[] args) throws InterruptedException {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
		// 解决 @PostConstruct @PreDestroy 无法执行
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		// 执行销毁

		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
		Resource resource = new ClassPathResource("META-INF/bean-constructor-dependency-injection.xml");
		definitionReader.loadBeanDefinitions(resource);
		// 显示地执行 preInstantiateSingletons()
		// SmartInitializingSingleton 通常在 Spring ApplicationContext 场景使用
		// preInstantiateSingletons 将已注册的 BeanDefinition 初始化成 Spring Bean
		beanFactory.preInstantiateSingletons();

		User user = beanFactory.getBean("user", User.class);
		System.out.println("\n" +user);

		SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
		System.out.println(superUser);

		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		System.out.println("\n" + userHolder + "\n");

		beanFactory.destroyBean("userHolder", userHolder);

		// Bean 销毁并不意味着 Bean 垃圾回收了
		System.out.println("\n" + userHolder + "\n");

		// 销毁 BeanFactory 中的单例 Bean
		beanFactory.destroySingletons();

		// 强制 GC
		System.gc();
		TimeUnit.SECONDS.sleep(10);
		System.gc();
	}
}
