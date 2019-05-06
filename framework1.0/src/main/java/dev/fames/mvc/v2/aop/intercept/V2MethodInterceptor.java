package dev.fames.mvc.v2.aop.intercept;

/**
 * Created by   on 2019/4/14.
 */
public interface V2MethodInterceptor {
    Object invoke(V2MethodInvocation invocation) throws Throwable;
}
