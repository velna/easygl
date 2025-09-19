package com.vanix.easygl.commons.value;

public interface IntValue extends Value<IntInterceptor> {
    int get();

    int set(int v);

    default int incr() {
        return incr(1);
    }

    default int incr(int amount) {
        int v = get();
        return set(v + amount) - v;
    }

    default int multiply(int factor) {
        int v = get();
        return set(v * factor) - v;
    }
}