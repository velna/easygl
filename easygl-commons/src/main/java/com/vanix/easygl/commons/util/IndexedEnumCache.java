package com.vanix.easygl.commons.util;

import com.vanix.easygl.commons.IntEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

public class IndexedEnumCache<T extends IntEnum> {
    private final int max;
    private final List<T> cache;

    public IndexedEnumCache(int max, IntFunction<T> factory) {
        this.max = max;
        this.cache = LazyList.lazyList(new ArrayList<>(), factory);
    }

    public T valueOf(int i) {
        if (i >= max || i < 0) {
            throw new IllegalArgumentException("Index out of range: " + i);
        }
        return cache.get(i);
    }
}
