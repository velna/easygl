package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.InternalPixelFormat;
import com.vanix.easygl.graphics.Texture2DMultiSample;

public class GlTexture2DMultiSample extends AbstractTexture<Texture2DMultiSample> implements Texture2DMultiSample,
        GlTextureOps.Parameters<Texture2DMultiSample>,
        GlTextureOps.CopyImageSubData<Texture2DMultiSample>,
        GlTextureOps.MakeView<Texture2DMultiSample>,
        GlTextureOps.SetStorage2DMultisample<Texture2DMultiSample> {
    public GlTexture2DMultiSample(int handle) {
        super(handle, Target);
    }

    public GlTexture2DMultiSample() {
        this(GLX.glGenTextures());
    }

    private GlTexture2DMultiSample(int handle, TexTarget<Texture2DMultiSample> target) {
        super(handle, target);
    }

    @Override
    public Texture2DMultiSample proxy() {
        return new GlTexture2DMultiSample(intHandle(), target.proxy(GLX.GL_PROXY_TEXTURE_2D_MULTISAMPLE));
    }

    @Override
    public Texture2DMultiSample establish(int samples, InternalPixelFormat pixelFormat, int width, int height, boolean fixedSampleLocations) {
        assertBinding();
        GLX.glTexImage2DMultisample(target.value(true), samples, pixelFormat.value(), width, height, fixedSampleLocations);
        GLX.checkError();
        return this;
    }

    @Override
    public Texture2DMultiSample setStorage(int samples, InternalPixelFormat pixelFormat, int width, int height, boolean fixedSampleLocations) {
        assertBinding();
        GLX.glTextureStorage2DMultisample(target.value(true), samples, pixelFormat.value(), width, height, fixedSampleLocations);
        GLX.checkError();
        return this;
    }
}
