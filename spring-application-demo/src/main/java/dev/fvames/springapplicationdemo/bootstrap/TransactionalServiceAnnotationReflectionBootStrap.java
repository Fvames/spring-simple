package dev.fvames.springapplicationdemo.bootstrap;

import dev.fvames.springapplicationdemo.annotation.TransactionalService;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 *
 *
 * @version 2019/10/15 17:12
 */
@TransactionalService(value = "test", name = "name test1")
public class TransactionalServiceAnnotationReflectionBootStrap {

	public static void main(String[] args) {

		AnnotatedElement annotatedElement = TransactionalServiceAnnotationReflectionBootStrap.class;
		TransactionalService service = annotatedElement.getAnnotation(TransactionalService.class);

		printAnnotationAttribute(service);
	}

	private static void printAnnotationAttribute(Annotation service) {
		Class<?> annotationType = service.annotationType();

		ReflectionUtils.doWithMethods(annotationType,
				method -> System.err.printf("@%s.%s() = %s \n",
						annotationType.getSimpleName(),
						method.getName(),
						ReflectionUtils.invokeMethod(method, service)),
				method -> !method.getDeclaringClass().equals(Annotation.class));
	}
}
