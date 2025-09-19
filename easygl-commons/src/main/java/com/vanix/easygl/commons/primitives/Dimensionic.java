package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsic;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface Dimensionic<T extends Dimensionic<T>> extends BufferOpsic {
    int BYTES = Integer.BYTES << 1;

    int getWidth();

    int getHeight();

    default <D extends Dimensioni<D>> D getDimensionG(D dimension) {
        return dimension.setWidth(getWidth()).setHeight(getHeight());
    }

    default Dimensioni<?> getDimension(Dimensioni<?> dimension) {
        return dimension.setWidth(getWidth()).setHeight(getHeight());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putInt(index, getWidth()).putInt(index + Integer.BYTES, getHeight());
    }

    @Override
    default int[] get(int[] out, int offset) {
        out[offset] = getWidth();
        out[offset + 1] = getHeight();
        return out;
    }

    @Override
    default IntBuffer get(int index, IntBuffer buffer) {
        return buffer.put(index, getWidth()).put(index + 1, getHeight());
    }

    static Dimensionic<?> of() {
        return new DimensioniImpl();
    }

    static Dimensionic<?> of(int width, int height) {
        return new DimensioniImpl(width, height);
    }
}
