package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsf;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public interface Positionf<T extends Positionf<T>> extends Positionfc<T>, BufferOpsf<T> {
    T setX(float x);

    T setY(float y);

    default T setPosition(float x, float y) {
        return setX(x).setY(y);
    }

    default T setPosition(Positionfc<?> source) {
        return setX(source.getY()).setY(source.getY());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setX(buffer.getFloat(index)).setY(buffer.getFloat(index + Float.BYTES));
    }

    @Override
    default T set(float[] in, int offset) {
        return setX(in[offset]).setY(in[offset + 1]);
    }

    @Override
    default T set(int index, FloatBuffer buffer) {
        return setX(buffer.get(index)).setY(buffer.get(index + 1));
    }

    static Positionf<?> of() {
        return new PositionfImpl();
    }

    static Positionf<?> of(float x, float y) {
        return new PositionfImpl(x, y);
    }
}
