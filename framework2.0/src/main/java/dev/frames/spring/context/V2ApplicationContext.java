package dev.frames.spring.context;

import dev.frames.spring.beans.config.V2BeanDefinition;
import dev.frames.spring.beans.factory.V2BeanDefinitionReader;
import dev.frames.spring.core.V2BeanFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class V2ApplicationContext implements V2BeanFactory {

    private String[] configLocations;
    private V2BeanDefinitionReader reader;

    // 缓存 BeanDefinition 信息
    private final Map<String, V2BeanDefinition> beanDefinitionMap = new HashMap<>();
    //单例的IOC容器缓存
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();
    //通用的IOC容器
    private Map<String, V2BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public V2ApplicationContext(String[] configLocations) throws Exception {
        this.configLocations = configLocations;

        refresh();
    }

    private void refresh() throws Exception {
        // 1.定位
        reader = new V2BeanDefinitionReader(configLocations[0]);
        // 2.加载,读取类名转化为 beanDefinition
        List<V2BeanDefinition> beanDefinitions = reader.loaderBeanDefinitions();

        // 3.注册，把 beanDefiniton 注册到容器中
        doRegisterBeanDefiniton(beanDefinitions);
        // 4.初始化未延迟的 bean， Di
        doAutoWired();

    }

    private void doAutoWired() {
        for (Map.Entry<String, V2BeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doRegisterBeanDefiniton(List<V2BeanDefinition> beanDefinitions) throws Exception {
        for (V2BeanDefinition beanDefinition : beanDefinitions) {
            String factoryBeanName = beanDefinition.getFactoryBeanName();
            if (beanDefinitionMap.containsKey(factoryBeanName)) {
                throw new Exception("已经存在类：" + factoryBeanName);
            }
            beanDefinitionMap.put(factoryBeanName, beanDefinition);
        }
    }


    public Object getBean(String beanName) {
        /*V2BeanDefinition V2BeanDefinition = this.beanDefinitionMap.get(beanName);
        Object instance = null;

        //工厂模式 + 策略模式
        instance = instantiateBean(beanName,V2BeanDefinition);

        //3、把这个对象封装到BeanWrapper中
        V2BeanWrapper beanWrapper = new V2BeanWrapper(instance);


        //4、拿到BeanWraoper之后，把BeanWrapper保存到IOC容器中去
        this.factoryBeanInstanceCache.put(beanName,beanWrapper);

        // 注入
        populateBean(beanName,new V2BeanDefinition(),beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();*/
    }

    private Object instantiateBean(String beanName, V2BeanDefinition v2BeanDefinition) {
        return null;
    }
}
