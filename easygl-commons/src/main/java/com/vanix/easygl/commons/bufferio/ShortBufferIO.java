package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class ShortBufferIO extends AbstractBufferIO<Short> {
    public ShortBufferIO() {
        super(Short.BYTES);
    }

    @Override
    public void write(@Nonnull Short object, ByteBuffer buffer) {
        buffer.putShort(object);
    }

    @Override
    protected Short read(@Nullable Short object, ByteBuffer buffer) {
        return buffer.getShort();
    }
}
