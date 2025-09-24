package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.feature.Multisample;
import com.vanix.easygl.opengl.GLX;

public class GlMultisample extends GlFeature<Multisample> implements Multisample {
    public GlMultisample(Graphics graphics) {
        super(GLX.GL_MULTISAMPLE, graphics);
    }

    @Override
    public Multisample enable(Feature feature) {
        GLX.glEnable(feature.value());
        return this;
    }

    @Override
    public Multisample disable(Feature feature) {
        GLX.glDisable(feature.value());
        return this;
    }

    @Override
    public boolean isEnable(Feature feature) {
        return GLX.glIsEnabled(feature.value());
    }

    @Override
    public Multisample setCoverage(float value, boolean invert) {
        GLX.glSampleCoverage(value, invert);
        return this;
    }

    @Override
    public float getCoverageValue() {
        return GLX.glGetFloat(GLX.GL_SAMPLE_COVERAGE_VALUE);
    }

    @Override
    public boolean isCoverageInvert() {
        return GLX.glGetBoolean(GLX.GL_SAMPLE_COVERAGE_INVERT);
    }

    @Override
    public Multisample setMinSampleShading(float value) {
        GLX.glMinSampleShading(value);
        return this;
    }

    @Override
    public float getMinSampleShading() {
        return GLX.glGetFloat(GLX.GL_MIN_SAMPLE_SHADING_VALUE);
    }

    @Override
    public Multisample setMask(int index, int mask) {
        GLX.glSampleMaski(index, mask);
        return this;
    }

    @Override
    public int getMask(int index) {
        return GLX.glGetIntegeri(GLX.GL_SAMPLE_MASK_VALUE, index);
    }
}
