package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsf;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public interface Dimensionf<T extends Dimensionf<T>> extends Dimensionfc<T>, BufferOpsf<T> {
    T setWidth(float width);

    T setHeight(float height);

    default T setDimension(float width, float height) {
        return setWidth(width).setHeight(height);
    }

    default T setDimension(Dimensionfc<?> source) {
        return setWidth(source.getWidth()).setHeight(source.getHeight());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setWidth(buffer.getFloat(index)).setHeight(buffer.getFloat(index + Float.BYTES));
    }

    @Override
    default T set(float[] in, int offset) {
        return setWidth(in[offset]).setHeight(in[offset + 1]);
    }

    @Override
    default T set(int index, FloatBuffer buffer) {
        return setWidth(buffer.get(index)).setHeight(buffer.get(index));
    }

    static Dimensionf<?> of() {
        return new DimensionfImpl();
    }

    static Dimensionf<?> of(float width, float height) {
        return new DimensionfImpl(width, height);
    }
}
