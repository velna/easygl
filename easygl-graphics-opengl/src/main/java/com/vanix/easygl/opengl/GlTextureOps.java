package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.InternalPixelFormat;
import com.vanix.easygl.graphics.PixelFormat;
import com.vanix.easygl.graphics.*;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.*;

public interface GlTextureOps<T> {

    void assertBinding() throws IllegalStateException;

    int intHandle();

    T self();

    default int targetValue() {
        return targetValue(false);
    }

    int targetValue(boolean proxy);

    interface ClearImage<T> extends TextureOps.ClearImage<T>, GlTextureOps<T> {
        interface GlClearTexImage<D> {
            void apply(int texture, int level, int format, int dataType, D data);
        }

        private <D> T clearImage(GlClearTexImage<D> glClearTexImage, int level, PixelFormat format, DataType dataType, D data) {
            glClearTexImage.apply(intHandle(), level, format.value(), dataType.value(), data);
            GLX.checkError();
            return self();
        }

        @Override
        default T clearImage(int level, PixelFormat format, DataType dataType, ByteBuffer data) {
            return clearImage(GLX::glClearTexImage, level, format, dataType, data);
        }

        @Override
        default T clearImage(int level, PixelFormat format, DataType dataType, short[] data) {
            return clearImage(GLX::glClearTexImage, level, format, dataType, data);
        }

        @Override
        default T clearImage(int level, PixelFormat format, DataType dataType, int[] data) {
            return clearImage(GLX::glClearTexImage, level, format, dataType, data);
        }

        @Override
        default T clearImage(int level, PixelFormat format, DataType dataType, float[] data) {
            return clearImage(GLX::glClearTexImage, level, format, dataType, data);
        }

        @Override
        default T clearImage(int level, PixelFormat format, DataType dataType, double[] data) {
            return clearImage(GLX::glClearTexImage, level, format, dataType, data);
        }

        @Override
        default T clearImage(int level, PixelFormat format, DataType dataType, ShortBuffer data) {
            return clearImage(GLX::glClearTexImage, level, format, dataType, data);
        }

        @Override
        default T clearImage(int level, PixelFormat format, DataType dataType, IntBuffer data) {
            return clearImage(GLX::glClearTexImage, level, format, dataType, data);
        }

        @Override
        default T clearImage(int level, PixelFormat format, DataType dataType, FloatBuffer data) {
            return clearImage(GLX::glClearTexImage, level, format, dataType, data);
        }

        @Override
        default T clearImage(int level, PixelFormat format, DataType dataType, DoubleBuffer data) {
            return clearImage(GLX::glClearTexImage, level, format, dataType, data);
        }
    }

    interface ClearSubImage<T> extends TextureOps.ClearSubImage<T>, GlTextureOps<T> {
        interface GlClearTexSubImage<D> {
            void apply(int texture, int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, int format, int dataType, D data);
        }

        private <D> T clearSubImage(GlClearTexSubImage<D> glClearTexSubImage, int level,
                                    int xOffset, int yOffset, int zOffset,
                                    int width, int height, int depth,
                                    PixelFormat format, DataType dataType, D data) {
            glClearTexSubImage.apply(intHandle(), level, xOffset, yOffset, zOffset, width, height, depth, format.value(), dataType.value(), data);
            GLX.checkError();
            return self();
        }

