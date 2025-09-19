package com.vanix.easygl.application;

import com.vanix.easygl.commons.value.DoubleValue;
import com.vanix.easygl.window.Window;
import com.vanix.easygl.graphics.Graphics;

public class DefaultRenderContext implements RenderContext {

    private final Window window;
    private final Graphics graphics;

    private final DoubleValue tickDelta;

    public DefaultRenderContext(Window window, Graphics graphics, DoubleValue tickDelta) {
        super();
        this.window = window;
        this.graphics = graphics;
        this.tickDelta = tickDelta;
    }

    @Override
    public Window window() {
        return window;
    }

    @Override
    public Graphics graphics() {
        return graphics;
    }

    @Override
    public double tickDelta() {
        return tickDelta.get();
    }

}
