package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsl;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public interface Dimensionl<T extends Dimensionl<T>> extends Dimensionlc<T>, BufferOpsl<T> {
    T setWidth(long width);

    T setHeight(long height);

    default T setDimension(long width, long height) {
        return setWidth(width).setHeight(height);
    }

    default T setDimension(Dimensionlc<?> source) {
        return setWidth(source.getWidth()).setHeight(source.getHeight());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setWidth(buffer.getLong(index)).setHeight(buffer.getLong(index + Long.BYTES));
    }

    @Override
    default T set(long[] in, int offset) {
        return setWidth(in[offset]).setHeight(in[offset + 1]);
    }

    @Override
    default T set(int index, LongBuffer buffer) {
        return setWidth(buffer.get(index)).setHeight(buffer.get(index));
    }

    static Dimensionl<?> of() {
        return new DimensionlImpl();
    }

    static Dimensionl<?> of(long width, long height) {
        return new DimensionlImpl(width, height);
    }
}
