package dev.fvames.springapplicationdemo.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @version 2019/10/15 17:29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Transactional
@Service(value = "transactionalService")
public @interface TransactionalService {

	@AliasFor(attribute = "value")
	String name() default "";

	@AliasFor(attribute = "name")
	String value() default "";

	/**
	 * 建立 {@link Transactional#transactionManager()} 别名
	 *
	 * @return {@link PlatformTransactionManager} Bean 名称，默认关联 "txManager" Bean
	 */
	@AliasFor(attribute = "transactionManager", annotation = Transactional.class)
	String manager() default "";
}
