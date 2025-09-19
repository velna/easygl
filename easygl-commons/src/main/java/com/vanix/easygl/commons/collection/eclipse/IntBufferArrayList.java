package com.vanix.easygl.commons.collection.eclipse;

import com.vanix.easygl.commons.bufferio.BufferList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class IntBufferArrayList extends IntArrayList implements BufferList {
    public IntBufferArrayList() {
    }

    public IntBufferArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public IntBufferArrayList(int initialCapacity, int fillValue) {
        super(initialCapacity);
        if (fillValue != 0) {
            Arrays.fill(items, fillValue);
        }
        size = initialCapacity;
    }

    public IntBufferArrayList(int... array) {
        super(array);
    }

    @Override
    public int saveInto(ByteBuffer buffer) {
        var localBuffer = buffer.asIntBuffer();
        int remaining = localBuffer.remaining();
        if (size == 0 || remaining == 0) {
            return 0;
        }
        int n = Math.min(size, remaining);
        localBuffer.put(items, 0, n);
        buffer.position(buffer.position() + n * Integer.BYTES);
        return n;
    }

    @Override
    public void loadFrom(ByteBuffer buffer, int count) {
        int newSize = Math.max(this.size, count);
        this.ensureCapacity(newSize);
        buffer.asIntBuffer().get(items, 0, count);
        buffer.position(buffer.position() + count * Integer.BYTES);
        this.size = newSize;
    }
}
