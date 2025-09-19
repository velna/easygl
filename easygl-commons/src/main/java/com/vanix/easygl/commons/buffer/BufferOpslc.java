package com.vanix.easygl.commons.buffer;

import java.nio.LongBuffer;

public interface BufferOpslc extends BufferOpsc {
    default long[] get(long[] out) {
        return get(out, 0);
    }

    long[] get(long[] out, int offset);

    default LongBuffer get(LongBuffer buffer) {
        return get(0, buffer);
    }

    LongBuffer get(int index, LongBuffer buffer);
}
