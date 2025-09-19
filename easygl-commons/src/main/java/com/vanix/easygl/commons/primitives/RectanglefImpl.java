package com.vanix.easygl.commons.primitives;

import lombok.Getter;

@Getter
public class RectanglefImpl implements Rectanglef<RectanglefImpl> {
    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public RectanglefImpl() {
    }

    public RectanglefImpl(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public RectanglefImpl setX(float x) {
        this.x = x;
        return this;
    }

    @Override
    public RectanglefImpl setY(float y) {
        this.y = y;
        return this;
    }

    @Override
    public RectanglefImpl setWidth(float width) {
        this.width = width;
        return this;
    }

    @Override
    public RectanglefImpl setHeight(float height) {
        this.height = height;
        return this;
    }
}
