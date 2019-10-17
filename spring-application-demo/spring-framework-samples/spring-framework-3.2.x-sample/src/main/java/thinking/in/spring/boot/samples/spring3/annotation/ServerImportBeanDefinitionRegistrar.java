package thinking.in.spring.boot.samples.spring3.annotation;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.Stream;

/**
 *
 * @version 2019/10/17 13:21
 */

public class ServerImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		ImportSelector importSelector = new ServerImportSelector();
		String[] selectedClassNames = importSelector.selectImports(importingClassMetadata);

		Stream.of(selectedClassNames)
				.map(BeanDefinitionBuilder::genericBeanDefinition)// 转换为 BeanDefinitionBuilder 对象
				.map(BeanDefinitionBuilder::getBeanDefinition)// 转换为 BeanDefinition
				.forEach(beanDefinition ->
						BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry)
				);

	}
}
