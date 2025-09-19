package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsd;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

public interface Dimensiond<T extends Dimensiond<T>> extends Dimensiondc<T>, BufferOpsd<T> {
    T setWidth(double width);

    T setHeight(double height);

    default T setDimension(double width, double height) {
        return setWidth(width).setHeight(height);
    }

    default T setDimension(Dimensiondc<?> source) {
        return setWidth(source.getWidth()).setHeight(source.getHeight());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setWidth(buffer.getDouble(index)).setHeight(buffer.getDouble(index + Double.BYTES));
    }

    @Override
    default T set(double[] in, int offset) {
        return setWidth(in[offset]).setHeight(in[offset + 1]);
    }

    @Override
    default T set(int index, DoubleBuffer buffer) {
        return setWidth(buffer.get(index)).setHeight(buffer.get(index));
    }

    static Dimensiond<?> of() {
        return new DimensiondImpl();
    }

    static Dimensiond<?> of(double width, double height) {
        return new DimensiondImpl(width, height);
    }
}
