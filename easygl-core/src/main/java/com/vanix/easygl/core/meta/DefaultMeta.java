package com.vanix.easygl.core.meta;

import java.util.function.Function;

public class DefaultMeta<T> implements Meta<T>{

    protected final Function<Object[], T> factory;

    public DefaultMeta(Function<Object[], T> factory) {
        this.factory = factory;
    }

    @Override
    public T create(Object... args) {
        if (factory == null) {
            throw new UnsupportedOperationException();
        }
        return factory.apply(args);
    }
}
