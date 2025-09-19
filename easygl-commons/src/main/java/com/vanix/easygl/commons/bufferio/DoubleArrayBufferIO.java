package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

public class DoubleArrayBufferIO extends AbstractMultiElementBufferIO<double[]> {
    public DoubleArrayBufferIO(int count) {
        super(count, Double.BYTES);
    }

    @Override
    protected int countOf(double[] object) {
        return object.length;
    }

    @Override
    protected double[] create(int count) {
        return new double[count];
    }

    @Override
    protected void doWrite(@Nonnull double[] object, ByteBuffer buffer) {
        buffer.asDoubleBuffer().put(object);
        buffer.position(buffer.position() + size);
    }

    @Override
    protected void doRead(@Nonnull double[] object, ByteBuffer buffer) {
        buffer.asDoubleBuffer().get(object);
        buffer.position(buffer.position() + size);
    }

}
