package dev.frames.spring.aop.interceptor;

import dev.frames.spring.aop.V2ReflectiveMethodInvocation;

/**
 * 前、后、异常等处理器的方法拦截接口
 */
public interface V2MethodInterceptor {

    Object invoke(V2ReflectiveMethodInvocation invocation) throws Exception;
}
