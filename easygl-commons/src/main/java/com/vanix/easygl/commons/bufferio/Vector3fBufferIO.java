package com.vanix.easygl.commons.bufferio;

import org.joml.Vector3f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class Vector3fBufferIO extends AbstractBufferIO<Vector3f> {
    private static final int BYTES = Float.BYTES * 3;

    public Vector3fBufferIO() {
        super(BYTES);
    }

    @Override
    public void write(@Nonnull Vector3f object, ByteBuffer buffer) {
        object.get(buffer).position(buffer.position() + BYTES);
    }

    @Override
    protected Vector3f read(@Nullable Vector3f object, ByteBuffer buffer) {
        if (object == null) {
            object = new Vector3f(buffer);
        } else {
            object.set(buffer);
        }
        buffer.position(buffer.position() + BYTES);
        return object;
    }
}
