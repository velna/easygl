package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.graphics.CompareFunction;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.feature.DepthTest;

public class GlDepthTest extends GlFeature<DepthTest> implements DepthTest {
    public GlDepthTest(Graphics graphics) {
        super(GLX.GL_DEPTH_TEST, graphics);
    }

    public DepthTest setFunction(CompareFunction function) {
        GLX.glDepthFunc(function.value());
        return this;
    }

}