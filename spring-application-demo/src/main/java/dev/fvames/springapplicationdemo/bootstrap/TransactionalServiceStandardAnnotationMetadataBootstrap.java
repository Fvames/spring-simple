package dev.fvames.springapplicationdemo.bootstrap;

import dev.fvames.springapplicationdemo.annotation.TransactionalService;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @version 2019/10/16 11:06
 */
@TransactionalService
public class TransactionalServiceStandardAnnotationMetadataBootstrap {

	public static void main(String[] args) {
		AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(TransactionalServiceStandardAnnotationMetadataBootstrap.class);
		LinkedHashSet<String> metaAnnotationTypes = annotationMetadata.getAnnotationTypes()
				.stream()
				.map(annotationMetadata::getMetaAnnotationTypes)
				.collect(LinkedHashSet::new, Set::addAll, Set::addAll);

		metaAnnotationTypes.forEach(metaAnnotation ->{

			Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(metaAnnotation);
			if (!CollectionUtils.isEmpty(annotationAttributes)) {
				annotationAttributes.forEach((name, value) ->
						System.out.printf("注解 @%s 属性 %s = %s \n", ClassUtils.getShortName(metaAnnotation), name, value));
			}
		});
	}

}
