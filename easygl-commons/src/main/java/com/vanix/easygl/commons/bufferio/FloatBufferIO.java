package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class FloatBufferIO extends AbstractBufferIO<Float> {

    public FloatBufferIO() {
        super(Float.BYTES);
    }

    @Override
    public void write(@Nonnull Float object, ByteBuffer buffer) {
        buffer.putFloat(object);
    }

    @Override
    public Float read(@Nullable Float object, ByteBuffer buffer) {
        return buffer.getFloat();
    }
}
