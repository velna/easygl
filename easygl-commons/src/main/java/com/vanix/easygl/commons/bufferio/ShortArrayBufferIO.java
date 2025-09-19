package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class ShortArrayBufferIO extends AbstractMultiElementBufferIO<short[]> {
    public ShortArrayBufferIO(int count) {
        super(count, Short.BYTES);
    }

    @Override
    protected int countOf(short[] object) {
        return object.length;
    }

    @Override
    protected short[] create(int count) {
        return new short[count];
    }

    @Override
    protected void doWrite(@Nonnull short[] object, ByteBuffer buffer) {
        buffer.asShortBuffer().put(object);
        buffer.position(buffer.position() + size);
    }

    @Override
    protected void doRead(@Nullable short[] object, ByteBuffer buffer) {
        buffer.asShortBuffer().get(object);
        buffer.position(buffer.position() + size);
    }

}
