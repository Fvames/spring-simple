package dev.fvames.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认 {@link UserFactory} 实现
 * {@link InitializingBean#afterPropertiesSet()} 初始化 Bean
 * <p>
 *     初始化优先级顺序：
 *     1. @PostConstruct
 *     2. InitializingBean#afterPropertiesSet()
 *     3. 自定义
 * </p>
 *
 * {@link DisposableBean#destroy()} 销毁 Bean
 * <p>
 *     销毁优先级顺序
 *     1.@PreDestroy
 *     2.DisposableBean#destroy()
 *     3.自定义销毁
 * </p>
 *
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    // 初始化
    @PostConstruct
    public void doInit() {
        System.out.println("@PostConstruct 初始化中 " );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() 初始化中");
    }

    // 自定义初始化
    public void initUserFactory() {
        System.out.println("自定义 initUserFactory() 初始化中");
    }

    // 销毁
    @PreDestroy
    public void doDestroy() {
        System.out.println("@PreDestroy 销毁中");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy() 销毁中");
    }

    // 自定义销毁
    public void destroyUserFactory() {
        System.out.println("自定义 destroyUserFactory() 销毁中");
    }
}
