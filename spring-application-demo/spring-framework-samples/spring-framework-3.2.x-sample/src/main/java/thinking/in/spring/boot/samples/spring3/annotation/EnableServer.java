package thinking.in.spring.boot.samples.spring3.annotation;

import org.springframework.context.annotation.Import;
import thinking.in.spring.boot.samples.spring3.server.Server;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 激活服务器
 *
 * @version 2019/10/17 10:52
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ServerImportSelector.class)
public @interface EnableServer {

	/**
	 * 设置服务器类型
	 * @return
	 */
	Server.Type type();
}
