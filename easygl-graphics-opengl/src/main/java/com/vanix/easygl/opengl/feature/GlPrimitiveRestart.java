package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.feature.PrimitiveRestart;
import com.vanix.easygl.opengl.GLX;

public class GlPrimitiveRestart extends GlFeature<PrimitiveRestart> implements PrimitiveRestart {
    public GlPrimitiveRestart(Graphics graphics) {
        super(GLX.GL_PRIMITIVE_RESTART, graphics);
    }

    @Override
    public PrimitiveRestart setRestartIndex(int index) {
        GLX.glPrimitiveRestartIndex(index);
        return this;
    }

    @Override
    public int getRestartIndex() {
        return GLX.glGetInteger(GLX.GL_PRIMITIVE_RESTART_INDEX);
    }
}
