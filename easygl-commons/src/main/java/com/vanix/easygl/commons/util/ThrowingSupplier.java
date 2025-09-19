package com.vanix.easygl.commons.util;

public interface ThrowingSupplier<T, E extends Throwable> {
    T get() throws E;
}
