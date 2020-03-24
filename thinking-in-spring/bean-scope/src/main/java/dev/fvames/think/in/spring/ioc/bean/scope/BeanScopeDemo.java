package dev.fvames.think.in.spring.ioc.bean.scope;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * Bean 的作用域
 *
 * @author
 * @version 2020/3/24 16:54
 */

public class BeanScopeDemo implements DisposableBean {

	// 默认就是 Singleton
	@Bean
	public User singletonUser() {
		return createUser();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public User prototypeUser() {
		return createUser();
	}

	private static User createUser() {
		User user = new User();
		user.setId(String.valueOf(System.nanoTime()));
		return user;
	}

	//-------------------------------------------------------------------------------------
	//              依赖注入
	//-------------------------------------------------------------------------------------

	@Autowired
	@Qualifier("singletonUser")
	private User singletonUser;
	@Autowired
	@Qualifier("singletonUser")
	private User singletonUser1;

	@Autowired
	@Qualifier("prototypeUser")
	private User prototypeUser;
	@Autowired
	@Qualifier("prototypeUser")
	private User prototypeUser1;

	@Autowired
	private Map<String, User> users;

	@Autowired
	private ConfigurableListableBeanFactory beanFactory;

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(BeanScopeDemo.class);
		applicationContext.addBeanFactoryPostProcessor(beanFactory ->
				beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
					@Override
					public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
						System.out.printf(" %s Bean 名称： %s 在初始化后回调... \n ", bean.getClass().getName(), beanName);
						return bean;
					}
				}));

		applicationContext.refresh();
		// 1. Singleton Bean 无论依赖注入还是依赖查找，均为同一对象；Prototype Bean 无论依赖注入还是依赖查找，都会新生成一个对象
		// 2. 如果依赖注入集合类型的对象，Singleton Bean 和 Prototype Bean 都会存在一个，Singleton Bean 保持唯一，Prototype Bean 会新生成一个
		// 3. 无论 Singleton Bean 还是 Prototype Bean 都会调用初始化回调方法，不过仅 Singleton Bean 会调用销毁方法
		//		(Prototype Bean 生成后脱离容器的生命周期管理，且需要注意及时回收，容易 oom，在 Singleton Bean 中的 Prototype Bean 属性 保持唯一)
		scopedBeansLookup(applicationContext);
		System.out.println("\n");
		scopedBeansInjection(applicationContext);

		applicationContext.close();
	}

	private static void scopedBeansInjection(AnnotationConfigApplicationContext applicationContext) {
		BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
		System.out.println("demo.singletonUser： " + demo.singletonUser);
		System.out.println("demo.singletonUser1： " + demo.singletonUser1);

		System.out.println("demo.prototypeUser： " + demo.prototypeUser);
		System.out.println("demo.prototypeUser1： " + demo.prototypeUser1);

		System.out.println("demo.users: " + demo.users);
	}

	private static void scopedBeansLookup(AnnotationConfigApplicationContext applicationContext) {

		for (int i = 0; i < 3; i++) {
			User singletonUser = applicationContext.getBean("singletonUser", User.class);
			System.out.println("singletonUser: " + singletonUser);

			User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
			System.out.println("prototypeUser: " + prototypeUser);

		}
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("\n当前 Prototype Scope Bean 正在销毁 \n");

		this.prototypeUser.destory();
		this.prototypeUser1.destory();

		for (Map.Entry<String, User> entry : users.entrySet()) {
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(entry.getKey());
			if (beanDefinition.isPrototype()) {
				entry.getValue().destory();
			}
		}

		System.out.println("\n当前 Prototype Scope Bean 销毁完成 \n");
	}
}
