package com.vanix.easygl.commons.primitives;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface Rectanglei<T extends Rectanglei<T>> extends Dimensioni<T>, Positioni<T>, Rectangleic<T> {

    default T setRectangle(int x, int y, int width, int height) {
        return setX(x).setY(y).setWidth(width).setHeight(height);
    }
  
    default T setRectangle(Rectangleic<?> rectangle){
        return setX(rectangle.getX()).setY(rectangle.getY()).setWidth(rectangle.getWidth()).setHeight(rectangle.getHeight());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setX(buffer.getInt(index))
                .setY(buffer.getInt(index += Integer.BYTES))
                .setWidth(buffer.getInt(index += Integer.BYTES))
                .setHeight(buffer.getInt(index + Integer.BYTES));
    }

    @Override
    default T set(int[] in, int offset) {
        return setX(in[offset]).setY(in[offset + 1]).setWidth(in[offset + 2]).setHeight(in[offset + 3]);
    }

    @Override
    default T set(int index, IntBuffer buffer) {
        return setX(buffer.get(index))
                .setY(buffer.get(index + 1))
                .setWidth(buffer.get(index + 2))
                .setHeight(buffer.get(index + 3));
    }

    static Rectanglei<?> of() {
        return new RectangleiImpl();
    }

    static Rectanglei<?> of(int x, int y, int width, int height) {
        return new RectangleiImpl(x, y, width, height);
    }
}
