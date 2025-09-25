package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.feature.DepthClamp;
import com.vanix.easygl.opengl.GLX;

public class GlDepthClamp extends GlFeature<DepthClamp> implements DepthClamp {
    public GlDepthClamp(Graphics graphics) {
        super(GLX.GL_DEPTH_CLAMP, graphics);
    }

    @Override
    public DepthClamp setDepthRange(double near, double far) {
        GLX.glDepthRange(near, far);
        return this;
    }

    @Override
    public DepthClamp setDepthRange(float near, float far) {
        GLX.glDepthRangef(near, far);
        return this;
    }

    @Override
    public float[] getDepthRangeFloat() {
        var data = new float[2];
        GLX.glGetFloatv(GLX.GL_DEPTH_RANGE, data);
        return data;
    }

    @Override
    public double[] getDepthRangeDouble() {
        var data = new double[2];
        GLX.glGetDoublev(GLX.GL_DEPTH_RANGE, data);
        return data;
    }

    @Override
    public int[] getDepthRangeIntMapped() {
        var data = new int[2];
        GLX.glGetIntegerv(GLX.GL_DEPTH_RANGE, data);
        return data;
    }

}
