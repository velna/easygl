package com.vanix.easygl.commons.buffer;

import java.nio.DoubleBuffer;

public interface BufferOpsdc extends BufferOpsc {
    default double[] get(double[] out) {
        return get(out, 0);
    }

    double[] get(double[] out, int offset);

    default DoubleBuffer get(DoubleBuffer buffer) {
        return get(0, buffer);
    }

    DoubleBuffer get(int index, DoubleBuffer buffer);
}
