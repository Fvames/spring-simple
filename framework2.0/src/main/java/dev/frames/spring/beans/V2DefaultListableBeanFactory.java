package dev.frames.spring.beans;

import dev.frames.spring.beans.config.V2BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class V2DefaultListableBeanFactory {

    private Map<String, V2BeanDefinition> beanDefinitionMap = new HashMap<String, V2BeanDefinition>();
}
