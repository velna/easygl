package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.IntFunction;

public class BufferListBufferIO<T extends BufferList> extends AbstractMultiElementBufferIO<T> {
    private final IntFunction<T> factory;

    protected BufferListBufferIO(int count, int sizeOfUnit, IntFunction<T> factory) {
        super(count, sizeOfUnit);
        this.factory = factory;
    }

    @Override
    protected int countOf(T object) {
        return object.size();
    }

    @Override
    protected T create(int count) {
        return factory.apply(count);
    }

    @Override
    protected void doWrite(@Nonnull T object, ByteBuffer buffer) {
        object.saveInto(buffer);
    }

    @Override
    protected void doRead(@Nonnull T object, ByteBuffer buffer) {
        object.loadFrom(buffer, count);
    }

}
