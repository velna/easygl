package com.vanix.easygl.commons.value;

@FunctionalInterface
public interface DoubleInterceptor {
	double apply(double oldValue, double setValue);
}