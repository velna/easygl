package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpslc;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public interface Positionlc<T extends Positionlc<T>> extends BufferOpslc {
    int BYTES = Long.BYTES << 1;

    long getX();

    long getY();

    default <P extends Positionl<P>> P getPositionG(P position){
        return position.setX(getX()).setY(getY());
    }

    default Positionl<?> getPosition(Positionl<?> position){
        return position.setX(getX()).setY(getY());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putLong(index, getX()).putLong(index + Long.BYTES, getY());
    }

    @Override
    default long[] get(long[] out, int offset) {
        out[offset] = getX();
        out[offset + 1] = getY();
        return out;
    }

    @Override
    default LongBuffer get(int index, LongBuffer buffer) {
        return buffer.put(index, getX()).put(index + 1, getY());
    }

    static Positionlc<?> of() {
        return new PositionlImpl();
    }

    static Positionlc<?> of(long x, long y) {
        return new PositionlImpl(x, y);
    }
}
