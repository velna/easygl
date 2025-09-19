package com.vanix.easygl.core.meta;

public interface MetaService {
    <T> T of(Class<?> key, Object... args);

    int queryInt(String id);

    default Object[] queryArray(String id) {
        throw new UnsupportedOperationException();
    }

    ErrorCode getError();

    void close();
}
