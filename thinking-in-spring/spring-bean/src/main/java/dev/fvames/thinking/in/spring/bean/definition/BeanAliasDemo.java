package dev.fvames.thinking.in.spring.bean.definition;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean alias
 *
 * @author
 * @version 2020/2/28 17:45
 */

public class BeanAliasDemo {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/bean-definition-context.xml");
		User user = applicationContext.getBean("user", User.class);
		User aliasUser = applicationContext.getBean("alias-user", User.class);

		System.out.println("aliasUser == user : " +(aliasUser == user));
	}
}
