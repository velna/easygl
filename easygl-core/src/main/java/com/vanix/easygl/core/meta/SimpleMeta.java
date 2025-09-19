package com.vanix.easygl.core.meta;

import java.util.function.Function;

public class SimpleMeta<T> implements Meta<T> {
    protected final Function<Object[], T> factory;

    public SimpleMeta(Function<Object[], T> factory) {
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
