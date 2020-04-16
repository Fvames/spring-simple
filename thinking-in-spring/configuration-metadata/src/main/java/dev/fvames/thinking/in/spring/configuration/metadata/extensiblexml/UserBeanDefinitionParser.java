package dev.fvames.thinking.in.spring.configuration.metadata.extensiblexml;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * 解析执行类
 *
 * @author
 * @version 2020/4/16 17:48
 */

public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return User.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		setPropertyValue("id", element, builder);
		setPropertyValue("name", element, builder);
		setPropertyValue("city", element, builder);
	}

	private void setPropertyValue(String attributeName, Element element, BeanDefinitionBuilder builder) {
		String attributeValue = element.getAttribute(attributeName);
		if (StringUtils.hasText(attributeValue)) {
			builder.addPropertyValue(attributeName, attributeValue);
		}
	}
}
