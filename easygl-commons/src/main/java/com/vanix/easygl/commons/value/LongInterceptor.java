package com.vanix.easygl.commons.value;

@FunctionalInterface
public interface LongInterceptor {
	long apply(long oldValue, long setValue);
}