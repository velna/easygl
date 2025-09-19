package com.vanix.easygl.commons.util;

public class TypeReferenceBean<T> extends TypeReference<T> {
    private final T bean;

    protected TypeReferenceBean(T bean) {
        this.bean = bean;
    }

    public T getBean() {
        return bean;
    }
}
