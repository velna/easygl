package com.vanix.easygl.commons.value;

public interface DoubleValue extends Value<DoubleInterceptor> {
    double get();

    double set(double v);

    default double incr() {
        return incr(1);
    }

    default double incr(double amount) {
        double v = get();
        return set(v + amount) - v;
    }

    default double multiply(double factor) {
        double v = get();
        return set(v * factor) - v;
    }
}