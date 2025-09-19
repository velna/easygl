package com.vanix.easygl.commons.bufferio;

import org.joml.Vector3i;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class Vector3iBufferIO extends AbstractBufferIO<Vector3i> {
    private static final int BYTES = Integer.BYTES * 3;

    public Vector3iBufferIO() {
        super(BYTES);
    }

    @Override
    public void write(@Nonnull Vector3i object, ByteBuffer buffer) {
        object.get(buffer).position(buffer.position() + BYTES);
    }

    @Override
    public Vector3i read(@Nullable Vector3i object, ByteBuffer buffer) {
        if (object == null) {
            object = new Vector3i(buffer);
        } else {
            object.set(buffer);
        }
        buffer.position(buffer.position() + BYTES);
        return object;
    }
}
