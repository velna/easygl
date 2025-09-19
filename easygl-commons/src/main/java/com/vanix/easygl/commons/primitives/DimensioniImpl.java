package com.vanix.easygl.commons.primitives;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class DimensioniImpl implements Dimensioni<DimensioniImpl> {
    protected int width;
    protected int height;

    public DimensioniImpl() {
    }

    public DimensioniImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public DimensioniImpl setWidth(int width) {
        this.width = width;
        return this;
    }

    @Override
    public DimensioniImpl setHeight(int height) {
        this.height = height;
        return this;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
