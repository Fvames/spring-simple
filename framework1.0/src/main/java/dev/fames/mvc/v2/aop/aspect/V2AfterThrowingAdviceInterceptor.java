package dev.fames.mvc.v2.aop.aspect;


import dev.fames.mvc.v2.aop.intercept.V2MethodInterceptor;
import dev.fames.mvc.v2.aop.intercept.V2MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by   on 2019/4/15.
 */
public class V2AfterThrowingAdviceInterceptor extends V2AbstractAspectAdvice implements V2Advice, V2MethodInterceptor {


    private String throwingName;

    public V2AfterThrowingAdviceInterceptor() {
        super();
    }

    public V2AfterThrowingAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(V2MethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        } catch (Throwable e) {
            invokeAdviceMethod(mi, null, e.getCause());
            throw e;
        }
    }

    public void setThrowName(String throwName) {
        this.throwingName = throwName;
    }
}
