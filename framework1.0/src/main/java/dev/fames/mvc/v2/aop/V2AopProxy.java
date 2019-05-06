package dev.fames.mvc.v2.aop;

/**
 * Created by  .
 */
public interface V2AopProxy {


    Object getProxy();


    Object getProxy(ClassLoader classLoader);
}
