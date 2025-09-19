package com.vanix.easygl.commons;

import java.util.function.ToIntFunction;

public class BitSet<T> {
    private int value;
    private final ToIntFunction<T> maskMapper;

    private BitSet(T e, ToIntFunction<T> maskMapper) {
        this.maskMapper = maskMapper;
        value = e == null ? 0 : maskMapper.applyAsInt(e);
    }

    @SafeVarargs
    public final BitSet<T> add(T... array) {
        for (var e : array) {
            add(e);
        }
        return this;
    }

    public BitSet<T> add(T e) {
        value |= maskMapper.applyAsInt(e);
        return this;
    }

    public BitSet<T> add(T e1, T e2) {
        value |= maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2);
        return this;
    }

    public BitSet<T> add(T e1, T e2, T e3) {
        value |= maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2) | maskMapper.applyAsInt(e3);
        return this;
    }

    public BitSet<T> add(T e1, T e2, T e3, T e4) {
        value |= maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2) | maskMapper.applyAsInt(e3)
                | maskMapper.applyAsInt(e4);
        return this;
    }

    public BitSet<T> add(T e1, T e2, T e3, T e4, T e5) {
        value |= maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2) | maskMapper.applyAsInt(e3)
                | maskMapper.applyAsInt(e4) | maskMapper.applyAsInt(e5);
        return this;
    }

    public boolean contains(T e) {
        int v = maskMapper.applyAsInt(e);
        return (value & v) == v;
    }

    public void clear() {
        value = 0;
    }

    public int value() {
        return value;
    }

    public BitSet<T> set(int value) {
        this.value = value;
        return this;
    }

    public static <T extends IntEnum> BitSet<T> of(T intEnum) {
        return of(intEnum, IntEnum::value);
    }

    public static <T> BitSet<T> of(ToIntFunction<T> maskMapper) {
        return new BitSet<>(null, maskMapper);
    }

    public static <T> BitSet<T> of(T e, ToIntFunction<T> maskMapper) {
        return new BitSet<>(e, maskMapper);
    }
}
