package com.vanix.easygl.commons.primitives;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class PositionfImpl implements Positionf<PositionfImpl> {

    protected float x;
    protected float y;

    public PositionfImpl() {
    }

    public PositionfImpl(float x, float y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public PositionfImpl setX(float x) {
        this.x = x;
        return this;
    }

    @Override
    public PositionfImpl setY(float y) {
        this.y = y;
        return this;
    }
}
