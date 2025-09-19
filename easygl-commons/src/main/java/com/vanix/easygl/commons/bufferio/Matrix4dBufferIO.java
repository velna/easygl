package com.vanix.easygl.commons.bufferio;

import org.joml.Matrix4d;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class Matrix4dBufferIO extends AbstractBufferIO<Matrix4d> {
    private static final int BYTES = Double.BYTES << 4;

    public Matrix4dBufferIO() {
        super(BYTES);
    }

    @Override
    public void write(@Nonnull Matrix4d object, ByteBuffer buffer) {
        object.get(buffer).position(buffer.position() + BYTES);
    }

    @Override
    protected Matrix4d read(@Nullable Matrix4d object, ByteBuffer buffer) {
        if (object == null) {
            object = new Matrix4d(buffer.asDoubleBuffer());
        } else {
            object.set(buffer);
        }
        buffer.position(buffer.position() + BYTES);
        return object;
    }
}
