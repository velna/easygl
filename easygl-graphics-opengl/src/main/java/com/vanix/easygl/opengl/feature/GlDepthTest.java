package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.graphics.CompareFunction;
import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.feature.DepthTest;
import com.vanix.easygl.opengl.Cache;
import com.vanix.easygl.opengl.GLX;

public class GlDepthTest extends GlFeature<DepthTest> implements DepthTest {
    public GlDepthTest(Graphics graphics) {
        super(GLX.GL_DEPTH_TEST, graphics);
    }

    @Override
    public DepthTest setWriteDepthBuffer(boolean enable) {
        GLX.glDepthMask(enable);
        return this;
    }

    @Override
    public boolean isWriteDepthBuffer() {
        return GLX.glGetBoolean(GLX.GL_DEPTH_WRITEMASK);
    }

    public DepthTest setFunction(CompareFunction function) {
        GLX.glDepthFunc(function.value());
        return this;
    }

    @Override
    public CompareFunction getFunction() {
        return Cache.CompareFunction.valueOf(GLX.glGetInteger(GLX.GL_DEPTH_FUNC));
    }
}