package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class LongBufferIO extends AbstractBufferIO<Long> {
    public LongBufferIO() {
        super(Long.BYTES);
    }

    @Override
    public void write(@Nonnull Long object, ByteBuffer buffer) {
        buffer.putLong(object);
    }

    @Override
    protected Long read(@Nullable Long object, ByteBuffer buffer) {
        return buffer.getLong();
    }
}
