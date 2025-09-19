package com.vanix.easygl.commons.primitives;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class PositiondImpl implements Positiond<PositiondImpl> {

    protected double x;
    protected double y;

    public PositiondImpl() {
    }

    public PositiondImpl(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public PositiondImpl setX(double x) {
        this.x = x;
        return this;
    }

    @Override
    public PositiondImpl setY(double y) {
        this.y = y;
        return this;
    }
}
