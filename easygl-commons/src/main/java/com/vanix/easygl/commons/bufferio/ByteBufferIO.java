package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class ByteBufferIO extends AbstractBufferIO<Byte> {
    public ByteBufferIO() {
        super(Byte.BYTES);
    }

    @Override
    public void write(@Nonnull Byte object, ByteBuffer buffer) {
        buffer.put(object);
    }

    @Override
    public Byte read(@Nullable Byte object, ByteBuffer buffer) {
        return buffer.get();
    }
}
