package com.vanix.easygl.window.input.event;

import com.vanix.easygl.window.input.Mouse;

public class MouseMoveEvent extends MouseEvent {
    private final double x;
    private final double y;

    public MouseMoveEvent(Mouse device, double x, double y) {
        super(device, null, null);
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }
}
