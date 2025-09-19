package com.vanix.easygl.commons.bufferio;

import org.joml.Vector2f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class Vector2fBufferIO extends AbstractBufferIO<Vector2f> {
    private static final int BYTES = Float.BYTES * 2;

    public Vector2fBufferIO() {
        super(BYTES);
    }

    @Override
    public void write(@Nonnull Vector2f object, ByteBuffer buffer) {
        object.get(buffer).position(buffer.position() + BYTES);
    }

    @Override
    protected Vector2f read(@Nullable Vector2f object, ByteBuffer buffer) {
        if (object == null) {
            object = new Vector2f(buffer);
        } else {
            object.set(buffer);
        }
        buffer.position(buffer.position() + BYTES);
        return object;
    }
}
