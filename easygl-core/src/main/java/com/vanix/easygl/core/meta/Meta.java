package com.vanix.easygl.core.meta;

public interface Meta<T> {
    T create(Object... args);
}
