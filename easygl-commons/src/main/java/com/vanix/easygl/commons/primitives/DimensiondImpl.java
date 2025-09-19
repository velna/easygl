package com.vanix.easygl.commons.primitives;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class DimensiondImpl implements Dimensiond<DimensiondImpl> {
    protected double width;
    protected double height;

    public DimensiondImpl() {
    }

    public DimensiondImpl(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public DimensiondImpl setWidth(double width) {
        this.width = width;
        return this;
    }

    @Override
    public DimensiondImpl setHeight(double height) {
        this.height = height;
        return this;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }
}
