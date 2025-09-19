package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.Origin;
import com.vanix.easygl.graphics.feature.Clipping;

public class GlClipping extends GlFeature<Clipping> implements Clipping {
    public GlClipping(Graphics graphics) {
        super(GLX.GL_CLIP_DISTANCE0, graphics);
    }

    @Override
    public Clipping enableAt(int index) {
        GLX.glEnable(GLX.GL_CLIP_DISTANCE0 + index);
        return this;
    }

    @Override
    public Clipping disableAt(int index) {
        GLX.glDisable(GLX.GL_CLIP_DISTANCE0 + index);
        return this;
    }

    @Override
    public boolean isEnabledAt(int index) {
        return GLX.glIsEnabled(GLX.GL_CLIP_DISTANCE0 + index);
    }

    @Override
    public Clipping control(Origin origin, DepthMode depthMode) {
        GLX.glClipControl(origin.value(), depthMode.value());
        GLX.checkError();
        return this;
    }
}
