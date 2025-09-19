package com.vanix.easygl.commons.primitives;

import com.vanix.easygl.commons.buffer.BufferOpsdc;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

public interface Dimensiondc<T extends Dimensiondc<T>> extends BufferOpsdc {
    int BYTES = Double.BYTES << 1;

    double getWidth();

    double getHeight();

    default <D extends Dimensiond<D>> D getDimensionG(D dimension) {
        return dimension.setWidth(getWidth()).setHeight(getHeight());
    }

    default Dimensiond<?> getDimension(Dimensiond<?> dimension) {
        return dimension.setWidth(getWidth()).setHeight(getHeight());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putDouble(index, getWidth()).putDouble(index + Double.BYTES, getHeight());
    }

    @Override
    default double[] get(double[] out, int offset) {
        out[offset] = getWidth();
        out[offset + 1] = getHeight();
        return out;
    }

    @Override
    default DoubleBuffer get(int index, DoubleBuffer buffer) {
        return buffer.put(index, getWidth()).put(index + 1, getHeight());
    }

    static Dimensiondc<?> of() {
        return new DimensiondImpl();
    }

    static Dimensiondc<?> of(double width, double height) {
        return new DimensiondImpl(width, height);
    }
}
