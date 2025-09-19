package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.*;

public class GlFrameBuffer extends GlBaseFrameBuffer<FrameBuffer> implements FrameBuffer {

    protected GlFrameBuffer() {
        this(GLX.glGenFramebuffers());
    }

    protected GlFrameBuffer(int handle) {
        super(handle, GLX::glDeleteFramebuffers);
    }

    @Override
    public FrameBuffer selectDrawBuffer(FrameInnerBuffer.ColorBuffer colorBuffer) {
        GLX.glDrawBuffer(colorBuffer.value());
        return this;
    }

    private FrameBuffer attach0(FrameInnerBuffer.Attachment attachment, Texture<?> texture, int level, int layer) {
        assertBinding();
        GLX.glFramebufferTextureLayer(target.value(), attachment.value(), texture.intHandle(), level, layer);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture1DArray texture, int level, int layer) {
        return attach0(attachment, texture, level, layer);
    }

    @Override
    public FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture2DArray texture, int level, int layer) {
        return attach0(attachment, texture, level, layer);
    }

    @Override
    public FrameBuffer attach(FrameInnerBuffer.Attachment attachment, TextureCubeMapArray texture, int level, int layer) {
        return attach0(attachment, texture, level, layer);
    }

    @Override
    public FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture2DMultiSampleArray texture, int level, int layer) {
        return attach0(attachment, texture, level, layer);
    }

    @Override
    public FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture3D texture, int level, int layer) {
        return attach0(attachment, texture, level, layer);
    }

    @Override
    public FrameBuffer invalidate(Target<FrameBuffer> target, FrameInnerBuffer.Attachment attachment) {
        assertBinding();
        GLX.glInvalidateFramebuffer(target.value(), attachment.value());
        return this;
    }

    @Override
    public FrameBuffer invalidate(Target<FrameBuffer> target, int x, int y, int width, int height, FrameInnerBuffer.Attachment attachment) {
        assertBinding();
        GLX.glInvalidateSubFramebuffer(target.value(), attachment.value(), x, y, width, height);
        return this;
    }

    @Override
    public FrameBuffer setDefaultWidth(int width) {
        assertBinding();
        GLX.glFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_WIDTH, width);
        GLX.checkError();
        return this;
    }

    @Override
    public int getDefaultWidth() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_WIDTH);
    }

    @Override
    public FrameBuffer setDefaultHeight(int height) {
        assertBinding();
        GLX.glFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_HEIGHT, height);
        GLX.checkError();
        return this;
    }

    @Override
    public int getDefaultHeight() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_HEIGHT);
    }

    @Override
    public FrameBuffer setDefaultLayers(int layers) {
        assertBinding();
        GLX.glFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_LAYERS, layers);
        GLX.checkError();
        return this;
    }

    @Override
    public int getDefaultLayers() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_LAYERS);
    }

    @Override
    public FrameBuffer setDefaultSamples(int samples) {
        assertBinding();
        GLX.glFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_SAMPLES, samples);
        GLX.checkError();
        return this;
    }

    @Override
    public int getDefaultSamples() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_SAMPLES);
    }

    @Override
    public FrameBuffer setDefaultFixedSampleLocations(int fixedSampleLocations) {
        assertBinding();
        GLX.glFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS, fixedSampleLocations);
        GLX.checkError();
        return this;
    }

    @Override
    public int getDefaultFixedSampleLocations() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS);
    }
}
