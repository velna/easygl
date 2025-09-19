package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsi;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface Dimensioni<T extends Dimensioni<T>> extends Dimensionic<T>, BufferOpsi<T> {
    T setWidth(int width);

    T setHeight(int height);

    default T setDimension(int width, int height) {
        return setWidth(width).setHeight(height);
    }

    default T setDimension(Dimensionic<?> source) {
        return setWidth(source.getWidth()).setHeight(source.getHeight());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setWidth(buffer.getInt(index)).setHeight(buffer.getInt(index + Integer.BYTES));
    }

    @Override
    default T set(int[] in, int offset) {
        return setWidth(in[offset]).setHeight(in[offset + 1]);
    }

    @Override
    default T set(int index, IntBuffer buffer) {
        return setWidth(buffer.get(index)).setHeight(buffer.get(index));
    }

    static Dimensioni<?> of() {
        return new DimensioniImpl();
    }

    static Dimensioni<?> of(int width, int height) {
        return new DimensioniImpl(width, height);
    }
}
