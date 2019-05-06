package dev.fames.mvc.v2.context;

import dev.fames.mvc.annotation.Autowired;
import dev.fames.mvc.annotation.Controller;
import dev.fames.mvc.annotation.Service;
import dev.fames.mvc.v2.aop.V2AopProxy;
import dev.fames.mvc.v2.aop.V2CglibAopProxy;
import dev.fames.mvc.v2.aop.V2JdkDynamicAopProxy;
import dev.fames.mvc.v2.aop.config.V2AopConfig;
import dev.fames.mvc.v2.aop.support.V2AdvisedSupport;
import dev.fames.mvc.v2.beans.V2BeanDefinitionReader;
import dev.fames.mvc.v2.beans.V2BeanWrapper;
import dev.fames.mvc.v2.beans.config.V2BeanDefinition;
import dev.fames.mvc.v2.beans.config.V2BeanPostProcess;
import dev.fames.mvc.v2.core.V2BeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class V2ApplicationContext implements V2BeanFactory {

    private String[] configLoactions;
    private V2BeanDefinitionReader reader;

    // 存储注册信息的 BeanDefinition
    protected final Map<String, V2BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    //单例的IOC容器缓存
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();
    //通用的IOC容器
    private Map<String, V2BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public V2ApplicationContext(String... configLoactions) {
        this.configLoactions = configLoactions;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void refresh() throws Exception {
        //1、定位，定位配置文件
        reader = new V2BeanDefinitionReader(this.configLoactions);

        //2、加载配置文件，扫描相关的类，把它们封装成BeanDefinition
        List<V2BeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

        //3、注册，把配置信息放到容器里面(伪IOC容器)
        doRegisterBeanDefinition(beanDefinitions);

        //4、把不是延时加载的类，有提前初始化
        doAutowrited();
    }

    //只处理非延时加载的情况
    private void doAutowrited() {
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

    private void doRegisterBeanDefinition(List<V2BeanDefinition> beanDefinitions) throws Exception {

        for (V2BeanDefinition beanDefinition : beanDefinitions) {
            if (this.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    @Override
    public Object getBean(String beanName) {

        V2BeanDefinition V2BeanDefinition = this.beanDefinitionMap.get(beanName);
        Object instance = null;

        //工厂模式 + 策略模式
        V2BeanPostProcess postProcessor = new V2BeanPostProcess();
        postProcessor.postProcessBeforeInitialization(instance, beanName);

        instance = instantiateBean(beanName, V2BeanDefinition);

        //3、把这个对象封装到BeanWrapper中
        V2BeanWrapper beanWrapper = new V2BeanWrapper(instance);


        //4、拿到BeanWraoper之后，把BeanWrapper保存到IOC容器中去
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);

        postProcessor.postProcessAfterInitialization(instance, beanName);
        // 注入
        populateBean(beanName, new V2BeanDefinition(), beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    private void populateBean(String beanName, V2BeanDefinition V2BeanDefinition, V2BeanWrapper V2BeanWrapper) {
        Object instance = V2BeanWrapper.getWrappedInstance();

        Class<?> clazz = V2BeanWrapper.getWrappedClass();
        //判断只有加了注解的类，才执行依赖注入
        if (!(clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class))) {
            return;
        }

        //获得所有的fields
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

            //强制访问
            field.setAccessible(true);

            try {

                if (this.factoryBeanInstanceCache.get(autowiredBeanName) == null) {
                    continue;
                }
                if (instance == null) {
                    continue;
                }
                field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    // 实例化
    private Object instantiateBean(String beanName, V2BeanDefinition V2BeanDefinition) {
        //1、拿到要实例化的对象的类名
        String className = V2BeanDefinition.getBeanClassName();

        //2、反射实例化，得到一个对象
        Object instance = null;
        try {

            // 如果存在直接返回
            if (this.factoryBeanObjectCache.containsKey(className)) {
                instance = this.factoryBeanObjectCache.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();

                V2AdvisedSupport config = instantionAopConfig(V2BeanDefinition);
                config.setTargetClass(clazz);
                config.setTarget(instance);

                //符合PointCut的规则的话，创建代理对象
                if (config.pointCutMatch()) {
                    instance = createProxy(config).getProxy();
                }

                this.factoryBeanObjectCache.put(className, instance);
                this.factoryBeanObjectCache.put(V2BeanDefinition.getFactoryBeanName(), instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    private V2AopProxy createProxy(V2AdvisedSupport config) {

        Class targetClass = config.getTargetClass();
        if (targetClass.getInterfaces().length > 0) {
            return new V2JdkDynamicAopProxy(config);
        }
        return new V2CglibAopProxy(config);
    }

    private V2AdvisedSupport instantionAopConfig(V2BeanDefinition V2BeanDefinition) {
        V2AopConfig config = new V2AopConfig();
        config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
        config.setAspectClass(this.reader.getConfig().getProperty("aspectClass"));
        config.setAspectBefore(this.reader.getConfig().getProperty("aspectBefore"));
        config.setAspectAfter(this.reader.getConfig().getProperty("aspectAfter"));
        config.setAspectAfterThrow(this.reader.getConfig().getProperty("aspectAfterThrow"));
        config.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspectAfterThrowingName"));
        return new V2AdvisedSupport(config);
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }

}
