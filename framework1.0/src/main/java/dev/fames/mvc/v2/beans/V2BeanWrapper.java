package dev.fames.mvc.v2.beans;

public class V2BeanWrapper {
    private Object wrappedInstance;
    private Class<?> wrappedClass;

    public V2BeanWrapper() {
    }

    public V2BeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    public Object getWrappedInstance() {
        return wrappedInstance;
    }

    public Class<?> getWrappedClass() {
        return this.wrappedInstance.getClass();
    }
}
