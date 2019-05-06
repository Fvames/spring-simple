package dev.fames.mvc.v2.aop.aspect;

import java.lang.reflect.Method;

/**
 * Created by   on 2019/4/15.
 */
public interface V2JoinPoint {

    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}
