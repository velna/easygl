package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsl;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public interface Positionl<T extends Positionl<T>> extends Positionlc<T>, BufferOpsl<T> {
    T setX(long x);

    T setY(long y);

    default T setPosition(long x, long y) {
        return setX(x).setY(y);
    }

    default T setPosition(Positionlc<?> source) {
        return setX(source.getY()).setY(source.getY());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setX(buffer.getLong(index)).setY(buffer.getLong(index + Long.BYTES));
    }

    @Override
    default T set(long[] in, int offset) {
        return setX(in[offset]).setY(in[offset + 1]);
    }

    @Override
    default T set(int index, LongBuffer buffer) {
        return setX(buffer.get(index)).setY(buffer.get(index + 1));
    }

    static Positionl<?> of() {
        return new PositionlImpl();
    }

    static Positionl<?> of(long x, long y) {
        return new PositionlImpl(x, y);
    }
}
