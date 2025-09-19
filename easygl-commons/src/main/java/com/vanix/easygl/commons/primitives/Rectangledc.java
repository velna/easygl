package com.vanix.easygl.commons.primitives;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

public interface Rectangledc<T extends Rectangledc<T>> extends Dimensiondc<T>, Positiondc<T> {

    default <R extends Rectangled<R>> R getRectangleG(R rectangle) {
        return rectangle.setX(getX()).setY(getY()).setWidth(getWidth()).setHeight(getHeight());
    }

    default Rectangled<?> getRectangle(Rectangled<?> rectangle) {
        return rectangle.setX(getX()).setY(getY()).setWidth(getWidth()).setHeight(getHeight());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putDouble(index, getX())
                .putDouble(index += Double.BYTES, getY())
                .putDouble(index += Double.BYTES, getWidth())
                .putDouble(index + Double.BYTES, getHeight());
    }

    @Override
    default double[] get(double[] out, int offset) {
        out[offset] = getX();
        out[offset + 1] = getY();
        out[offset + 2] = getWidth();
        out[offset + 3] = getHeight();
        return out;
    }

    @Override
    default DoubleBuffer get(int index, DoubleBuffer buffer) {
        return buffer
                .put(index, getX())
                .put(index + 1, getY())
                .put(index + 2, getWidth())
                .put(index + 3, getHeight());
    }

    static Rectangledc<?> of(double x, double y, double width, double height) {
        return new RectangledImpl(x, y, width, height);
    }
}
