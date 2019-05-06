package dev.frames.spring.context;

public class V2BeanWrapper {
    // 包装为对象，需要时直接取出
    private Object wrapperInstance;

    public V2BeanWrapper(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    // 传入类型，生成对象， org.springframework.beans.AbstractNestablePropertyAccessor.AbstractNestablePropertyAccessor(java.lang.Class<?>)
    public V2BeanWrapper(Class<?> clazz) {
        try {
            this.wrapperInstance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }
}
