package com.vanix.easygl.commons.buffer;

import java.nio.DoubleBuffer;

public interface BufferOpsd<T extends BufferOpsd<T>> extends BufferOps<T>, BufferOpsdc {
    default T set(double[] in) {
        return set(in, 0);
    }

    T set(double[] in, int offset);

    default T set(DoubleBuffer buffer) {
        return set(0, buffer);
    }

    T set(int index, DoubleBuffer buffer);
}
