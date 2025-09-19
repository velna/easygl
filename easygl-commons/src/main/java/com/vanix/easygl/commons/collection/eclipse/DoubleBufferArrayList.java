package com.vanix.easygl.commons.collection.eclipse;

import com.vanix.easygl.commons.bufferio.BufferList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class DoubleBufferArrayList extends DoubleArrayList implements BufferList {
    public DoubleBufferArrayList() {
    }

    public DoubleBufferArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public DoubleBufferArrayList(int initialCapacity, double fillValue) {
        super(initialCapacity);
        if (fillValue != 0) {
            Arrays.fill(items, fillValue);
        }
        size = initialCapacity;
    }

    public DoubleBufferArrayList(double... array) {
        super(array);
    }

    @Override
    public int saveInto(ByteBuffer buffer) {
        var localBuffer = buffer.asDoubleBuffer();
        int remaining = localBuffer.remaining();
        if (size == 0 || remaining == 0) {
            return 0;
        }
        int n = Math.min(size, remaining);
        localBuffer.put(items, 0, n);
        buffer.position(buffer.position() + n * Double.BYTES);
        return n;
    }

    @Override
    public void loadFrom(ByteBuffer buffer, int count) {
        int newSize = Math.max(this.size, count);
        this.ensureCapacity(newSize);
        buffer.asDoubleBuffer().get(items, 0, count);
        buffer.position(buffer.position() + count * Double.BYTES);
        this.size = newSize;
    }
}
