package dev.fames.mvc.v2.aop;

import dev.fames.mvc.v2.aop.support.V2AdvisedSupport;

/**
 * Created by   on 2019/4/14.
 */
public class V2CglibAopProxy implements V2AopProxy {
    public V2CglibAopProxy() {
    }

    public V2CglibAopProxy(V2AdvisedSupport config) {
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
