package com.vanix.easygl.commons.collection.eclipse;

import com.vanix.easygl.commons.bufferio.BufferList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class FloatBufferArrayList extends FloatArrayList implements BufferList {
    public FloatBufferArrayList() {
    }

    public FloatBufferArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public FloatBufferArrayList(int initialCapacity, float fillValue) {
        super(initialCapacity);
        if (fillValue != 0) {
            Arrays.fill(items, fillValue);
        }
        size = initialCapacity;
    }

    public FloatBufferArrayList(float... array) {
        super(array);
    }

    @Override
    public int saveInto(ByteBuffer buffer) {
        var localBuffer = buffer.asFloatBuffer();
        int remaining = localBuffer.remaining();
        if (size == 0 || remaining == 0) {
            return 0;
        }
        int n = Math.min(size, remaining);
        localBuffer.put(items, 0, n);
        buffer.position(buffer.position() + n * Float.BYTES);
        return n;
    }

    @Override
    public void loadFrom(ByteBuffer buffer, int count) {
        int newSize = Math.max(this.size, count);
        this.ensureCapacity(newSize);
        buffer.asFloatBuffer().get(items, 0, count);
        buffer.position(buffer.position() + count * Float.BYTES);
        this.size = newSize;
    }
}
