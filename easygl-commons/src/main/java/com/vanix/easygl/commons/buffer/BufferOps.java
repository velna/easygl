package com.vanix.easygl.commons.buffer;

import java.nio.ByteBuffer;

public interface BufferOps<T extends BufferOps<T>> extends BufferOpsc {
    default T set(ByteBuffer buffer) {
        return set(0, buffer);
    }

    T set(int index, ByteBuffer buffer);
}
