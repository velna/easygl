package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsfc;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public interface Dimensionfc<T extends Dimensionfc<T>> extends BufferOpsfc {
    int BYTES = Float.BYTES << 1;

    float getWidth();

    float getHeight();

    default <D extends Dimensionf<D>> D getDimensionG(D dimension) {
        return dimension.setWidth(getWidth()).setHeight(getHeight());
    }

    default Dimensionf<?> getDimension(Dimensionf<?> dimension) {
        return dimension.setWidth(getWidth()).setHeight(getHeight());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putFloat(index, getWidth()).putFloat(index + Float.BYTES, getHeight());
    }

    @Override
    default float[] get(float[] out, int offset) {
        out[offset] = getWidth();
        out[offset + 1] = getHeight();
        return out;
    }

    @Override
    default FloatBuffer get(int index, FloatBuffer buffer) {
        return buffer.put(index, getWidth()).put(index + 1, getHeight());
    }

    static Dimensionfc<?> of() {
        return new DimensionfImpl();
    }

    static Dimensionfc<?> of(float width, float height) {
        return new DimensionfImpl(width, height);
    }
}
