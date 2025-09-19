package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsic;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface Positionic<T extends Positionic<T>> extends BufferOpsic {
    int BYTES = Integer.BYTES << 1;

    int getX();

    int getY();

    default <P extends Positioni<P>> P getPositionG(P position){
        return position.setX(getX()).setY(getY());
    }

    default Positioni<?> getPosition(Positioni<?> position){
        return position.setX(getX()).setY(getY());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putInt(index, getX()).putInt(index + Integer.BYTES, getY());
    }

    @Override
    default int[] get(int[] out, int offset) {
        out[offset] = getX();
        out[offset + 1] = getY();
        return out;
    }

    @Override
    default IntBuffer get(int index, IntBuffer buffer) {
        return buffer.put(index, getX()).put(index + 1, getY());
    }

    static Positionic<?> of() {
        return new PositioniImpl();
    }

    static Positionic<?> of(int x, int y) {
        return new PositioniImpl(x, y);
    }
}
