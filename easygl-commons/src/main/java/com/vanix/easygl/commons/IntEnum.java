package com.vanix.easygl.commons;

import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public interface IntEnum {
    int value();

    @SuppressWarnings("unchecked")
    static <T extends IntEnum> T[] values(Class<T> type) {
        if (type.isEnum()) {
            return type.getEnumConstants();
        } else {
            return (T[]) IntEnumCache.valuesMap.computeIfAbsent(type, key -> {
                List<T> values = new ArrayList<>();
                for (var field : type.getFields()) {
                    if (Modifier.isStatic(field.getModifiers()) && type.isAssignableFrom(field.getType())) {
                        try {
                            values.add((T) field.get(null));
                        } catch (IllegalAccessException e) {
                            // ignore
                        }
                    }
                }
                return values.toArray(ArrayUtils.newInstance(type, values.size()));
            });
        }
    }

    @Nullable
    static <T extends IntEnum> T valueOf(Class<T> type, int value) {
        for (var e : values(type)) {
            if (e.value() == value) {
                return e;
            }
        }
        return null;
    }
}
