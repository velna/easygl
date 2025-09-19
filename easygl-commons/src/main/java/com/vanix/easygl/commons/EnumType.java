package com.vanix.easygl.commons;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public abstract class EnumType<T> {
    private final Class<T> valueType;
    private List<T> values;

    protected EnumType(Class<T> valueType) {
        this.valueType = valueType;
    }

    @SuppressWarnings("unchecked")
    public final T[] values() {
        if (values == null) {
            values = new ArrayList<>();
            for (var field : this.getClass().getFields()) {
                if (!Modifier.isStatic(field.getModifiers()) && valueType.isAssignableFrom(field.getType())) {
                    try {
                        values.add((T) field.get(this));
                    } catch (IllegalAccessException e) {
                        // ignore
                    }
                }
            }
        }
        return values.toArray(ArrayUtils.newInstance(valueType, values.size()));
    }
}
