package dev.frames.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class V2JdkDynamicAopProxy implements V2AopProxy, InvocationHandler {

    private V2AdvisedSupport advised;

    public V2JdkDynamicAopProxy(V2AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.advised.getClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.advised.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 以 method 为 key 保存该方法的执行器链（前、后方法）
        List<Object> interceptorsAndDynamicMethodMatchers =
                this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, this.advised.getTargetClass());

        V2ReflectiveMethodInvocation methodInvocation =
                new V2ReflectiveMethodInvocation(proxy, null, method, args,
                        this.advised.getTargetClass(), interceptorsAndDynamicMethodMatchers);
        return methodInvocation.proceed();
    }
}
