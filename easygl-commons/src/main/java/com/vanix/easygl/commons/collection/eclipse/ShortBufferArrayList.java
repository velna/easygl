package com.vanix.easygl.commons.collection.eclipse;

import com.vanix.easygl.commons.bufferio.BufferList;
import org.eclipse.collections.impl.list.mutable.primitive.ShortArrayList;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ShortBufferArrayList extends ShortArrayList implements BufferList {
    public ShortBufferArrayList() {
    }

    public ShortBufferArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public ShortBufferArrayList(int initialCapacity, short fillValue) {
        super(initialCapacity);
        if (fillValue != 0) {
            Arrays.fill(items, fillValue);
        }
        size = initialCapacity;
    }

    public ShortBufferArrayList(short... array) {
        super(array);
    }

    @Override
    public int saveInto(ByteBuffer buffer) {
        var localBuffer = buffer.asShortBuffer();
        int remaining = localBuffer.remaining();
        if (size == 0 || remaining == 0) {
            return 0;
        }
        int n = Math.min(size, remaining);
        localBuffer.put(items, 0, n);
        buffer.position(buffer.position() + n * Short.BYTES);
        return n;
    }

    @Override
    public void loadFrom(ByteBuffer buffer, int count) {
        int newSize = Math.max(this.size, count);
        this.ensureCapacity(newSize);
        buffer.asShortBuffer().get(items, 0, count);
        buffer.position(buffer.position() + count * Short.BYTES);
        this.size = newSize;
    }
}
