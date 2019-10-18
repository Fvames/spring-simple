package thinking.in.spring.boot.samples.spring3.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @version 2019/10/18 10:06
 */

public class SpringWebMvcServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[0];
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return of(SpringWebMvcCofniguration.class);
	}

	@Override
	protected String[] getServletMappings() {
		return of("/*");
	}

	private static <T> T[] of(T... values) {
		return values;
	}
}
