package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.core.AbstractMultiTargetBindable;
import com.vanix.easygl.graphics.*;
import org.joml.Vector4f;
import org.joml.Vector4i;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.function.IntConsumer;

public abstract class GlBaseFrameBuffer<T extends BaseFrameBuffer<T>>
        extends AbstractMultiTargetBindable<BaseFrameBuffer.Target<T>, T>
        implements BaseFrameBuffer<T> {
    public GlBaseFrameBuffer(int handle, IntConsumer closeFunction) {
        super(handle, closeFunction);
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    @Override
    public T attach(Target<T> target, FrameInnerBuffer.Attachment attachment, RenderBuffer renderBuffer) {
        GLX.glFramebufferRenderbuffer(target.value(), attachment.value(), GLX.GL_RENDERBUFFER, renderBuffer.intHandle());
        GLX.checkError();
        return self();
    }

    @Override
    public T detachRenderBuffer(Target<T> target, FrameInnerBuffer.Attachment attachment) {
        GLX.glFramebufferRenderbuffer(target.value(), attachment.value(), GLX.GL_RENDERBUFFER, 0);
        return self();
    }

    @Override
    public T clear(FrameInnerBuffer.Mask attr) {
        GLX.glClear(attr.value());
        return self();
    }

    @Override
    public T setClearColor(float red, float green, float blue, float alpha) {
        GLX.glClearColor(red, blue, green, alpha);
        return self();
    }

    @Override
    public Color getClearColor() {
        float[] data = new float[4];
        GLX.glGetFloatv(GLX.GL_COLOR_CLEAR_VALUE, data);
        return new Color(data[0], data[1], data[2], data[3]);
    }

    @Override
    public T setClearDepth(float depth) {
        GLX.glClearDepth(depth);
        return self();
    }

    @Override
    public float getClearDepth() {
        return GLX.glGetFloat(GLX.GL_DEPTH_CLEAR_VALUE);
    }

    @Override
    public T setClearStencil(int s) {
        GLX.glClearStencil(s);
        return self();
    }

    @Override
    public int getClearStencil() {
        return GLX.glGetInteger(GLX.GL_STENCIL_CLEAR_VALUE);
    }

    @Override
    public T selectDrawBuffers(FrameInnerBuffer.MultiSelectableDrawBuffer... drawBuffers) {
        int[] values = new int[drawBuffers.length];
        for (int i = 0; i < drawBuffers.length; i++) {
            values[i] = drawBuffers[i].value();
        }
        GLX.glDrawBuffers(values);
        return self();
    }

    @Override
    public T blit(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, FrameInnerBuffer.Mask buffers, MagFilter filter) {
        GLX.glBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, buffers.value(), filter.value());
        return self();
    }

    @Override
    public T clearColorBuffer(FrameInnerBuffer.DrawBuffer drawBuffer, Vector4f color) {
        GLX.glClearBufferfv(GLX.GL_COLOR, drawBuffer.index(), new float[]{color.x, color.y, color.z, color.w});
        return self();
    }

    @Override
    public T clearColorBuffer(FrameInnerBuffer.DrawBuffer drawBuffer, Vector4i color) {
        GLX.glClearBufferiv(GLX.GL_COLOR, drawBuffer.index(), new int[]{color.x, color.y, color.z, color.w});
        return self();
    }

    @Override
    public T clearColorBufferUnsigned(FrameInnerBuffer.DrawBuffer drawBuffer, Vector4i color) {
        GLX.glClearBufferuiv(GLX.GL_COLOR, drawBuffer.index(), new int[]{color.x, color.y, color.z, color.w});
        return self();
    }

    @Override
    public T clearDepthBuffer(float value) {
        GLX.glClearBufferfv(GLX.GL_DEPTH, 0, new float[]{value});
        return self();
    }

    @Override
    public T clearStencilBuffer(int value) {
        GLX.glClearBufferiv(GLX.GL_STENCIL, 0, new int[]{value});
        return self();
    }

    @Override
    public T clearDepthAndStencilBuffer(float depthValue, int stencilValue) {
        GLX.glClearBufferfi(GLX.GL_DEPTH_STENCIL, 0, depthValue, stencilValue);
        return self();
    }

    @Override
    public T setColorMask(boolean read, boolean green, boolean blue, boolean alpha) {
        GLX.glColorMask(read, green, blue, alpha);
        return self();
    }

    @Override
    public T setColorMask(FrameInnerBuffer.DrawBuffer drawBuffer, boolean read, boolean green, boolean blue, boolean alpha) {
        GLX.glColorMaski(drawBuffer.value(), read, green, blue, alpha);
        return self();
    }

    @Override
    public T setDepthMask(boolean flag) {
        GLX.glDepthMask(flag);
        return self();
    }

    @Override
    public T setStencilMask(int mask) {
        GLX.glStencilMask(mask);
        return self();
    }

    @Override
    public T setStencilMask(Face face, int mask) {
        GLX.glStencilMaskSeparate(face.value(), mask);
        return self();
    }

    @Override
    public T selectReadBuffer(FrameInnerBuffer.ReadBuffer readBuffer) {
        GLX.glReadBuffer(readBuffer.value());
        return self();
    }

    @Override
    public FrameInnerBuffer.ReadBuffer getReadBuffer() {
        return Cache.FrameReadBuffer.get(GLX.glGetInteger(GLX.GL_READ_BUFFER));
    }

    @Override
    public T setClampColor(boolean clamp) {
        GLX.glClampColor(GLX.GL_CLAMP_READ_COLOR, clamp ? GLX.GL_TRUE : GLX.GL_FALSE);
        return self();
    }

    @Override
    public boolean isClampColor() {
        return GLX.glGetBoolean(GLX.GL_CLAMP_READ_COLOR);
    }

    @Override
    public T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, ByteBuffer data) {
        GLX.glReadPixels(x, y, width, height, format.value(), dataType.value(), data);
        return self();
    }

    @Override
    public T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, short[] data) {
        GLX.glReadPixels(x, y, width, height, format.value(), dataType.value(), data);
        return self();
    }

    @Override
    public T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, ShortBuffer data) {
        GLX.glReadPixels(x, y, width, height, format.value(), dataType.value(), data);
        return self();
    }

    @Override
    public T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, int[] data) {
        GLX.glReadPixels(x, y, width, height, format.value(), dataType.value(), data);
        return self();
    }

    @Override
    public T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, IntBuffer data) {
        GLX.glReadPixels(x, y, width, height, format.value(), dataType.value(), data);
        return self();
    }

    @Override
    public T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, float[] data) {
        GLX.glReadPixels(x, y, width, height, format.value(), dataType.value(), data);
        return self();
    }

    @Override
    public T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, FloatBuffer data) {
        GLX.glReadPixels(x, y, width, height, format.value(), dataType.value(), data);
        return self();
    }

    @Override
    public Status getStatus() {
        return Cache.FrameBufferStatus.get(GLX.glCheckFramebufferStatus(target.value()));
    }

    @Override
    public T checkStatus() throws GraphicsException {
        int status = GLX.glCheckFramebufferStatus(target.value());
        if (status != GLX.GL_FRAMEBUFFER_COMPLETE) {
            throw new GraphicsException(Cache.FrameBufferStatus.get(status).name());
        }
        return self();
    }

    @Override
    public boolean isDoubleBufferSupported() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_DOUBLEBUFFER) == GLX.GL_TRUE;
    }

    @Override
    public PixelFormat getPreferredPixelFormat() {
        assertBinding();
        return Cache.PixelFormat.get(GLX.glGetFramebufferParameteri(target.value(), GLX.GL_IMPLEMENTATION_COLOR_READ_FORMAT));
    }

    @Override
    public DataType getPreferredPixelDataType() {
        assertBinding();
        return Cache.DataType.get(GLX.glGetFramebufferParameteri(target.value(), GLX.GL_IMPLEMENTATION_COLOR_READ_TYPE));
    }

    @Override
    public int getCoverageMaskSize() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_SAMPLES);
    }

    @Override
    public int numSampleBuffers() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_SAMPLE_BUFFERS);
    }

    @Override
    public boolean isStereoBufferSupported() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_STEREO) == GLX.GL_TRUE;
    }
}
