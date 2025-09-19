package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ListBufferIO<T> extends AbstractMultiElementBufferIO<List<T>> {

    private final BufferIO<T> componentBufferIO;

    public ListBufferIO(BufferIO<T> componentBufferIO, int count) {
        super(count, componentBufferIO.size());
        this.componentBufferIO = componentBufferIO;
    }

    @Override
    protected int countOf(List<T> object) {
        return object.size();
    }

    @Override
    protected List<T> create(int count) {
        var list = new ArrayList<T>(count);
        for (int i = 0; i < count; i++) {
            list.add(null);
        }
        return list;
    }

    @Override
    protected void doWrite(@Nonnull List<T> object, ByteBuffer buffer) {
        for (var obj : object) {
            componentBufferIO.write(obj, buffer);
        }
    }

    @Override
    protected void doRead(@Nonnull List<T> object, ByteBuffer buffer) {
        for (int i = 0; i < count; i++) {
            int index = i;
            componentBufferIO.read(object.get(i), buffer, v -> object.set(index, v));
        }
    }

}
