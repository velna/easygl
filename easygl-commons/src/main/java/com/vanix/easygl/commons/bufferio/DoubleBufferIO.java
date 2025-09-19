package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class DoubleBufferIO extends AbstractBufferIO<Double> {
    public DoubleBufferIO() {
        super(Double.BYTES);
    }

    @Override
    public void write(@Nonnull Double object, ByteBuffer buffer) {
        buffer.putDouble(object);
    }

    @Override
    protected Double read(@Nullable Double object, ByteBuffer buffer) {
        return buffer.getDouble();
    }
}
