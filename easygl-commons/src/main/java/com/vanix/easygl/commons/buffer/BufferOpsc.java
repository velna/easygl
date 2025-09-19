package com.vanix.easygl.commons.buffer;

import java.nio.ByteBuffer;

public interface BufferOpsc {
    default ByteBuffer get(ByteBuffer buffer) {
        return get(0, buffer);
    }

    ByteBuffer get(int index, ByteBuffer buffer);
}
