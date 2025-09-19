package com.vanix.easygl.commons.primitives;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

public interface Rectangled<T extends Rectangled<T>> extends Dimensiond<T>, Positiond<T>, Rectangledc<T> {

    default T setRectangle(double x, double y, double width, double height) {
        return setX(x).setY(y).setWidth(width).setHeight(height);
    }
  
    default T setRectangle(Rectangledc<?> rectangle){
        return setX(rectangle.getX()).setY(rectangle.getY()).setWidth(rectangle.getWidth()).setHeight(rectangle.getHeight());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setX(buffer.getDouble(index))
                .setY(buffer.getDouble(index += Double.BYTES))
                .setWidth(buffer.getDouble(index += Double.BYTES))
                .setHeight(buffer.getDouble(index + Double.BYTES));
    }

    @Override
    default T set(double[] in, int offset) {
        return setX(in[offset]).setY(in[offset + 1]).setWidth(in[offset + 2]).setHeight(in[offset + 3]);
    }

    @Override
    default T set(int index, DoubleBuffer buffer) {
        return setX(buffer.get(index))
                .setY(buffer.get(index + 1))
                .setWidth(buffer.get(index + 2))
                .setHeight(buffer.get(index + 3));
    }

    static Rectangled<?> of() {
        return new RectangledImpl();
    }

    static Rectangled<?> of(double x, double y, double width, double height) {
        return new RectangledImpl(x, y, width, height);
    }
}
