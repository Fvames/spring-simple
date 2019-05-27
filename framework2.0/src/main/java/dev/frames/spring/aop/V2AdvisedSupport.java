package dev.frames.spring.aop;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.Method;
import java.util.List;

public class V2AdvisedSupport {

    private V2AopConfig aopConfig;

    private Class<?> targetClass;

    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    public Object getTarget() {
        return null;
    }

    // 获取拦截器链
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, @Nullable Class<?> targetClass) {

        return null;
    }
}
