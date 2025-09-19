package com.vanix.easygl.commons.value;

public interface LongValue extends Value<LongInterceptor> {
    long get();

    long set(long v);

    default long incr() {
        return incr(1);
    }

    default long incr(long amount) {
        long v = get();
        return set(v + amount) - v;
    }

    default long multiply(long factor) {
        long v = get();
        return set(v * factor) - v;
    }
}