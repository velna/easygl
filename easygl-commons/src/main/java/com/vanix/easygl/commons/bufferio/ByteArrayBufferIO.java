package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

public class ByteArrayBufferIO extends AbstractMultiElementBufferIO<byte[]> {

    public ByteArrayBufferIO(int count) {
        super(count, Byte.BYTES);
    }

    @Override
    protected int countOf(byte[] object) {
        return object.length;
    }

    @Override
    protected byte[] create(int count) {
        return new byte[count];
    }

    @Override
    protected void doWrite(@Nonnull byte[] object, ByteBuffer buffer) {
        buffer.put(object);
    }

    @Override
    protected void doRead(@Nonnull byte[] object, ByteBuffer buffer) {
        buffer.get(object);
    }

}
