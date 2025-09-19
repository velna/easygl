package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.graphics.InternalPixelFormat;
import com.vanix.easygl.graphics.RenderBuffer;
import com.vanix.easygl.graphics.Texture;

import java.util.function.IntConsumer;

public class GlRenderBuffer extends AbstractBindable<BindTarget.Default<RenderBuffer>, RenderBuffer> implements RenderBuffer {

    protected GlRenderBuffer() {
        this(GLX.glGenRenderbuffers());
    }

    protected GlRenderBuffer(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteRenderbuffers);
    }

    @Override
    public RenderBuffer storage(InternalPixelFormat internalFormat, int width, int height) {
        assertBinding();
        GLX.glRenderbufferStorage(GLX.GL_RENDERBUFFER, internalFormat.value(), width, height);
        GLX.checkError();
        return this;
    }

    @Override
    public RenderBuffer storageMultiSample(int samples, InternalPixelFormat internalFormat, int width, int height) {
        assertBinding();
        GLX.glRenderbufferStorageMultisample(GLX.GL_RENDERBUFFER, samples, internalFormat.value(), width, height);
        GLX.checkError();
        return this;
    }

    @Override
    public RenderBuffer copyImageSubData(int srcX, int srcY, int srcZ, int width, int height, int depth,
                                         RenderBuffer dst, int dstX, int dstY, int dstZ) {
        GLX.glCopyImageSubData(intHandle(), target.value(), 0, srcX, srcY, srcZ,
                dst.intHandle(), dst.target().value(), 0, dstX, dstY, dstZ,
                width, height, depth);
        GLX.checkError();
        return this;
    }

    @Override
    public RenderBuffer copyImageSubData(int srcX, int srcY, int srcZ, int width, int height, int depth,
                                         Texture<?> dst, int dstMipmapLevel, int dstX, int dstY, int dstZ) {
        GLX.glCopyImageSubData(intHandle(), target.value(), 0, srcX, srcY, srcZ,
                dst.intHandle(), dst.target().value(), dstMipmapLevel, dstX, dstY, dstZ,
                width, height, depth);
        GLX.checkError();
        return this;
    }

    @Override
    public int getWidth() {
        assertBinding();
        return GLX.glGetRenderbufferParameteri(GLX.GL_RENDERBUFFER, GLX.GL_RENDERBUFFER_WIDTH);
    }

    @Override
    public int getHeight() {
        assertBinding();
        return GLX.glGetRenderbufferParameteri(GLX.GL_RENDERBUFFER, GLX.GL_RENDERBUFFER_HEIGHT);
    }

    @Override
    public InternalPixelFormat getInternalFormat() {
        assertBinding();
        return Cache.InternalPixelFormatCache.get(GLX.glGetRenderbufferParameteri(GLX.GL_RENDERBUFFER, GLX.GL_RENDERBUFFER_INTERNAL_FORMAT));
    }

    @Override
    public int getNumSamples() {
        assertBinding();
        return GLX.glGetRenderbufferParameteri(GLX.GL_RENDERBUFFER, GLX.GL_RENDERBUFFER_SAMPLES);
    }

    @Override
    public int getRedBits() {
        assertBinding();
        return GLX.glGetRenderbufferParameteri(GLX.GL_RENDERBUFFER, GLX.GL_RENDERBUFFER_RED_SIZE);
    }

    @Override
    public int getGreenBits() {
        assertBinding();
        return GLX.glGetRenderbufferParameteri(GLX.GL_RENDERBUFFER, GLX.GL_RENDERBUFFER_GREEN_SIZE);
    }

    @Override
    public int getBlueBits() {
        assertBinding();
        return GLX.glGetRenderbufferParameteri(GLX.GL_RENDERBUFFER, GLX.GL_RENDERBUFFER_BLUE_SIZE);
    }

    @Override
    public int getAlphaBits() {
        assertBinding();
        return GLX.glGetRenderbufferParameteri(GLX.GL_RENDERBUFFER, GLX.GL_RENDERBUFFER_ALPHA_SIZE);
    }

    @Override
    public int getDepthBits() {
        assertBinding();
        return GLX.glGetRenderbufferParameteri(GLX.GL_RENDERBUFFER, GLX.GL_RENDERBUFFER_DEPTH_SIZE);
    }

    @Override
    public int getStencilBits() {
        assertBinding();
        return GLX.glGetRenderbufferParameteri(GLX.GL_RENDERBUFFER, GLX.GL_RENDERBUFFER_STENCIL_SIZE);
    }
}
