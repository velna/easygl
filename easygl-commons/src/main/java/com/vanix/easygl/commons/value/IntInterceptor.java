package com.vanix.easygl.commons.value;

@FunctionalInterface
public interface IntInterceptor {
	int apply(int oldValue, int setValue);
}