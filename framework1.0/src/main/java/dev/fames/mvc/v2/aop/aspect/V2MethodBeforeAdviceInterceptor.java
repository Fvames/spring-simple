package dev.fames.mvc.v2.aop.aspect;

import dev.fames.mvc.v2.aop.intercept.V2MethodInterceptor;
import dev.fames.mvc.v2.aop.intercept.V2MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by   on 2019/4/15.
 */
public class V2MethodBeforeAdviceInterceptor extends V2AbstractAspectAdvice implements V2Advice, V2MethodInterceptor {


    private V2JoinPoint joinPoint;

    public V2MethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    private void before(Method method, Object[] args, Object target) throws Throwable {
        //传送了给织入参数
        //method.invoke(target);
        super.invokeAdviceMethod(this.joinPoint, null, null);

    }

    @Override
    public Object invoke(V2MethodInvocation mi) throws Throwable {
        //从被织入的代码中才能拿到，JoinPoint
        this.joinPoint = mi;
        before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }
}
