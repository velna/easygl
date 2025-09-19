package com.vanix.easygl.commons.buffer;

import java.nio.FloatBuffer;

public interface BufferOpsf<T extends BufferOpsf<T>> extends BufferOps<T>, BufferOpsfc {
    default T set(float[] in) {
        return set(in, 0);
    }

    T set(float[] in, int offset);

    default T set(FloatBuffer buffer) {
        return set(0, buffer);
    }

    T set(int index, FloatBuffer buffer);
}
