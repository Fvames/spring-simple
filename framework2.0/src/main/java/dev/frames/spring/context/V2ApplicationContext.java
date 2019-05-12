package dev.frames.spring.context;

import dev.frames.spring.annotation.Autowired;
import dev.frames.spring.annotation.Controller;
import dev.frames.spring.annotation.Service;
import dev.frames.spring.beans.config.V2BeanDefinition;
import dev.frames.spring.beans.factory.V2BeanDefinitionReader;
import dev.frames.spring.core.V2BeanFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class V2ApplicationContext implements V2BeanFactory {

    private String[] configLocations;
    private V2BeanDefinitionReader reader;

    // 缓存 BeanDefinition 信息
    private final Map<String, V2BeanDefinition> beanDefinitionMap = new HashMap<>();
    //单例的IOC容器缓存
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();
    //通用的IOC容器
    private Map<String, V2BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public V2ApplicationContext(String... configLocations) {
        try {
            this.configLocations = configLocations;

            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        System.out.println(">>>>>>>> 初始化完成");

    }

    private void doAutoWired() {
        for (Map.Entry<String, V2BeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String factoryBeanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                try {
                    getBean(factoryBeanName);
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

    /**
     * initHandleMapping 会再次 di 一次
     *
     * @param factoryBeanName
     * @return
     */
    @Override
    public Object getBean(String factoryBeanName) {

        V2BeanDefinition v2BeanDefinition = this.beanDefinitionMap.get(factoryBeanName);

        Object instance = instantiateBean(factoryBeanName, v2BeanDefinition);

        //3、把这个对象封装到BeanWrapper中
        V2BeanWrapper beanWrapper = new V2BeanWrapper(instance);


        //4、拿到BeanWraoper之后，把BeanWrapper保存到IOC容器中去
        this.factoryBeanInstanceCache.put(factoryBeanName, beanWrapper);
        this.factoryBeanInstanceCache.put(v2BeanDefinition.getBeanClassName(), beanWrapper);

        // 注入 org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.checkDependencies 单例检测循环依赖，property 不能处理，构造注入方式不能处理
        populateBean(factoryBeanName, new V2BeanDefinition(), beanWrapper);

        return this.factoryBeanInstanceCache.get(factoryBeanName).getWrapperInstance();
    }

    private void populateBean(String factoryBeanName, V2BeanDefinition v2BeanDefinition, V2BeanWrapper beanWrapper) {

        Class<?> clazz = beanWrapper.getWrapperInstance().getClass();

        if (!(clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class))) {
            return;
        }

        // 获取所有的 fileds
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }

            Autowired autowired = field.getAnnotation(Autowired.class);
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }

            field.setAccessible(true);

            V2BeanWrapper cacheBeanWrapper = factoryBeanInstanceCache.get(autowiredBeanName);
            if (cacheBeanWrapper == null) {
                continue;
            }

            try {
                // 赋值操作，spring 实现更复杂，参考源码
                field.set(beanWrapper.getWrapperInstance(), cacheBeanWrapper.getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private Object instantiateBean(String factoryBeanName, V2BeanDefinition v2BeanDefinition) {
        Object instance = null;
        // 判断单例缓存是否存在
        if (factoryBeanObjectCache.containsKey(factoryBeanName)) {
            return factoryBeanObjectCache.get(factoryBeanName);
        } else {

            try {
                Class<?> clazz = Class.forName(v2BeanDefinition.getBeanClassName());
                instance = clazz.newInstance();

                factoryBeanObjectCache.put(factoryBeanName, instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return instance;
    }

    public List<String> getBeanDefinitionNames() {

        return beanDefinitionMap.keySet().stream().collect(Collectors.toList());

    }

    public V2BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }
}
