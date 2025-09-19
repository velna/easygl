package com.vanix.easygl.commons.primitives;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public interface Rectanglel<T extends Rectanglel<T>> extends Dimensionl<T>, Positionl<T>, Rectanglelc<T> {

    default T setRectangle(long x, long y, long width, long height) {
        return setX(x).setY(y).setWidth(width).setHeight(height);
    }
  
    default T setRectangle(Rectanglelc<?> rectangle){
        return setX(rectangle.getX()).setY(rectangle.getY()).setWidth(rectangle.getWidth()).setHeight(rectangle.getHeight());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setX(buffer.getLong(index))
                .setY(buffer.getLong(index += Long.BYTES))
                .setWidth(buffer.getLong(index += Long.BYTES))
                .setHeight(buffer.getLong(index + Long.BYTES));
    }

    @Override
    default T set(long[] in, int offset) {
        return setX(in[offset]).setY(in[offset + 1]).setWidth(in[offset + 2]).setHeight(in[offset + 3]);
    }

    @Override
    default T set(int index, LongBuffer buffer) {
        return setX(buffer.get(index))
                .setY(buffer.get(index + 1))
                .setWidth(buffer.get(index + 2))
                .setHeight(buffer.get(index + 3));
    }

    static Rectanglel<?> of() {
        return new RectanglelImpl();
    }

    static Rectanglel<?> of(long x, long y, long width, long height) {
        return new RectanglelImpl(x, y, width, height);
    }
}
