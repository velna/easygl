package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class IntBufferIO extends AbstractBufferIO<Integer> {
    public IntBufferIO() {
        super(Integer.BYTES);
    }

    @Override
    public void write(@Nonnull Integer object, ByteBuffer buffer) {
        buffer.putInt(object);
    }

    @Override
    protected Integer read(@Nullable Integer object, ByteBuffer buffer) {
        return buffer.getInt();
    }
}
