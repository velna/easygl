package com.vanix.easygl.commons.buffer;

import java.nio.IntBuffer;

public interface BufferOpsic extends BufferOpsc {
    default int[] get(int[] out) {
        return get(out, 0);
    }

    int[] get(int[] out, int offset);

    default IntBuffer get(IntBuffer buffer) {
        return get(0, buffer);
    }

    IntBuffer get(int index, IntBuffer buffer);
}
