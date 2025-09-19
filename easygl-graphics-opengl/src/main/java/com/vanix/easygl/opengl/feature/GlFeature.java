package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.feature.GraphicsFeature;

public abstract class GlFeature<T extends GraphicsFeature<T>> implements GraphicsFeature<T> {
    private final int capability;
    private final Graphics graphics;

    public GlFeature(int capability, Graphics graphics) {
        this.capability = capability;
        this.graphics = graphics;
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    @Override
    public boolean isEnabled() {
        return GLX.glIsEnabled(capability);
    }

    @Override
    public T enable() {
        GLX.glEnable(capability);
        return self();
    }

    @Override
    public T disable() {
        GLX.glDisable(capability);
        return self();
    }

    @Override
    public Graphics then() {
        return graphics;
    }
}
