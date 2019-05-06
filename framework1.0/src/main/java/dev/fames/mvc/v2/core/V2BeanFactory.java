package dev.fames.mvc.v2.core;

/**
 * 工厂模式创建 beans
 */
public interface V2BeanFactory {

    // 根据 beans 的名字，获取在 IOC 容器中的 beans 实例
    Object getBean(String name);
}
