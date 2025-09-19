package com.vanix.easygl.graphics.draw;

import com.vanix.easygl.graphics.DrawMode;

public interface Drawing<S> {
    void draw();

    S source();

    DrawMode mode();

    interface Builder<S, D extends Drawing<S>> {
        D build();
    }
}
