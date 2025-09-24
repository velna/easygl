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


    @Override
    public FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture1D texture, int level) {
        assertBinding();
        GLX.glFramebufferTexture1D(target.value(), attachment.value(), texture.target().value(), texture.intHandle(), level);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture2D texture, int level) {
        assertBinding();
        GLX.glFramebufferTexture2D(target.value(), attachment.value(), texture.target().value(), texture.intHandle(), level);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer attach(FrameInnerBuffer.Attachment attachment, TextureRectangle texture) {
        assertBinding();
        GLX.glFramebufferTexture2D(target.value(), attachment.value(), texture.target().value(), texture.intHandle(), 0);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture2DMultiSample texture) {
        assertBinding();
        GLX.glFramebufferTexture2D(target.value(), attachment.value(), texture.target().value(), texture.intHandle(), 0);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer attach(FrameInnerBuffer.Attachment attachment, TextureCubeMap.Face texture, int level) {
        assertBinding();
        GLX.glFramebufferTexture2D(target.value(), attachment.value(), texture.target().value(), texture.cubeMap().intHandle(), level);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture3D texture, int level, int layer) {
        assertBinding();
        GLX.glFramebufferTexture3D(target.value(), attachment.value(), texture.target().value(), texture.intHandle(), level, layer);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer detach(FrameInnerBuffer.Attachment attachment, Texture.TexTarget<?> textureTarget, int level) {
        assertBinding();
        int targetValue = textureTarget.value();
        if (targetValue == Texture1D.Target.value()) {
            GLX.glFramebufferTexture1D(target.value(), attachment.value(), target().value(), 0, level);
        } else if (targetValue == Texture2D.Target.value()
                || targetValue == TextureRectangle.Target.value()
                || targetValue == Texture2DMultiSample.Target.value()
                || targetValue == TextureRectangle.Target.value()) {
            GLX.glFramebufferTexture2D(target.value(), attachment.value(), target().value(), 0, level);
        }
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer detachCubeMap(FrameInnerBuffer.Attachment attachment, TextureCubeMap.FaceTarget target, int level) {
        assertBinding();
        GLX.glFramebufferTexture2D(target.value(), attachment.value(), target().value(), 0, level);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer detach3D(FrameInnerBuffer.Attachment attachment, int level, int layer) {
        assertBinding();
        GLX.glFramebufferTexture3D(target.value(), attachment.value(), Texture3D.Target.value(), 0, level, layer);
        GLX.checkError();
        return this;
    }

    private FrameBuffer attachLayer0(FrameInnerBuffer.Attachment attachment, Texture<?> texture, int level, int layer) {
        assertBinding();
        GLX.glFramebufferTextureLayer(target.value(), attachment.value(), texture.intHandle(), level, layer);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer attachLayer(FrameInnerBuffer.Attachment attachment, Texture1DArray texture, int level, int layer) {
        return attachLayer0(attachment, texture, level, layer);
    }

    @Override
    public FrameBuffer attachLayer(FrameInnerBuffer.Attachment attachment, Texture2DArray texture, int level, int layer) {
        return attachLayer0(attachment, texture, level, layer);
    }

    @Override
    public FrameBuffer attachLayer(FrameInnerBuffer.Attachment attachment, TextureCubeMapArray texture, int level, int layer) {
        return attachLayer0(attachment, texture, level, layer);
    }

    @Override
    public FrameBuffer attachLayer(FrameInnerBuffer.Attachment attachment, Texture2DMultiSampleArray texture, int level, int layer) {
        return attachLayer0(attachment, texture, level, layer);
    }

    @Override
    public FrameBuffer attachLayer(FrameInnerBuffer.Attachment attachment, Texture3D texture, int level, int layer) {
        return attachLayer0(attachment, texture, level, layer);
    }

    @Override
    public FrameBuffer detachLayer(FrameInnerBuffer.Attachment attachment, int level, int layer) {
        assertBinding();
        GLX.glFramebufferTextureLayer(target.value(), attachment.value(), 0, level, layer);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer attachLayered(FrameInnerBuffer.Attachment attachment, Texture<?> texture, int level) {
        assertBinding();
        GLX.glFramebufferTexture(target.value(), attachment.value(), texture.intHandle(), level);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer detachLayered(FrameInnerBuffer.Attachment attachment, int level) {
        assertBinding();
        GLX.glFramebufferTexture(target.value(), attachment.value(), 0, level);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer invalidate(FrameInnerBuffer.Attachment attachment) {
        assertBinding();
        GLX.glInvalidateFramebuffer(target.value(), attachment.value());
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer invalidate(int x, int y, int width, int height, FrameInnerBuffer.Attachment attachment) {
        assertBinding();
        GLX.glInvalidateSubFramebuffer(target.value(), attachment.value(), x, y, width, height);
        GLX.checkError();
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
