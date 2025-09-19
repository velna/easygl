package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

public class FloatArrayBufferIO extends AbstractMultiElementBufferIO<float[]> {
    public FloatArrayBufferIO(int count) {
        super(count, Float.BYTES);
    }

    @Override
    protected int countOf(float[] object) {
        return object.length;
    }

    @Override
    protected float[] create(int count) {
        return new float[count];
    }

    @Override
    protected void doWrite(@Nonnull float[] object, ByteBuffer buffer) {
        buffer.asFloatBuffer().put(object);
        buffer.position(buffer.position() + size);
    }

    @Override
    protected void doRead(@Nonnull float[] object, ByteBuffer buffer) {
        buffer.asFloatBuffer().get(object);
        buffer.position(buffer.position() + size);
    }

}
