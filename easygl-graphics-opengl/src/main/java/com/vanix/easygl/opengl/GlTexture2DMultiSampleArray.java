package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.InternalPixelFormat;
import com.vanix.easygl.graphics.Texture2DMultiSampleArray;

public class GlTexture2DMultiSampleArray extends AbstractTexture<Texture2DMultiSampleArray> implements Texture2DMultiSampleArray,
        GlTextureOps.Parameters<Texture2DMultiSampleArray>,
        GlTextureOps.CopyImageSubData<Texture2DMultiSampleArray>,
        GlTextureOps.MakeView<Texture2DMultiSampleArray> {

    public GlTexture2DMultiSampleArray() {
        this(GLX.glGenTextures());
    }

    public GlTexture2DMultiSampleArray(int handle) {
        super(handle, Target);
    }

    private GlTexture2DMultiSampleArray(int handle, TexTarget<Texture2DMultiSampleArray> target) {
        super(handle, target);
    }

    @Override
    public Texture2DMultiSampleArray proxy() {
        return new GlTexture2DMultiSampleArray(intHandle(), target.proxy(GLX.GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY));
    }

    @Override
    public Texture2DMultiSampleArray establish(int samples, InternalPixelFormat pixelFormat, int width, int height, int depth, boolean fixedSampleLocations) {
        assertBinding();
        GLX.glTexImage3DMultisample(target.value(true), samples, pixelFormat.value(), width, height, depth, fixedSampleLocations);
        GLX.checkError();
        return this;
    }

    @Override
    public Texture2DMultiSampleArray setStorage(int samples, InternalPixelFormat pixelFormat, int width, int height, int depth, boolean fixedSampleLocations) {
        assertBinding();
        GLX.glTexStorage3DMultisample(target.value(true), samples, pixelFormat.value(), width, height, depth, fixedSampleLocations);
        GLX.checkError();
        return this;
    }
}