        @Override
        default T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                PixelFormat format, DataType dataType, ByteBuffer data) {
            return clearSubImage(GLX::glClearTexSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                PixelFormat format, DataType dataType, short[] data) {
            return clearSubImage(GLX::glClearTexSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                PixelFormat format, DataType dataType, int[] data) {
            return clearSubImage(GLX::glClearTexSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                PixelFormat format, DataType dataType, float[] data) {
            return clearSubImage(GLX::glClearTexSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                PixelFormat format, DataType dataType, double[] data) {
            return clearSubImage(GLX::glClearTexSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                PixelFormat format, DataType dataType, ShortBuffer data) {
            return clearSubImage(GLX::glClearTexSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                PixelFormat format, DataType dataType, IntBuffer data) {
            return clearSubImage(GLX::glClearTexSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                PixelFormat format, DataType dataType, FloatBuffer data) {
            return clearSubImage(GLX::glClearTexSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                PixelFormat format, DataType dataType, DoubleBuffer data) {
            return clearSubImage(GLX::glClearTexSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }
    }

    interface Copy2D<T> extends TextureOps.Copy2D<T>, GlTextureOps<T> {

        @SuppressWarnings("unchecked")
        @Override
        default T copy(int level, InternalPixelFormat internalPixelFormat, int x, int y, int width, int height) {
            assertBinding();
            GLX.glCopyTexImage2D(targetValue(), level, internalPixelFormat.value(), x, y, width, height, 0);
            GLX.checkError();
            return (T) this;
        }

    }

    interface Copy2DSub<T> extends TextureOps.Copy2DSub<T>, GlTextureOps<T> {

        @SuppressWarnings("unchecked")
        @Override
        default T copySub(int level, int xOffset, int yOffset, int x, int y, int width, int height) {
            assertBinding();
            GLX.glCopyTexSubImage2D(targetValue(), level, xOffset, yOffset, x, y, width, height);
            GLX.checkError();
            return (T) this;
        }
    }

    interface Copy3DSub<T> extends TextureOps.Copy3DSub<T>, GlTextureOps<T> {
        @SuppressWarnings("unchecked")
        @Override
        default T copySub(int level, int xOffset, int yOffset, int zOffset, int x, int y, int width, int height) {
            assertBinding();
            GLX.glCopyTexSubImage3D(targetValue(), level, xOffset, yOffset, zOffset, x, y, width, height);
            GLX.checkError();
            return (T) this;
        }
    }

    interface CopyImageSubData<T> extends TextureOps.CopyImageSubData<T>, GlTextureOps<T> {
        @Override
        default T copyImageSubData(int srcMipMapLevel, int srcX, int srcY, int srcZ, int width, int height, int depth,
                                   RenderBuffer dst, int dstX, int dstY, int dstZ) {
            GLX.glCopyImageSubData(intHandle(), targetValue(), srcMipMapLevel, srcX, srcY, srcZ,
                    dst.intHandle(), dst.target().value(), 0, dstX, dstY, dstZ,
                    width, height, depth);
            GLX.checkError();
            return self();
        }

        @Override
        default T copyImageSubData(int srcMipMapLevel, int srcX, int srcY, int srcZ, int width, int height, int depth,
                                   Texture<?> dst, int dstMipmapLevel, int dstX, int dstY, int dstZ) {
            GLX.glCopyImageSubData(intHandle(), targetValue(), srcMipMapLevel, srcX, srcY, srcZ,
                    dst.intHandle(), dst.target().value(), dstMipmapLevel, dstX, dstY, dstZ,
                    width, height, depth);
            GLX.checkError();
            return self();
        }
    }

    interface GenerateMipmap<T> extends TextureOps.GenerateMipmap<T>, GlTextureOps<T> {
        @SuppressWarnings("unchecked")
        @Override
        default T generateMipmap() {
            assertBinding();
            GLX.glGenerateMipmap(targetValue());
            GLX.checkError();
            return (T) this;
        }
    }

    interface GetCompressedImage<T> extends TextureOps.GetCompressedImage<T>, GlTextureOps<T> {
        @Override
        default T getCompressedImage(int level, ByteBuffer data) {
            GLX.glGetCompressedTexImage(targetValue(), level, data);
            GLX.checkError();
            return self();
        }
    }

    interface GetCompressedSubImage<T> extends TextureOps.GetCompressedSubImage<T>, GlTextureOps<T> {
        interface GlGetCompressedTextureSubImage<D> {
            void apply(int texture, int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, D data);
        }

        private <D> T getCompressedSubImage(GlGetCompressedTextureSubImage<D> glGetCompressedTextureSubImage,
                                            int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, D data) {
            glGetCompressedTextureSubImage.apply(intHandle(), level, xOffset, yOffset, zOffset, width, height, depth, data);
            GLX.checkError();
            return self();
        }

        @Override
        default T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, ByteBuffer data) {
            return getCompressedSubImage(GLX::glGetCompressedTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, data);
        }

        @Override
        default T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, short[] data) {
            return getCompressedSubImage(GLX::glGetCompressedTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, data);
        }

        @Override
        default T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, int[] data) {
            return getCompressedSubImage(GLX::glGetCompressedTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, data);
        }

        @Override
        default T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, float[] data) {
            return getCompressedSubImage(GLX::glGetCompressedTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, data);
        }

        @Override
        default T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, double[] data) {
            return getCompressedSubImage(GLX::glGetCompressedTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, data);
        }

        @Override
        default T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, ShortBuffer data) {
            return getCompressedSubImage(GLX::glGetCompressedTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, data);
        }

        @Override
        default T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, IntBuffer data) {
            return getCompressedSubImage(GLX::glGetCompressedTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, data);
        }

        @Override
        default T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, FloatBuffer data) {
            return getCompressedSubImage(GLX::glGetCompressedTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, data);
        }

        @Override
        default T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, DoubleBuffer data) {
            return getCompressedSubImage(GLX::glGetCompressedTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, data);
        }
    }

    interface GetImage<T> extends TextureOps.GetImage<T>, GlTextureOps<T> {
        interface GlGetTextImage<D> {
            void apply(int texture, int level, int format, int dataType, D data);
        }

        private <D> T getImage(GlGetTextImage<D> glGetTextImage, int level, PixelFormat format, DataType dataType, D data) {
            glGetTextImage.apply(intHandle(), level, format.value(), dataType.value(), data);
            GLX.checkError();
            return self();
        }

        @Override
        default T getImage(int level, PixelFormat format, DataType dataType, ByteBuffer data) {
            return getImage(GLX::glGetTexImage, level, format, dataType, data);
        }

        @Override
        default T getImage(int level, PixelFormat format, DataType dataType, short[] data) {
            return getImage(GLX::glGetTexImage, level, format, dataType, data);
        }

        @Override
        default T getImage(int level, PixelFormat format, DataType dataType, int[] data) {
            return getImage(GLX::glGetTexImage, level, format, dataType, data);
        }

        @Override
        default T getImage(int level, PixelFormat format, DataType dataType, float[] data) {
            return getImage(GLX::glGetTexImage, level, format, dataType, data);
        }

        @Override
        default T getImage(int level, PixelFormat format, DataType dataType, double[] data) {
            return getImage(GLX::glGetTexImage, level, format, dataType, data);
        }

        @Override
        default T getImage(int level, PixelFormat format, DataType dataType, ShortBuffer data) {
            return getImage(GLX::glGetTexImage, level, format, dataType, data);
        }

        @Override
        default T getImage(int level, PixelFormat format, DataType dataType, IntBuffer data) {
            return getImage(GLX::glGetTexImage, level, format, dataType, data);
        }

        @Override
        default T getImage(int level, PixelFormat format, DataType dataType, FloatBuffer data) {
            return getImage(GLX::glGetTexImage, level, format, dataType, data);
        }

        @Override
        default T getImage(int level, PixelFormat format, DataType dataType, DoubleBuffer data) {
            return getImage(GLX::glGetTexImage, level, format, dataType, data);
        }
    }

    interface GetSubImage<T> extends TextureOps.GetSubImage<T>, GlTextureOps<T> {
        interface GlGetTextureSubImage<D> {
            void apply(int texture, int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, int format, int dataType, D data);
        }

        private <D> T getSubImage(GlGetTextureSubImage<D> glGetTextureSubImage, int level,
                                  int xOffset, int yOffset, int zOffset, int width, int height, int depth,
                                  PixelFormat format, DataType dataType, D data) {
            glGetTextureSubImage.apply(intHandle(), level, xOffset, yOffset, zOffset, width, height, depth, format.value(), dataType.value(), data);
            GLX.checkError();
            return self();
        }

        @Override
        default T getSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, ByteBuffer data) {
            return getSubImage(GLX::glGetTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T getSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, short[] data) {
            return getSubImage(GLX::glGetTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T getSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, int[] data) {
            return getSubImage(GLX::glGetTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T getSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, float[] data) {
            return getSubImage(GLX::glGetTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T getSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, double[] data) {
            return getSubImage(GLX::glGetTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T getSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, ShortBuffer data) {
            return getSubImage(GLX::glGetTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T getSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, IntBuffer data) {
            return getSubImage(GLX::glGetTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T getSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, FloatBuffer data) {
            return getSubImage(GLX::glGetTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T getSubImage(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, DoubleBuffer data) {
            return getSubImage(GLX::glGetTextureSubImage, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }
    }

    interface Load2D<T> extends TextureOps.Load2D<T>, GlTextureOps<T> {

        @SuppressWarnings("unchecked")
        @Override
        default T allocate(int level, InternalPixelFormat format, int width, int height) {
            assertBinding();
            GLX.glTexImage2D(targetValue(true),
                    level,
                    format.value(),
                    width,
                    height,
                    0,
                    format.value(),
                    DataType.UnsignedByte.value(),
                    MemoryUtil.NULL);
            GLX.checkError();
            return (T) this;
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, ByteBuffer data) {
            return load0(GLX::glTexImage2D, level, internalPixelFormat, width, height, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, ShortBuffer data) {
            return load0(GLX::glTexImage2D, level, internalPixelFormat, width, height, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, FloatBuffer data) {
            return load0(GLX::glTexImage2D, level, internalPixelFormat, width, height, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, DoubleBuffer data) {
            return load0(GLX::glTexImage2D, level, internalPixelFormat, width, height, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, short[] data) {
            return load0(GLX::glTexImage2D, level, internalPixelFormat, width, height, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, int[] data) {
            return load0(GLX::glTexImage2D, level, internalPixelFormat, width, height, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, float[] data) {
            return load0(GLX::glTexImage2D, level, internalPixelFormat, width, height, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, double[] data) {
            return load0(GLX::glTexImage2D, level, internalPixelFormat, width, height, format, dataType, data);
        }

        @SuppressWarnings("unchecked")
        private <D> T load0(GlTexImage2D<D> glTexImage2D, int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, D data) {
            assertBinding();
            glTexImage2D.apply(targetValue(true),
                    level,
                    internalPixelFormat.value(),
                    width,
                    height,
                    0,
                    format.value(),
                    dataType.value(),
                    data);
            GLX.checkError();
            return (T) this;
        }

        interface GlTexImage2D<T> {
            void apply(int target, int level, int internalPixelFormat, int width, int height, int border, int format, int dataType, T data);
        }

    }

    interface Load2DCompressed<T> extends TextureOps.Load2DCompressed<T>, GlTextureOps<T> {
        @SuppressWarnings("unchecked")
        @Override
        default T loadCompressed(int level, InternalPixelFormat.Compressed format, int width, int height, ByteBuffer data) {
            assertBinding();
            GLX.glCompressedTexImage2D(targetValue(true), level, format.value(), width, height, 0, data);
            GLX.checkError();
            return (T) this;
        }
    }

    interface Load2DCompressedSub<T> extends TextureOps.Load2DCompressedSub<T>, GlTextureOps<T> {
        @SuppressWarnings("unchecked")
        @Override
        default T loadCompressedSub(int level, InternalPixelFormat.Compressed format, int xOffset, int yOffset, int width, int height, ByteBuffer data) {
            assertBinding();
            GLX.glCompressedTexSubImage2D(targetValue(), level, xOffset, yOffset, width, height, format.value(), data);
            GLX.checkError();
            return (T) this;
        }
    }

    interface Load2DSub<T> extends TextureOps.Load2DSub<T>, GlTextureOps<T> {

        @SuppressWarnings("unchecked")
        private <D> T load0(GlTexSubImage2D<D> glTexSubImage2D, int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, D data) {
            assertBinding();
            glTexSubImage2D.apply(targetValue(),
                    level,
                    xOffset,
                    yOffset,
                    width,
                    height,
                    format.value(),
                    dataType.value(),
                    data);
            GLX.checkError();
            return (T) this;
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, ByteBuffer data) {
            return load0(GLX::glTexSubImage2D, level, xOffset, yOffset, width, height, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, ShortBuffer data) {
            return load0(GLX::glTexSubImage2D, level, xOffset, yOffset, width, height, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, FloatBuffer data) {
            return load0(GLX::glTexSubImage2D, level, xOffset, yOffset, width, height, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, DoubleBuffer data) {
            return load0(GLX::glTexSubImage2D, level, xOffset, yOffset, width, height, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, short[] data) {
            return load0(GLX::glTexSubImage2D, level, xOffset, yOffset, width, height, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, int[] data) {
            return load0(GLX::glTexSubImage2D, level, xOffset, yOffset, width, height, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, float[] data) {
            return load0(GLX::glTexSubImage2D, level, xOffset, yOffset, width, height, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, double[] data) {
            return load0(GLX::glTexSubImage2D, level, xOffset, yOffset, width, height, format, dataType, data);
        }

        interface GlTexSubImage2D<T> {
            void apply(int target, int level, int xOffset, int yOffset, int width, int height, int format, int dataType, T data);
        }
    }

    interface Load3D<T> extends TextureOps.Load3D<T>, GlTextureOps<T> {

        @SuppressWarnings("unchecked")
        @Override
        default T allocate(int level, InternalPixelFormat format, int width, int height, int depth) {
            assertBinding();
            GLX.glTexImage3D(targetValue(true), level, format.value(),
                    width, height, depth, 0, format.value(), DataType.UnsignedByte.value(), MemoryUtil.NULL);
            GLX.checkError();
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        private <D> T load(GlTexImage3D<D> glTexImage3D, int level, InternalPixelFormat internalPixelFormat,
                           int width, int height, int depth, PixelFormat format, DataType dataType, D data) {
            assertBinding();
            glTexImage3D.apply(targetValue(true), level, internalPixelFormat.value(),
                    width, height, depth, 0, format.value(), dataType.value(), data);
            GLX.checkError();
            return (T) this;
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, ByteBuffer data) {
            return load(GLX::glTexImage3D, level, internalPixelFormat, width, height, depth, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, ShortBuffer data) {
            return load(GLX::glTexImage3D, level, internalPixelFormat, width, height, depth, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, FloatBuffer data) {
            return load(GLX::glTexImage3D, level, internalPixelFormat, width, height, depth, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, DoubleBuffer data) {
            return load(GLX::glTexImage3D, level, internalPixelFormat, width, height, depth, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, short[] data) {
            return load(GLX::glTexImage3D, level, internalPixelFormat, width, height, depth, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, int[] data) {
            return load(GLX::glTexImage3D, level, internalPixelFormat, width, height, depth, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, float[] data) {
            return load(GLX::glTexImage3D, level, internalPixelFormat, width, height, depth, format, dataType, data);
        }

        @Override
        default T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, double[] data) {
            return load(GLX::glTexImage3D, level, internalPixelFormat, width, height, depth, format, dataType, data);
        }

        interface GlTexImage3D<D> {
            void apply(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, D data);
        }
    }

    interface Load3DCompressed<T> extends TextureOps.Load3DCompressed<T>, GlTextureOps<T> {
        @SuppressWarnings("unchecked")
        @Override
        default T loadCompressed(int level, InternalPixelFormat.Compressed format, int width, int height, int depth, ByteBuffer data) {
            assertBinding();
            GLX.glCompressedTexImage3D(targetValue(true), level, format.value(), width, height, depth, 0, data);
            GLX.checkError();
            return (T) this;
        }
    }

    interface Load3DCompressedSub<T> extends TextureOps.Load3DCompressedSub<T>, GlTextureOps<T> {
        @SuppressWarnings("unchecked")
        @Override
        default T loadCompressedSub(int level, InternalPixelFormat.Compressed format, int xOffset, int yOffset, int zOffset, int width, int height, int depth, ByteBuffer data) {
            assertBinding();
            GLX.glCompressedTexSubImage3D(targetValue(), level, xOffset, yOffset, zOffset, width, height, depth, format.value(), data);
            GLX.checkError();
            return (T) this;
        }
    }

    interface Load3DSub<T> extends TextureOps.Load3DSub<T>, GlTextureOps<T> {

        @SuppressWarnings("unchecked")
        private <D> T loadSub(GlTexSubImage3D<D> glTexSubImage3D, int level,
                              int xOffset, int yOffset, int zOffset,
                              int width, int height, int depth,
                              PixelFormat format, DataType dataType, D data) {
            assertBinding();
            glTexSubImage3D.apply(targetValue(), level,
                    xOffset, yOffset, zOffset,
                    width, height, depth,
                    format.value(), dataType.value(), data);
            GLX.checkError();
            return (T) this;
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, ByteBuffer data) {
            return loadSub(GLX::glTexSubImage3D, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, ShortBuffer data) {
            return loadSub(GLX::glTexSubImage3D, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, FloatBuffer data) {
            return loadSub(GLX::glTexSubImage3D, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, DoubleBuffer data) {
            return loadSub(GLX::glTexSubImage3D, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, short[] data) {
            return loadSub(GLX::glTexSubImage3D, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, int[] data) {
            return loadSub(GLX::glTexSubImage3D, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, float[] data) {
            return loadSub(GLX::glTexSubImage3D, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        @Override
        default T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, double[] data) {
            return loadSub(GLX::glTexSubImage3D, level, xOffset, yOffset, zOffset, width, height, depth, format, dataType, data);
        }

        interface GlTexSubImage3D<D> {
            void apply(int target, int level,
                       int xOffset, int yOffset, int zOffset,
                       int width, int height, int depth,
                       int format, int type, D data);
        }
    }

    interface MakeView<T extends Texture<T>> extends TextureOps.MakeView, Texture<T> {
        @Override
        default <V extends Texture<V>> V makeView(Texture.TexTarget<V> target, InternalPixelFormat format,
                                                  int minLevel, int numLevels, int minLayer, int numLayers) {
            int thisTarget = this.target().value();
            int viewTarget = target.value();
            V viewTexture = (V) switch (thisTarget) {
                case GLX.GL_TEXTURE_1D, GLX.GL_TEXTURE_1D_ARRAY -> {
                    if (viewTarget == GLX.GL_TEXTURE_1D) {
                        yield new GlTexture1D();
                    } else if (viewTarget == GLX.GL_TEXTURE_1D_ARRAY) {
                        yield new GlTexture1DArray();
                    } else {
                        yield null;
                    }
                }
                case GLX.GL_TEXTURE_2D, GLX.GL_TEXTURE_2D_ARRAY -> {
                    if (viewTarget == GLX.GL_TEXTURE_2D) {
                        yield new GlTexture2D();
                    } else if (viewTarget == GLX.GL_TEXTURE_2D_ARRAY) {
                        yield new GlTexture2DArray();
                    } else {
                        yield null;
                    }
                }
                case GLX.GL_TEXTURE_3D -> new GlTexture3D();
                case GLX.GL_TEXTURE_CUBE_MAP, GLX.GL_TEXTURE_CUBE_MAP_ARRAY -> {
                    if (viewTarget == GLX.GL_TEXTURE_CUBE_MAP) {
                        yield new GlTextureCubeMap();
                    } else if (viewTarget == GLX.GL_TEXTURE_2D) {
                        yield new GlTexture2D();
                    } else if (viewTarget == GLX.GL_TEXTURE_2D_ARRAY) {
                        yield new GlTexture2DArray();
                    } else if (viewTarget == GLX.GL_TEXTURE_CUBE_MAP_ARRAY) {
                        yield new GlTextureCubeMapArray();
                    } else {
                        yield null;
                    }
                }
                case GLX.GL_TEXTURE_RECTANGLE -> new GlTextureRectangle();
                case GLX.GL_TEXTURE_2D_MULTISAMPLE, GLX.GL_TEXTURE_2D_MULTISAMPLE_ARRAY -> {
                    if (viewTarget == GLX.GL_TEXTURE_2D_MULTISAMPLE) {
                        yield new GlTexture2DMultiSample();
                    } else if (viewTarget == GLX.GL_TEXTURE_2D_MULTISAMPLE_ARRAY) {
                        yield new GlTexture2DMultiSampleArray();
                    } else {
                        yield null;
                    }
                }
                default ->
                        throw new IllegalArgumentException("Make view of " + this.target().state().id() + " is not supported.");
            };
            if (viewTexture == null) {
                throw new IllegalArgumentException(target.state().id() + " is not compatible with " + this.target().state().id());
            }
            GLX.glTextureView(viewTexture.intHandle(), viewTarget, this.intHandle(), format.value(),
                    minLevel, numLevels, minLayer, numLayers);
            GLX.checkError();
            return viewTexture;
        }

    }

    interface Parameters<T> extends TextureOps.Parameters<T>, GlTextureOps<T> {
        @Override
        default T baseLevel(int value) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_BASE_LEVEL, value);
            GLX.checkError();
            return self();
        }

        @Override
        default T borderColor(float red, float green, float blue, float alpha) {
            assertBinding();
            GLX.glTexParameterfv(targetValue(), GLX.GL_TEXTURE_BORDER_COLOR, new float[]{red, green, blue, alpha});
            GLX.checkError();
            return self();
        }

        @Override
        default Color borderColor() {
            try (MemoryStack stack = MemoryStack.stackPush()) {
                var buffer = stack.mallocFloat(4);
                GLX.glGetTexParameterfv(targetValue(), GLX.GL_TEXTURE_BORDER_COLOR, buffer);
                return new Color(buffer.get(0), buffer.get(1), buffer.get(2), buffer.get(3));
            }
        }

        @Override
        default T compareFunc(CompareFunction func) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_COMPARE_FUNC, func.value());
            GLX.checkError();
            return self();
        }

        @Override
        default CompareFunction compareFunc() {
            return Cache.CompareFunction.valueOf(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_COMPARE_FUNC));
        }

        @Override
        default T compareModeNone() {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_COMPARE_MODE, GLX.GL_NONE);
            GLX.checkError();
            return self();
        }

        @Override
        default T compareModeRefToTexture() {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_COMPARE_MODE, GLX.GL_COMPARE_REF_TO_TEXTURE);
            GLX.checkError();
            return self();
        }

        @Override
        default T depthStencilMode(Texture.DepthStencilMode mode) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_DEPTH_STENCIL_TEXTURE_MODE, mode.value());
            GLX.checkError();
            return self();
        }

        @Override
        default int immutableLevels() {
            return GLX.glGetTexParameteri(targetValue(), GLX.GL_TEXTURE_IMMUTABLE_LEVELS);
        }

        @Override
        default boolean isCompareModeRefToTexture() {
            return GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_COMPARE_MODE) == GLX.GL_COMPARE_REF_TO_TEXTURE;
        }

        @Override
        default boolean isImmutableFormat() {
            return GLX.glGetTexParameteri(targetValue(), GLX.GL_TEXTURE_IMMUTABLE_FORMAT) == GLX.GL_TRUE;
        }

        @Override
        default T loadBias(float value) {
            assertBinding();
            GLX.glTexParameterf(targetValue(), GLX.GL_TEXTURE_LOD_BIAS, value);
            GLX.checkError();
            return self();
        }

        @Override
        default float loadBias() {
            return GLX.glGetTextureParameterf(intHandle(), GLX.GL_TEXTURE_LOD_BIAS);
        }

        @Override
        default T magFilter(MagFilter mf) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_MAG_FILTER, mf.value());
            GLX.checkError();
            return self();
        }

        @Override
        default MagFilter magFilter() {
            return Cache.MagFilter.valueOf(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_MAG_FILTER));
        }

        @Override
        default T maxLevel(int value) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_MAX_LEVEL, value);
            GLX.checkError();
            return self();
        }

        @Override
        default T maxLoad(float value) {
            assertBinding();
            GLX.glTexParameterf(targetValue(), GLX.GL_TEXTURE_MAX_LOD, value);
            GLX.checkError();
            return self();
        }

        @Override
        default float maxLoad() {
            return GLX.glGetTextureParameterf(intHandle(), GLX.GL_TEXTURE_MAX_LOD);
        }

        @Override
        default T minFilter(MinFilter mf) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_MIN_FILTER, mf.value());
            GLX.checkError();
            return self();
        }

        @Override
        default MinFilter minFilter() {
            return Cache.MinFilter.get(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_MIN_FILTER));
        }

        @Override
        default T minLoad(float value) {
            assertBinding();
            GLX.glTexParameterf(targetValue(), GLX.GL_TEXTURE_MIN_LOD, value);
            GLX.checkError();
            return self();
        }

        @Override
        default float minLoad() {
            return GLX.glGetTextureParameterf(intHandle(), GLX.GL_TEXTURE_MIN_LOD);
        }

        @Override
        default T swizzle(Texture.Swizzle r, Texture.Swizzle g, Texture.Swizzle b, Texture.Swizzle a) {
            assertBinding();
            GLX.glTexParameteriv(targetValue(), GLX.GL_TEXTURE_SWIZZLE_RGBA, new int[]{r.value(), g.value(), b.value(), a.value()});
            GLX.checkError();
            return self();
        }

        @Override
        default T swizzleA(Texture.Swizzle swizzle) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_SWIZZLE_A, swizzle.value());
            GLX.checkError();
            return self();
        }

        @Override
        default T swizzleB(Texture.Swizzle swizzle) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_SWIZZLE_B, swizzle.value());
            GLX.checkError();
            return self();
        }

        @Override
        default T swizzleG(Texture.Swizzle swizzle) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_SWIZZLE_G, swizzle.value());
            GLX.checkError();
            return self();
        }

        @Override
        default T swizzleR(Texture.Swizzle swizzle) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_SWIZZLE_R, swizzle.value());
            GLX.checkError();
            return self();
        }

        @Override
        default int viewMinLayer() {
            return GLX.glGetTexParameteri(targetValue(), GLX.GL_TEXTURE_VIEW_MIN_LAYER);
        }

        @Override
        default int viewMinLevel() {
            return GLX.glGetTexParameteri(targetValue(), GLX.GL_TEXTURE_VIEW_MIN_LEVEL);
        }

        @Override
        default int viewNumLayers() {
            return GLX.glGetTexParameteri(targetValue(), GLX.GL_TEXTURE_VIEW_NUM_LAYERS);
        }

        @Override
        default int viewNumLevels() {
            return GLX.glGetTexParameteri(targetValue(), GLX.GL_TEXTURE_VIEW_NUM_LEVELS);
        }

        @Override
        default T wrapR(Texture.Wrap wrap) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_WRAP_R, wrap.value());
            GLX.checkError();
            return self();
        }

        @Override
        default Texture.Wrap wrapR() {
            return Cache.TextureWrap.valueOf(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_R));
        }

        @Override
        default T wrapS(Texture.Wrap wrap) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_WRAP_S, wrap.value());
            GLX.checkError();
            return self();
        }

        @Override
        default Texture.Wrap wrapS() {
            return Cache.TextureWrap.valueOf(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_S));
        }

        @Override
        default T wrapT(Texture.Wrap wrap) {
            assertBinding();
            GLX.glTexParameteri(targetValue(), GLX.GL_TEXTURE_WRAP_T, wrap.value());
            GLX.checkError();
            return self();
        }

        @Override
        default Texture.Wrap wrapT() {
            return Cache.TextureWrap.valueOf(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_T));
        }
    }

    interface SetStorage2D<T> extends TextureOps.SetStorage2D<T>, GlTextureOps<T> {

        @SuppressWarnings("unchecked")
        @Override
        default T setStorage(int levels, InternalPixelFormat.Sized format, int width, int height) {
            assertBinding();
            GLX.glTexStorage2D(targetValue(true), levels, format.value(), width, height);
            GLX.checkError();
            return (T) this;
        }
    }

    interface SetStorage2DMultisample<T> extends TextureOps.SetStorage2DMultisample<T>, GlTextureOps<T> {
        @SuppressWarnings("unchecked")
        @Override
        default T setStorage(int samples, InternalPixelFormat.Sized format, int width, int height, boolean fixedSampleLocations) {
            assertBinding();
            GLX.glTexStorage2DMultisample(targetValue(true), samples, format.value(), width, height, fixedSampleLocations);
            GLX.checkError();
            return (T) this;
        }
    }

    interface SetStorage3D<T> extends TextureOps.SetStorage3D<T>, GlTextureOps<T> {
        @SuppressWarnings("unchecked")
        @Override
        default T setStorage(int levels, InternalPixelFormat.Sized format, int width, int height, int depth) {
            assertBinding();
            GLX.glTexStorage3D(targetValue(true), levels, format.value(), width, height, depth);
            GLX.checkError();
            return (T) this;
        }
    }
}
