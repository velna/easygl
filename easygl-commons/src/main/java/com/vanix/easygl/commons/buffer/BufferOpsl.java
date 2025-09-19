package com.vanix.easygl.commons.buffer;

import java.nio.LongBuffer;

public interface BufferOpsl<T extends BufferOpsl<T>> extends BufferOps<T>, BufferOpslc {
    default T set(long[] in) {
        return set(in, 0);
    }

    T set(long[] in, int offset);

    default T set(LongBuffer buffer) {
        return set(0, buffer);
    }

    T set(int index, LongBuffer buffer);
}
