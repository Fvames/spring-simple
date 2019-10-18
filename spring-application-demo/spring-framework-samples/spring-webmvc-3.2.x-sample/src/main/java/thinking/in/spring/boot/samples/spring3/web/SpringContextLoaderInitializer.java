package thinking.in.spring.boot.samples.spring3.web;

import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @version 2019/10/18 10:26
 */

public class SpringContextLoaderInitializer extends AbstractContextLoaderInitializer {
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		return new AnnotationConfigWebApplicationContext();
	}
}
