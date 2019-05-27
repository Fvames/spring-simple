package dev.frames.spring.aop;

import com.sun.istack.internal.Nullable;

public interface V2AopProxy {

    Object getProxy();

    Object getProxy(@Nullable ClassLoader classLoader);
}
