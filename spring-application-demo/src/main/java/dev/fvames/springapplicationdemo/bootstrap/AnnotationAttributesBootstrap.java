package dev.fvames.springapplicationdemo.bootstrap;

import dev.fvames.springapplicationdemo.bean.TransactionalServiceBean;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.AnnotatedElement;

/**
 *
 * @version 2019/10/16 9:56
 */

public class AnnotationAttributesBootstrap {

	public static void main(String[] args) {
		AnnotatedElement annotatedElement =  TransactionalServiceBean.class;

		AnnotationAttributes serviceAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Service.class);
		AnnotationAttributes transactionalAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Transactional.class);

		print(serviceAttributes);
		print(transactionalAttributes);
	}

	private static void print(AnnotationAttributes attributes) {
		System.out.printf("注解 @%s 属性集合： \n", attributes.annotationType().getSimpleName());

		attributes.forEach((name, value) ->
				System.out.printf("\t 属性 %s：%s \n", name, value));
	}
}
