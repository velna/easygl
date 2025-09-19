package com.vanix.easygl.core;

import java.util.Objects;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public abstract class AbstractHandle implements Handle {
    protected final long handle;
    private final LongConsumer closeFunction;
    private final boolean isLong;
    private boolean closed;

    protected AbstractHandle(int handle, IntConsumer closeFunction) {
        this.handle = handle;
        this.closeFunction = h -> closeFunction.accept((int) h);
        this.isLong = false;
    }

    protected AbstractHandle(long handle, LongConsumer closeFunction) {
        this.handle = handle;
        this.closeFunction = closeFunction;
        this.isLong = true;
    }

    @Override
    public void close() {
        if (!closed) {
            closeFunction.accept(handle);
            closed = true;
        }
    }

    @Override
    public long handle() {
        return handle;
    }

    @Override
    public int intHandle() {
        if (isLong) {
            throw new UnsupportedOperationException();
        }
        return (int) handle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractHandle that = (AbstractHandle) o;
        return handle == that.handle && isLong == that.isLong && closed == that.closed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(handle, isLong, closed);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "handle=" + handle +
                ", closed=" + closed +
                '}';
    }
}
