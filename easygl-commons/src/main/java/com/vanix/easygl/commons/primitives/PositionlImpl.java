package com.vanix.easygl.commons.primitives;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class PositionlImpl implements Positionl<PositionlImpl> {

    protected long x;
    protected long y;

    public PositionlImpl() {
    }

    public PositionlImpl(long x, long y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public long getX() {
        return x;
    }

    @Override
    public long getY() {
        return y;
    }

    @Override
    public PositionlImpl setX(long x) {
        this.x = x;
        return this;
    }

    @Override
    public PositionlImpl setY(long y) {
        this.y = y;
        return this;
    }
}
