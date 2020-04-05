package dev.fvames.thinking.in.spring.bean.lifecycle;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * UserHolder 类
 * <p>按顺序实现 aware 接口</p>
 * <p>初始化</p>
 * <p>销毁</p>
 *
 * @author
 * @version 2020/4/2 15:45
 */

public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware
		, EnvironmentAware
		, InitializingBean
		, SmartInitializingSingleton
		, DisposableBean{

	private final User user;
	private Integer number;

	private String description;
	private ClassLoader classLoader;
	private BeanFactory beanFactory;
	private String name;
	private Environment environment;

	public UserHolder(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@PostConstruct
	public void initPostConstruct() {
		this.description = "The user holder V4";
		System.out.println("initPostConstruct(): " + description);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.description = "The user holder V5";
		System.out.println("afterPropertiesSet(): " + description);
	}
	// 自定义初始化方法
	public void doInit() throws Exception {
		this.description = "The user holder V6";
		System.out.println("doInit(): " + description);
	}

	@Override
	public void afterSingletonsInstantiated() {
		this.description = "The user holder V8";
		System.out.println("afterSingletonsInstantiated(): " + description);
	}

	@PreDestroy
	public void preDestroy() {
		this.description = "The user holder V10";
		System.out.println("preDestroy(): " + description);
	}

	@Override
	public void destroy() throws Exception {
		this.description = "The user holder V11";
		System.out.println("destroy(): " + description);
	}

	public void doDestroy() throws Exception {
		this.description = "The user holder V12";
		System.out.println("doDestroy(): " + description);
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				", number=" + number +
				", description='" + description + '\'' +
				", classLoader=" + classLoader +
				", beanFactory=" + beanFactory +
				", name='" + name + '\'' +
				", environment=" + environment +
				'}';
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {

		this.classLoader = classLoader;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

		this.beanFactory = beanFactory;
	}

	@Override
	public void setBeanName(String name) {

		this.name = name;
	}

	@Override
	public void setEnvironment(Environment environment) {

		this.environment = environment;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("The UserHolder is finalized.");
	}
}
