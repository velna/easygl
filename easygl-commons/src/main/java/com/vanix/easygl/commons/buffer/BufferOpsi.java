package com.vanix.easygl.commons.buffer;

import java.nio.IntBuffer;

public interface BufferOpsi<T extends BufferOpsi<T>> extends BufferOps<T>, BufferOpsic {
    default T set(int[] in) {
        return set(in, 0);
    }

    T set(int[] in, int offset);

    default T set(IntBuffer buffer) {
        return set(0, buffer);
    }

    T set(int index, IntBuffer buffer);
}
