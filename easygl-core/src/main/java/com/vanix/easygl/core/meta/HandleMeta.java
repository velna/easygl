package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.HandleArray;

public interface HandleMeta<T extends Handle> extends Meta<T> {
    default HandleArray<T> createArray(int n, Object... args) {
        throw new UnsupportedOperationException();
    }
}
