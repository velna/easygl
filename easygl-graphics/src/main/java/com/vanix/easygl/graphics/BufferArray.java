package com.vanix.easygl.graphics;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.Support;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public abstract class BufferArray extends HandleArray<Buffer> {
    public BufferArray(List<Buffer> list, int[] handles, Consumer<int[]> closeFunction) {
        super(list, handles, closeFunction);
    }

    @Support(since = Version.GL44)
    public abstract BufferArray bindBase(int first);

    @Support(since = Version.GL44)
    public abstract BufferArray bindRange(int first, long[] offsets, long[] sizes);

    public BufferArray bindRange(int first, long offset, long size) {
        long[] offsets = new long[size()];
        Arrays.fill(offsets, offset);
        long[] sizes = new long[size()];
        Arrays.fill(sizes, size);
        return bindRange(first, offsets, sizes);
    }
}
