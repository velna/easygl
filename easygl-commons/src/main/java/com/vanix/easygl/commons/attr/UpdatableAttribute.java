package com.vanix.easygl.commons.attr;

public interface UpdatableAttribute<T, O> extends Attribute<T> {
    O set(T value);
}