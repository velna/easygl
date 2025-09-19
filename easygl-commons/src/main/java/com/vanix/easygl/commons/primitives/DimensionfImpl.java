package com.vanix.easygl.commons.primitives;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class DimensionfImpl implements Dimensionf<DimensionfImpl> {
    protected float width;
    protected float height;

    public DimensionfImpl() {
    }

    public DimensionfImpl(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public DimensionfImpl setWidth(float width) {
        this.width = width;
        return this;
    }

    @Override
    public DimensionfImpl setHeight(float height) {
        this.height = height;
        return this;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
