package com.vanix.easygl.commons.value;

@FunctionalInterface
public interface FloatInterceptor {
	float apply(float oldValue, float setValue);
}