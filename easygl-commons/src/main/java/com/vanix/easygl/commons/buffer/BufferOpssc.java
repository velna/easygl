package com.vanix.easygl.commons.buffer;

import java.nio.ShortBuffer;

public interface BufferOpssc extends BufferOpsc {
    default short[] get(short[] out) {
        return get(out, 0);
    }

    short[] get(short[] out, int offset);

    default ShortBuffer get(ShortBuffer buffer) {
        return get(0, buffer);
    }

    ShortBuffer get(int index, ShortBuffer buffer);
}
