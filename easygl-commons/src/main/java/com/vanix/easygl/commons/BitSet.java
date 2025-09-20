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

    @SafeVarargs
    public final BitSet<T> addAll(T... es) {
        for (var e : es) {
            value |= maskMapper.applyAsInt(e);
        }
        return this;
    }

    public BitSet<T> remove(T e) {
        value &= ~maskMapper.applyAsInt(e);
        return this;
    }

    public BitSet<T> remove(T e1, T e2) {
        value &= ~(maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2));
        return this;
    }

    public BitSet<T> remove(T e1, T e2, T e3) {
        value &= ~(maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2) | maskMapper.applyAsInt(e3));
        return this;
    }

    public BitSet<T> remove(T e1, T e2, T e3, T e4) {
        value &= ~(maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2) | maskMapper.applyAsInt(e3)
                | maskMapper.applyAsInt(e4));
        return this;
    }

    public BitSet<T> remove(T e1, T e2, T e3, T e4, T e5) {
        value &= ~(maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2) | maskMapper.applyAsInt(e3)
                | maskMapper.applyAsInt(e4) | maskMapper.applyAsInt(e5));
        return this;
    }

    @SafeVarargs
    public final BitSet<T> removeAll(T... es) {
        for (var e : es) {
            value &= ~maskMapper.applyAsInt(e);
        }
        return this;
    }

    public boolean contains(T e) {
        int v = maskMapper.applyAsInt(e);
        return (value & v) == v;
    }

    public boolean contains(T e1, T e2) {
        int v = (maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2));
        return (value & v) == v;
    }

    public boolean contains(T e1, T e2, T e3) {
        int v = (maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2) | maskMapper.applyAsInt(e3));
        return (value & v) == v;
    }

    public boolean contains(T e1, T e2, T e3, T e4) {
        int v = (maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2) | maskMapper.applyAsInt(e3)
                | maskMapper.applyAsInt(e4));
        return (value & v) == v;
    }

    public boolean contains(T e1, T e2, T e3, T e4, T e5) {
        int v = (maskMapper.applyAsInt(e1) | maskMapper.applyAsInt(e2) | maskMapper.applyAsInt(e3)
                | maskMapper.applyAsInt(e4) | maskMapper.applyAsInt(e5));
        return (value & v) == v;
    }

    @SafeVarargs
    public final boolean containsAll(T... es) {
        int v = 0;
        for (var e : es) {
            v |= maskMapper.applyAsInt(e);
        }
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

    public static <T extends IntEnum> BitSet<T> of(Class<T> type) {
        return of(null, IntEnum::value);
    }

    public static <T> BitSet<T> of(ToIntFunction<T> maskMapper) {
        return new BitSet<>(null, maskMapper);
    }

    public static <T> BitSet<T> of(T e, ToIntFunction<T> maskMapper) {
        return new BitSet<>(e, maskMapper);
    }
}
