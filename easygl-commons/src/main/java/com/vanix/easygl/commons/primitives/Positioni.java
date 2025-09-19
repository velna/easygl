package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsi;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface Positioni<T extends Positioni<T>> extends Positionic<T>, BufferOpsi<T> {
    T setX(int x);

    T setY(int y);

    default T setPosition(int x, int y) {
        return setX(x).setY(y);
    }

    default T setPosition(Positionic<?> source) {
        return setX(source.getY()).setY(source.getY());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setX(buffer.getInt(index)).setY(buffer.getInt(index + Integer.BYTES));
    }

    @Override
    default T set(int[] in, int offset) {
        return setX(in[offset]).setY(in[offset + 1]);
    }

    @Override
    default T set(int index, IntBuffer buffer) {
        return setX(buffer.get(index)).setY(buffer.get(index + 1));
    }

    static Positioni<?> of() {
        return new PositioniImpl();
    }

    static Positioni<?> of(int x, int y) {
        return new PositioniImpl(x, y);
    }
}
