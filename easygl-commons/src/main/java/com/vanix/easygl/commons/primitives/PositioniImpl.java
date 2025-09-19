package com.vanix.easygl.commons.primitives;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class PositioniImpl implements Positioni<PositioniImpl> {

    protected int x;
    protected int y;

    public PositioniImpl() {
    }

    public PositioniImpl(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public PositioniImpl setX(int x) {
        this.x = x;
        return this;
    }

    @Override
    public PositioniImpl setY(int y) {
        this.y = y;
        return this;
    }
}
