package com.vanix.easygl.commons.bufferio;

import org.joml.Vector3d;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class Vector3dBufferIO extends AbstractBufferIO<Vector3d> {
    private static final int BYTES = Double.BYTES * 3;

    public Vector3dBufferIO() {
        super(BYTES);
    }

    @Override
    public void write(@Nonnull Vector3d object, ByteBuffer buffer) {
        object.get(buffer).position(buffer.position() + BYTES);
    }

    @Override
    protected Vector3d read(@Nullable Vector3d object, ByteBuffer buffer) {
        if (object == null) {
            object = new Vector3d(buffer);
        } else {
            object.set(buffer);
        }
        buffer.position(buffer.position() + BYTES);
        return object;
    }
}
