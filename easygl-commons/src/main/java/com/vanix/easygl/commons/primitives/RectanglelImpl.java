package com.vanix.easygl.commons.primitives;

import lombok.Getter;

@Getter
public class RectanglelImpl implements Rectanglel<RectanglelImpl> {
    protected long x;
    protected long y;
    protected long width;
    protected long height;

    public RectanglelImpl() {
    }

    public RectanglelImpl(long x, long y, long width, long height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public RectanglelImpl setX(long x) {
        this.x = x;
        return this;
    }

    @Override
    public RectanglelImpl setY(long y) {
        this.y = y;
        return this;
    }

    @Override
    public RectanglelImpl setWidth(long width) {
        this.width = width;
        return this;
    }

    @Override
    public RectanglelImpl setHeight(long height) {
        this.height = height;
        return this;
    }
}
