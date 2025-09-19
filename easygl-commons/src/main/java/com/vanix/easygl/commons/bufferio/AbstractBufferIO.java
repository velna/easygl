package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public abstract class AbstractBufferIO<T> implements BufferIO<T> {
    protected final int size;

    protected AbstractBufferIO(int size) {
        this.size = size;
    }

    protected abstract T read(@Nullable T object, ByteBuffer buffer);

    @Override
    public final int size() {
        return size;
    }

    @Override
    public final T read(@Nullable T object, ByteBuffer buffer, @Nullable Consumer<T> setter) {
        int pos = buffer.position();
        var value = read(object, buffer);
        if (buffer.position() - pos != size) {
            throw new BufferIOException("Expect read " + size + " bytes, but only " + (buffer.position() - pos) + " detected.");
        }
        if (setter != null && value != object) {
            setter.accept(value);
        }
        return value;
    }
}
