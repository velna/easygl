package com.vanix.easygl.commons.util;

import com.vanix.easygl.commons.IntEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.collections.api.block.function.primitive.IntToIntFunction;

public class IntEnumCache<T extends Enum<T> & IntEnum> {
    private final T[] cache;
    private final IntToIntFunction indexMapper;

    public IntEnumCache(Class<T> enumClass, int mask) {
        this(enumClass, v -> v & mask);
    }

    public IntEnumCache(Class<T> enumClass, int split, int mask) {
        this(enumClass, v -> v < split ? v : (v & mask) + split);
    }

    public IntEnumCache(Class<T> enumClass, IntToIntFunction indexMapper) {
        int max = 0;
        for (var e : enumClass.getEnumConstants()) {
            max = Math.max(max, indexMapper.valueOf(e.value()));
        }
        cache = ArrayUtils.newInstance(enumClass, max + 1);
        for (var e : enumClass.getEnumConstants()) {
            var index = indexMapper.valueOf(e.value());
            cache[index] = e;
        }
        this.indexMapper = indexMapper;
    }

    public T valueOf(int value) {
        return cache[indexMapper.valueOf(value)];
    }

    public static IntToIntFunction splitAndMaskMapper(int split, int mask) {
        return v -> v < split ? v : (v & mask) + split;
    }
}
