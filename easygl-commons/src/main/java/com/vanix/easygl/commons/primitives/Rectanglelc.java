package com.vanix.easygl.commons.primitives;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public interface Rectanglelc<T extends Rectanglelc<T>> extends Dimensionlc<T>, Positionlc<T> {

    default <R extends Rectanglel<R>> R getRectangleG(R rectangle) {
        return rectangle.setX(getX()).setY(getY()).setWidth(getWidth()).setHeight(getHeight());
    }

    default Rectanglel<?> getRectangle(Rectanglel<?> rectangle) {
        return rectangle.setX(getX()).setY(getY()).setWidth(getWidth()).setHeight(getHeight());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putLong(index, getX())
                .putLong(index += Long.BYTES, getY())
                .putLong(index += Long.BYTES, getWidth())
                .putLong(index + Long.BYTES, getHeight());
    }

    @Override
    default long[] get(long[] out, int offset) {
        out[offset] = getX();
        out[offset + 1] = getY();
        out[offset + 2] = getWidth();
        out[offset + 3] = getHeight();
        return out;
    }

    @Override
    default LongBuffer get(int index, LongBuffer buffer) {
        return buffer
                .put(index, getX())
                .put(index + 1, getY())
                .put(index + 2, getWidth())
                .put(index + 3, getHeight());
    }

    static Rectanglelc<?> of(long x, long y, long width, long height) {
        return new RectanglelImpl(x, y, width, height);
    }
}
