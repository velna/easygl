package com.vanix.easygl.commons.value;

public interface FloatValue extends Value<FloatInterceptor> {
    float get();

    float set(float v);

    default float incr() {
        return incr(1);
    }

    default float incr(float amount) {
        float v = get();
        return set(v + amount) - v;
    }

    default float multiply(float factor) {
        float v = get();
        return set(v * factor) - v;
    }
}