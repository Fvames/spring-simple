package dev.fames.mvc.v2.aop.aspect;

import dev.fames.mvc.v2.aop.intercept.V2MethodInterceptor;
import dev.fames.mvc.v2.aop.intercept.V2MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by   on 2019/4/15.
 */
public class V2AfterReturningAdviceInterceptor extends V2AbstractAspectAdvice implements V2Advice, V2MethodInterceptor {

    private V2JoinPoint joinPoint;

    public V2AfterReturningAdviceInterceptor() {

    }

    public V2AfterReturningAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }


    @Override
    public Object invoke(V2MethodInvocation mi) throws Throwable {
        Object retVal = mi.proceed();
        this.joinPoint = mi;
        this.afterReturning(retVal, mi.getMethod(), mi.getArguments(), mi.getThis());
        return retVal;
    }

    private void afterReturning(Object retVal, Method method, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.joinPoint, retVal, null);
    }
}
