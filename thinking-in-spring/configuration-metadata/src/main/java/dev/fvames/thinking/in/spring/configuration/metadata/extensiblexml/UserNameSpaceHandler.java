package dev.fvames.thinking.in.spring.configuration.metadata.extensiblexml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 绑定 user 标签的处理类
 *
 * @author
 * @version 2020/4/16 17:47
 */

public class UserNameSpaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("users", new UserBeanDefinitionParser());
	}
}
