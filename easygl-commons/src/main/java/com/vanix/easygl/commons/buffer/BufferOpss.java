package com.vanix.easygl.commons.buffer;

import java.nio.ShortBuffer;

public interface BufferOpss<T extends BufferOpss<T>> extends BufferOps<T>, BufferOpssc {
    default T set(short[] in) {
        return set(in, 0);
    }

    T set(short[] in, int offset);

    default T set(ShortBuffer buffer) {
        return set(0, buffer);
    }

    T set(int index, ShortBuffer buffer);
}
