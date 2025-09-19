package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsdc;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

public interface Positiondc<T extends Positiondc<T>> extends BufferOpsdc {
    int BYTES = Double.BYTES << 1;

    double getX();

    double getY();

    default <P extends Positiond<P>> P getPositionG(P position){
        return position.setX(getX()).setY(getY());
    }

    default Positiond<?> getPosition(Positiond<?> position){
        return position.setX(getX()).setY(getY());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putDouble(index, getX()).putDouble(index + Double.BYTES, getY());
    }

    @Override
    default double[] get(double[] out, int offset) {
        out[offset] = getX();
        out[offset + 1] = getY();
        return out;
    }

    @Override
    default DoubleBuffer get(int index, DoubleBuffer buffer) {
        return buffer.put(index, getX()).put(index + 1, getY());
    }

    static Positiondc<?> of() {
        return new PositiondImpl();
    }

    static Positiondc<?> of(double x, double y) {
        return new PositiondImpl(x, y);
    }
}
