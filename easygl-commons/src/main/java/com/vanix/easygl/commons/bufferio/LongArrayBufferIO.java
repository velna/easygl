package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

public class LongArrayBufferIO extends AbstractMultiElementBufferIO<long[]> {
    public LongArrayBufferIO(int count) {
        super(count, Long.BYTES);
    }

    @Override
    protected int countOf(long[] object) {
        return object.length;
    }

    @Override
    protected long[] create(int count) {
        return new long[count];
    }

    @Override
    protected void doWrite(@Nonnull long[] object, ByteBuffer buffer) {
        buffer.asLongBuffer().put(object);
        buffer.position(buffer.position() + size);
    }

    @Override
    protected void doRead(@Nonnull long[] object, ByteBuffer buffer) {
        buffer.asLongBuffer().get(object);
        buffer.position(buffer.position() + size);
    }

}
