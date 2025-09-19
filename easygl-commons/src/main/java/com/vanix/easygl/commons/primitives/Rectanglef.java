package com.vanix.easygl.commons.primitives;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public interface Rectanglef<T extends Rectanglef<T>> extends Dimensionf<T>, Positionf<T>, Rectanglefc<T> {

    default T setRectangle(float x, float y, float width, float height) {
        return setX(x).setY(y).setWidth(width).setHeight(height);
    }
  
    default T setRectangle(Rectanglefc<?> rectangle){
        return setX(rectangle.getX()).setY(rectangle.getY()).setWidth(rectangle.getWidth()).setHeight(rectangle.getHeight());
    }

    @Override
    default T set(int index, ByteBuffer buffer) {
        return setX(buffer.getFloat(index))
                .setY(buffer.getFloat(index += Float.BYTES))
                .setWidth(buffer.getFloat(index += Float.BYTES))
                .setHeight(buffer.getFloat(index + Float.BYTES));
    }

    @Override
    default T set(float[] in, int offset) {
        return setX(in[offset]).setY(in[offset + 1]).setWidth(in[offset + 2]).setHeight(in[offset + 3]);
    }

    @Override
    default T set(int index, FloatBuffer buffer) {
        return setX(buffer.get(index))
                .setY(buffer.get(index + 1))
                .setWidth(buffer.get(index + 2))
                .setHeight(buffer.get(index + 3));
    }

    static Rectanglef<?> of() {
        return new RectanglefImpl();
    }

    static Rectanglef<?> of(float x, float y, float width, float height) {
        return new RectanglefImpl(x, y, width, height);
    }
}
