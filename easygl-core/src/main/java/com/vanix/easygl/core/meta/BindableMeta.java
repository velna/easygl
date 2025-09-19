package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.Handle;

public interface BindableMeta<E extends BindTarget<E, T>, T extends Handle> extends HandleMeta<T> {
    BindingState<E, T> newBindingState(String name);

    default long unbindValueLong() {
        throw new UnsupportedOperationException();
    }

    default int unbindValueInt() {
        throw new UnsupportedOperationException();
    }
}
