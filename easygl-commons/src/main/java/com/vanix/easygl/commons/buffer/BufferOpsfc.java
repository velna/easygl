package com.vanix.easygl.commons.buffer;

import java.nio.FloatBuffer;

public interface BufferOpsfc extends BufferOpsc {
    default float[] get(float[] out) {
        return get(out, 0);
    }

    float[] get(float[] out, int offset);

    default FloatBuffer get(FloatBuffer buffer) {
        return get(0, buffer);
    }

    FloatBuffer get(int index, FloatBuffer buffer);
}
