package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.HandleArray;

import java.util.List;
import java.util.function.Consumer;

public interface ArrayFactory<T extends Handle> {
    HandleArray<T> createArray(List<T> list, int[] handles, Consumer<int[]> closeFunction);
}
