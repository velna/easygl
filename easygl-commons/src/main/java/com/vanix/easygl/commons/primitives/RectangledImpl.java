package com.vanix.easygl.commons.primitives;

import lombok.Getter;

@Getter
public class RectangledImpl implements Rectangled<RectangledImpl> {
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public RectangledImpl() {
    }

    public RectangledImpl(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public RectangledImpl setX(double x) {
        this.x = x;
        return this;
    }

    @Override
    public RectangledImpl setY(double y) {
        this.y = y;
        return this;
    }

    @Override
    public RectangledImpl setWidth(double width) {
        this.width = width;
        return this;
    }

    @Override
    public RectangledImpl setHeight(double height) {
        this.height = height;
        return this;
    }
}
