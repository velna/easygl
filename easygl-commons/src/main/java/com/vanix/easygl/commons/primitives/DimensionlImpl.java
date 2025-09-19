package com.vanix.easygl.commons.primitives;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class DimensionlImpl implements Dimensionl<DimensionlImpl> {
    protected long width;
    protected long height;

    public DimensionlImpl() {
    }

    public DimensionlImpl(long width, long height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public DimensionlImpl setWidth(long width) {
        this.width = width;
        return this;
    }

    @Override
    public DimensionlImpl setHeight(long height) {
        this.height = height;
        return this;
    }

    @Override
    public long getWidth() {
        return width;
    }

    @Override
    public long getHeight() {
        return height;
    }
}
