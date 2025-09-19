package com.vanix.easygl.core;

import com.vanix.easygl.commons.util.ExtendedIterable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class HandleArray<T extends Handle> implements Closeable, ExtendedIterable<T> {
    private final Consumer<int[]> closeFunction;
    protected final int[] handles;
    private final List<T> list;

    public HandleArray(List<T> list, int[] handles, Consumer<int[]> closeFunction) {
        this.list = list;
        this.handles = handles == null ? list.stream().mapToInt(Handle::intHandle).toArray() : handles;
        this.closeFunction = closeFunction;
    }

    public HandleArray(List<T> list) {
        this(list, null, null);
    }

    public static <T extends Handle> HandleArray<T> of(int n, Supplier<T> factory) {
        List<T> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(factory.get());
        }
        return new HandleArray<>(list);
    }

    public static <T extends Handle> HandleArray<T> initInit(List<T> list, int[] handles, Consumer<int[]> closeFunction) {
        if (handles != null && list.size() != handles.length) {
            throw new IllegalArgumentException();
        }
        int[] handlesArray;
        if (handles != null) {
            handlesArray = handles.clone();
        } else {
            handlesArray = new int[list.size()];
            int i = 0;
            for (var e : list) {
                handlesArray[i++] = e.intHandle();
            }
        }
        return new HandleArray<>(new ArrayList<>(list), handlesArray, closeFunction);
    }

    public T getFirst() {
        return list.getFirst();
    }

    public T getLast() {
        return list.getLast();
    }

    public T get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int[] getHandles() {
        return handles;
    }

    @Override
    public void close() {
        if (closeFunction != null && handles != null) {
            closeFunction.accept(handles);
        } else {
            list.forEach(Closeable::close);
        }
    }

    @Nonnull
    @Override
    public Iterator<T> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(list.iterator());
    }
}
