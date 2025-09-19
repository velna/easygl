package com.vanix.easygl.commons.primitives;

import lombok.Getter;

@Getter
public class RectangleiImpl implements Rectanglei<RectangleiImpl> {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public RectangleiImpl() {
    }

    public RectangleiImpl(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public RectangleiImpl setX(int x) {
        this.x = x;
        return this;
    }

    @Override
    public RectangleiImpl setY(int y) {
        this.y = y;
        return this;
    }

    @Override
    public RectangleiImpl setWidth(int width) {
        this.width = width;
        return this;
    }

    @Override
    public RectangleiImpl setHeight(int height) {
        this.height = height;
        return this;
    }
}
