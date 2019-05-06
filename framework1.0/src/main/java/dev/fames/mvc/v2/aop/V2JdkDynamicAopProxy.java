package dev.fames.mvc.v2.aop;

import dev.fames.mvc.v2.aop.intercept.V2MethodInvocation;
import dev.fames.mvc.v2.aop.support.V2AdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by   on 2019/4/14.
 */
public class V2JdkDynamicAopProxy implements V2AopProxy, InvocationHandler {

    private V2AdvisedSupport advised;

    public V2JdkDynamicAopProxy() {
    }

    public V2JdkDynamicAopProxy(V2AdvisedSupport config) {
        this.advised = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.advised.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.advised.getTargetClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> interceptorsAndDynamicMethodMatchers = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, this.advised.getTargetClass());
        V2MethodInvocation invocation = new V2MethodInvocation(proxy, this.advised.getTarget(), method, args, this.advised.getTargetClass(), interceptorsAndDynamicMethodMatchers);
        return invocation.proceed();
    }
}
