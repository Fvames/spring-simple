package thinking.in.spring.boot.samples.spring3.annotation;

import org.springframework.context.annotation.Import;
import thinking.in.spring.boot.samples.spring3.config.HelloWorldConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version 2019/10/17 9:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}
