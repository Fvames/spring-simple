package dev.frames.spring.aop;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class V2ReflectiveMethodInvocation {


    protected final Object proxy;
    @Nullable
    protected final Object target;

    protected final Method method;

    protected Object[] arguments = new Object[0];

    @Nullable
    private final Class<?> targetClass;

    /**
     * Lazily initialized map of user-specific attributes for this invocation.
     */
    @Nullable
    private Map<String, Object> userAttributes;

    /**
     * List of MethodInterceptor and InterceptorAndDynamicMethodMatcher
     * that need dynamic checks.
     */
    protected final List<?> interceptorsAndDynamicMethodMatchers;

    public V2ReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass, List<?> interceptorsAndDynamicMethodMatchers) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.targetClass = targetClass;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    public Object proceed() throws Throwable {
        return null;
    }
}
