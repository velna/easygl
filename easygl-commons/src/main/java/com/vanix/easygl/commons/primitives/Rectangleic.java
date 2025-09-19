package com.vanix.easygl.commons.primitives;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface Rectangleic<T extends Rectangleic<T>> extends Dimensionic<T>, Positionic<T> {

    default <R extends Rectanglei<R>> R getRectangleG(R rectangle) {
        return rectangle.setX(getX()).setY(getY()).setWidth(getWidth()).setHeight(getHeight());
    }

    default Rectanglei<?> getRectangle(Rectanglei<?> rectangle) {
        return rectangle.setX(getX()).setY(getY()).setWidth(getWidth()).setHeight(getHeight());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putInt(index, getX())
                .putInt(index += Integer.BYTES, getY())
                .putInt(index += Integer.BYTES, getWidth())
                .putInt(index + Integer.BYTES, getHeight());
    }

    @Override
    default int[] get(int[] out, int offset) {
        out[offset] = getX();
        out[offset + 1] = getY();
        out[offset + 2] = getWidth();
        out[offset + 3] = getHeight();
        return out;
    }

    @Override
    default IntBuffer get(int index, IntBuffer buffer) {
        return buffer
                .put(index, getX())
                .put(index + 1, getY())
                .put(index + 2, getWidth())
                .put(index + 3, getHeight());
    }

    static Rectangleic<?> of(int x, int y, int width, int height) {
        return new RectangleiImpl(x, y, width, height);
    }
}
