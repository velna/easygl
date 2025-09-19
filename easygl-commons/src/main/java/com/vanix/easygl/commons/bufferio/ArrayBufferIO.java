package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;

public class ArrayBufferIO<T> extends AbstractMultiElementBufferIO<T[]> {

    private final BufferIO<T> componentBufferIO;
    private final Class<T> componentType;

    public ArrayBufferIO(BufferIO<T> componentBufferIO, Class<T> componentType, int count) {
        super(count, componentBufferIO.size());
        this.componentBufferIO = componentBufferIO;
        this.componentType = componentType;
    }

    @Override
    protected int countOf(T[] object) {
        return object.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T[] create(int count) {
        return (T[]) Array.newInstance(componentType, count);
    }

    @Override
    protected void doWrite(@Nonnull T[] object, ByteBuffer buffer) {
        for (var obj : object) {
            componentBufferIO.write(obj, buffer);
        }
    }

    @Override
    protected void doRead(@Nonnull T[] object, ByteBuffer buffer) {
        for (int i = 0; i < object.length; i++) {
            int index = i;
            componentBufferIO.read(object[i], buffer, v -> object[index] = v);
        }
    }

}
