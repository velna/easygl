package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

public class IntArrayBufferIO extends AbstractMultiElementBufferIO<int[]> {
    public IntArrayBufferIO(int count) {
        super(count, Integer.BYTES);
    }

    @Override
    protected int countOf(int[] object) {
        return object.length;
    }

    @Override
    protected int[] create(int count) {
        return new int[count];
    }

    @Override
    protected void doWrite(@Nonnull int[] object, ByteBuffer buffer) {
        buffer.asIntBuffer().put(object);
        buffer.position(buffer.position() + size);
    }

    @Override
    protected void doRead(@Nonnull int[] object, ByteBuffer buffer) {
        buffer.asIntBuffer().get(object);
        buffer.position(buffer.position() + size);
    }

}
