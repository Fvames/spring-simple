package dev.fvames.springapplicationdemo.bootstrap;

import dev.fvames.springapplicationdemo.annotation.TransactionalService;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.util.Set;

/**
 *
 * @version 2019/10/15 17:52
 */
@TransactionalService
public class TransactionalServiceAnnotationMedaDataBootStrap {

	public static void main(String[] args) throws Exception{

		String className = TransactionalServiceAnnotationMedaDataBootStrap.class.getName();
		CachingMetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();

		MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(className);
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

		annotationMetadata.getAnnotationTypes().forEach(annotationType ->{

			Set<String> metaAnnotationTypes = annotationMetadata.getMetaAnnotationTypes(annotationType);
			metaAnnotationTypes.forEach(metaAnnotationType ->{

				System.err.printf("注解 %s 元标注 @%s \n", annotationType, metaAnnotationType);
			});
		});
	}
}
