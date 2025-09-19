package com.vanix.easygl.commons.primitives;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public interface Rectanglefc<T extends Rectanglefc<T>> extends Dimensionfc<T>, Positionfc<T> {

    default <R extends Rectanglef<R>> R getRectangleG(R rectangle) {
        return rectangle.setX(getX()).setY(getY()).setWidth(getWidth()).setHeight(getHeight());
    }

    default Rectanglef<?> getRectangle(Rectanglef<?> rectangle) {
        return rectangle.setX(getX()).setY(getY()).setWidth(getWidth()).setHeight(getHeight());
    }

    @Override
    default ByteBuffer get(int index, ByteBuffer buffer) {
        return buffer.putFloat(index, getX())
                .putFloat(index += Float.BYTES, getY())
                .putFloat(index += Float.BYTES, getWidth())
                .putFloat(index + Float.BYTES, getHeight());
    }

    @Override
    default float[] get(float[] out, int offset) {
        out[offset] = getX();
        out[offset + 1] = getY();
        out[offset + 2] = getWidth();
        out[offset + 3] = getHeight();
        return out;
    }

    @Override
    default FloatBuffer get(int index, FloatBuffer buffer) {
        return buffer
                .put(index, getX())
                .put(index + 1, getY())
                .put(index + 2, getWidth())
                .put(index + 3, getHeight());
    }

    static Rectanglefc<?> of(float x, float y, float width, float height) {
        return new RectanglefImpl(x, y, width, height);
    }
}
