package dev.fvames.thinking.in.spring.bean.definition;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注解创建 BeanDefinition 示例
 *
 * @author
 * @version 2020/2/28 16:51
 */
@Import(AnnotationBeanDefinitionCreationDemo.Config.class)
public class AnnotationBeanDefinitionCreationDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 配置类
		applicationContext.register(AnnotationBeanDefinitionCreationDemo.class);

		// 通过 BeanDefinition 注册 API 实现
		// 1.命名 Bean 的注册方式
		registerBeanDefinition(applicationContext, "wangjiaer-user");
		// 2.非命名方式注册
		registerBeanDefinition(applicationContext);

		applicationContext.refresh();

		// 查找,不会重复
		System.out.println("Config 类型的所有 Bean 集合： "+ applicationContext.getBeansOfType(User.class));
		System.out.println("User 类型的所有 Bean 集合： "+ applicationContext.getBeansOfType(User.class));
		// 关闭
		applicationContext.close();
	}

	private static void registerBeanDefinition(BeanDefinitionRegistry registry) {
		registerBeanDefinition(registry, null);
	}

	private static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		beanDefinitionBuilder
				.addPropertyValue("id", "1")
				.addPropertyValue("name", "王嘉尔");
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

		if (StringUtils.hasText(beanName)) {
			registry.registerBeanDefinition(beanName, beanDefinition);
		} else {
			BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
		}
	}

	@Component
	public static class Config{

		@Bean(name = {"user", "wang-user"})
		public User user() {
			User user = new User();
			user.setId("1");
			user.setName("王嘉尔");
			return user;
		}
	}
}
