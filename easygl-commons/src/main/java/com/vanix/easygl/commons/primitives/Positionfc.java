package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsfc;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public interface Positionfc<T extends Positionfc<T>> extends BufferOpsfc {
    int BYTES = Float.BYTES << 1;

    float getX();

    float getY();

    default <P extends Positionf<P>> P getPositionG(P position){
        return position.setX(getX()).setY(getY());
    }

    default Positionf<?> getPosition(Positionf<?> position){
        return position.setX(getX()).setY(getY());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putFloat(index, getX()).putFloat(index + Float.BYTES, getY());
    }

    @Override
    default float[] get(float[] out, int offset) {
        out[offset] = getX();
        out[offset + 1] = getY();
        return out;
    }

    @Override
    default FloatBuffer get(int index, FloatBuffer buffer) {
        return buffer.put(index, getX()).put(index + 1, getY());
    }

    static Positionfc<?> of() {
        return new PositionfImpl();
    }

    static Positionfc<?> of(float x, float y) {
        return new PositionfImpl(x, y);
    }
}
