package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.commons.primitives.Rectanglei;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.MultiTargetBindable;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;
import org.joml.Vector4f;
import org.joml.Vector4i;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public interface BaseFrameBuffer<T extends BaseFrameBuffer<T>> extends MultiTargetBindable<BaseFrameBuffer.Target<T>, T> {

    default T bindFrame() {
        return bind(Target.frame());
    }

    default T bindDraw() {
        return bind(Target.draw());
    }

    default T bindRead() {
        return bind(Target.read());
    }

    @Support(since = Version.GL30)
    T attach(Target<T> target, FrameInnerBuffer.Attachment attachment, RenderBuffer renderBuffer);

    @Support(since = Version.GL30)
    default T attach(FrameInnerBuffer.Attachment attachment, RenderBuffer renderBuffer) {
        return attach(target(), attachment, renderBuffer);
    }

    @Support(since = Version.GL30)
    T detachRenderBuffer(Target<T> target, FrameInnerBuffer.Attachment attachment);

    @Support(since = Version.GL30)
    default T detachRenderBuffer(FrameInnerBuffer.Attachment attachment) {
        return detachRenderBuffer(target(), attachment);
    }

    @Support(since = Version.GL30)
    T blit(int srcX0, int srcY0, int srcX1, int srcY1,
           int dstX0, int dstY0, int dstX1, int dstY1,
           FrameInnerBuffer.Mask buffers, MagFilter filter);

    default T blit(int x0, int y0, int x1, int y1,
                   FrameInnerBuffer.Mask buffers, MagFilter filter) {
        return blit(x0, y0, x1, y1, x0, y0, x1, y1, buffers, filter);
    }

    @Support(since = Version.GL30)
    default T blit(Rectanglei<?> src, Rectanglei<?> dst, FrameInnerBuffer.Mask buffers, MagFilter filter) {
        return blit(src.getX(), src.getY(), src.getX() + src.getWidth(), src.getY() + src.getHeight(),
                dst.getX(), dst.getY(), dst.getX() + dst.getWidth(), dst.getY() + dst.getHeight(),
                buffers, filter);
    }

    @Support(since = Version.GL30)
    default T blit(Rectanglei<?> rect, FrameInnerBuffer.Mask buffers, MagFilter filter) {
        return blit(rect, rect, buffers, filter);
    }

    T selectDrawBuffers(FrameInnerBuffer.MultiSelectableDrawBuffer... drawBuffers);

    Status getStatus();

    T checkStatus() throws GraphicsException;

    //region Parameters
    @Support(since = Version.GL43)
    boolean isDoubleBufferSupported();

    @Support(since = Version.GL43)
    PixelFormat getPreferredPixelFormat();

    @Support(since = Version.GL43)
    DataType getPreferredPixelDataType();

    @Support(since = Version.GL43)
    int getCoverageMaskSize();

    @Support(since = Version.GL43)
    int numSampleBuffers();

    @Support(since = Version.GL43)
    boolean isStereoBufferSupported();
    //endregion

    //region Reading and Copying Pixels
    T selectReadBuffer(FrameInnerBuffer.ReadBuffer readBuffer);

    FrameInnerBuffer.ReadBuffer getReadBuffer();

    T setClampColor(boolean clamp);

    boolean isClampColor();

    T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, ByteBuffer data);

    T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, short[] data);

    T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, ShortBuffer data);

    T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, int[] data);

    T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, IntBuffer data);

    T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, float[] data);

    T readPixels(int x, int y, int width, int height, PixelFormat format, DataType dataType, FloatBuffer data);

    default T readPixels(Rectanglei<?> rect, PixelFormat format, DataType dataType, ByteBuffer data) {
        return readPixels(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), format, dataType, data);
    }

    default T readPixels(Rectanglei<?> rect, PixelFormat format, DataType dataType, short[] data) {
        return readPixels(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), format, dataType, data);
    }

    default T readPixels(Rectanglei<?> rect, PixelFormat format, DataType dataType, ShortBuffer data) {
        return readPixels(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), format, dataType, data);
    }

    default T readPixels(Rectanglei<?> rect, PixelFormat format, DataType dataType, int[] data) {
        return readPixels(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), format, dataType, data);
    }

    default T readPixels(Rectanglei<?> rect, PixelFormat format, DataType dataType, IntBuffer data) {
        return readPixels(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), format, dataType, data);
    }

    default T readPixels(Rectanglei<?> rect, PixelFormat format, DataType dataType, float[] data) {
        return readPixels(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), format, dataType, data);
    }

    default T readPixels(Rectanglei<?> rect, PixelFormat format, DataType dataType, FloatBuffer data) {
        return readPixels(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), format, dataType, data);
    }
    //endregion

    //region Clearing the Buffers
    T clear(FrameInnerBuffer.Mask mask);

    T setClearColor(float red, float green, float blue, float alpha);

    default T setClearColor(Color color) {
        return setClearColor(color.red(), color.green(), color.blue(), color.alpha());
    }

    Color getClearColor();

    @Support(since = Version.GL30)
    T clearColorBuffer(FrameInnerBuffer.DrawBuffer drawBuffer, Vector4f color);

    @Support(since = Version.GL30)
    T clearColorBuffer(FrameInnerBuffer.DrawBuffer drawBuffer, Vector4i color);

    @Support(since = Version.GL30)
    T clearColorBufferUnsigned(FrameInnerBuffer.DrawBuffer drawBuffer, Vector4i color);

    @Support(since = Version.GL30)
    T clearDepthBuffer(float value);

    @Support(since = Version.GL30)
    T clearStencilBuffer(int value);

    @Support(since = Version.GL30)
    T clearDepthAndStencilBuffer(float depthValue, int stencilValue);

    T setClearDepth(float depth);

    float getClearDepth();

    T setClearStencil(int s);

    int getClearStencil();
    //endregion

    //region Fine Control of Buffer Updates
    T setColorMask(boolean read, boolean green, boolean blue, boolean alpha);

    @Support(since = Version.GL30)
    T setColorMask(FrameInnerBuffer.DrawBuffer drawBuffer, boolean read, boolean green, boolean blue, boolean alpha);

    T setDepthMask(boolean flag);

    T setStencilMask(int mask);

    T setStencilMask(Face face, int mask);
    //endregion


    @SuppressWarnings("unchecked")
    sealed interface Target<T extends BaseFrameBuffer<T>> extends BindTarget<Target<T>, T> permits FrameBufferTarget {
        static <T extends BaseFrameBuffer<T>> Target<T> read() {
            return FrameBufferTarget.Read;
        }

        static <T extends BaseFrameBuffer<T>> Target<T> draw() {
            return FrameBufferTarget.Draw;
        }

        static <T extends BaseFrameBuffer<T>> Target<T> frame() {
            return FrameBufferTarget.Frame;
        }
    }

    enum Status implements IntEnum {
        Complete("FRAMEBUFFER_COMPLETE"),
        Undefined("FRAMEBUFFER_UNDEFINED"),
        IncompleteAttachment("FRAMEBUFFER_INCOMPLETE_ATTACHMENT"),
        IncompleteMissingAttachment("FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT"),
        IncompleteDrawBuffer("FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER"),
        IncompleteReadBuffer("FRAMEBUFFER_INCOMPLETE_READ_BUFFER"),
        Unsupported("FRAMEBUFFER_UNSUPPORTED"),
        IncompleteMultisample("FRAMEBUFFER_INCOMPLETE_MULTISAMPLE"),
        IncompleteLayerTargets("FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS");
        private final int value;

        Status(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }
}
