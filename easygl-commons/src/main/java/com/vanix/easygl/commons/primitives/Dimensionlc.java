package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpslc;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public interface Dimensionlc<T extends Dimensionlc<T>> extends BufferOpslc {
    int BYTES = Long.BYTES << 1;

    long getWidth();

    long getHeight();

    default <D extends Dimensionl<D>> D getDimensionG(D dimension) {
        return dimension.setWidth(getWidth()).setHeight(getHeight());
    }

    default Dimensionl<?> getDimension(Dimensionl<?> dimension) {
        return dimension.setWidth(getWidth()).setHeight(getHeight());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putLong(index, getWidth()).putLong(index + Long.BYTES, getHeight());
    }

    @Override
    default long[] get(long[] out, int offset) {
        out[offset] = getWidth();
        out[offset + 1] = getHeight();
        return out;
    }

    @Override
    default LongBuffer get(int index, LongBuffer buffer) {
        return buffer.put(index, getWidth()).put(index + 1, getHeight());
    }

    static Dimensionlc<?> of() {
        return new DimensionlImpl();
    }

    static Dimensionlc<?> of(long width, long height) {
        return new DimensionlImpl(width, height);
    }
}
