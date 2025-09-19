package com.vanix.easygl.commons.util;

public interface ThrowingFunction<T, R, E extends Exception> {
	R apply(T t) throws E;
}
