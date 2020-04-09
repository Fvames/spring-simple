package dev.fvames.thinking.in.spring.configuration.metadata;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * Bean 配置元信息
 *
 * attribute 不会覆盖 propertyValue 的值
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "王艺嘉");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        beanDefinition.setAttribute("name", "王艺嘉 2");
        // 设置来源（辅助作用）
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals(User.class, bean.getClass()) && "user".equals(beanName)) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    if (BeanConfigurationMetadataDemo.class.equals(bd.getSource())) {
                        // 属性（存储）上下文
                        String name = (String) bd.getAttribute("name");

                        User user = (User) bean;
                        user.setName(name);
                    }
                }
                return bean;
            }
        });

        beanFactory.registerBeanDefinition("user", beanDefinition);

        System.out.println(beanFactory.getBean("user", User.class));
    }
}
