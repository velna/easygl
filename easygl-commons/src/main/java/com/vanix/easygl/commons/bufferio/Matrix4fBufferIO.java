package com.vanix.easygl.commons.bufferio;

import org.joml.Matrix4f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class Matrix4fBufferIO extends AbstractBufferIO<Matrix4f> {
    private static final int BYTES = Float.BYTES << 4;

    public Matrix4fBufferIO() {
        super(BYTES);
    }

    @Override
    public void write(@Nonnull Matrix4f object, ByteBuffer buffer) {
        object.get(buffer).position(buffer.position() + BYTES);
    }

    @Override
    protected Matrix4f read(@Nullable Matrix4f object, ByteBuffer buffer) {
        if (object == null) {
            object = new Matrix4f(buffer.asFloatBuffer());
        } else {
            object.set(buffer);
        }
        buffer.position(buffer.position() + BYTES);
        return object;
    }
}
