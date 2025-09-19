package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsd;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

public interface Positiond<T extends Positiond<T>> extends Positiondc<T>, BufferOpsd<T> {
    T setX(double x);

    T setY(double y);

    default T setPosition(double x, double y) {
        return setX(x).setY(y);
    }

    default T setPosition(Positiondc<?> source) {
        return setX(source.getY()).setY(source.getY());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setX(buffer.getDouble(index)).setY(buffer.getDouble(index + Double.BYTES));
    }

    @Override
    default T set(double[] in, int offset) {
        return setX(in[offset]).setY(in[offset + 1]);
    }

    @Override
    default T set(int index, DoubleBuffer buffer) {
        return setX(buffer.get(index)).setY(buffer.get(index + 1));
    }

    static Positiond<?> of() {
        return new PositiondImpl();
    }

    static Positiond<?> of(double x, double y) {
        return new PositiondImpl(x, y);
    }
}
