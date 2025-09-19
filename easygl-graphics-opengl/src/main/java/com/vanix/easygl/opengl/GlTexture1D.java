package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.InternalPixelFormat;
import com.vanix.easygl.graphics.PixelFormat;
import com.vanix.easygl.graphics.Texture1D;
import org.lwjgl.system.MemoryUtil;

import java.nio.*;

public class GlTexture1D extends AbstractTexture<Texture1D> implements Texture1D,
        GlTextureOps.GetImage<Texture1D>,
        GlTextureOps.GetSubImage<Texture1D>,
        GlTextureOps.GetCompressedImage<Texture1D>,
        GlTextureOps.GetCompressedSubImage<Texture1D>,
        GlTextureOps.CopyImageSubData<Texture1D>,
        GlTextureOps.Parameters<Texture1D>,
        GlTextureOps.MakeView<Texture1D>,
        GlTextureOps.GenerateMipmap<Texture1D> {
    public GlTexture1D() {
        this(GLX.glGenTextures());
    }

    public GlTexture1D(int handle) {
        super(handle, Target);
    }

    private GlTexture1D(int handle, TexTarget<Texture1D> target) {
        super(handle, target);
    }

    @Override
    public Texture1D proxy() {
        return new GlTexture1D(intHandle(), target.proxy(GLX.GL_PROXY_TEXTURE_1D));
    }

    interface GlTexImage1D<D> {
        void apply(int target, int level, int internalFormat, int width, int border, int format, int type, D data);
    }

    private <D> Texture1D load(GlTexImage1D<D> glTexImage1D, int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, D data) {
        assertBinding();
        glTexImage1D.apply(target.value(true), level, internalPixelFormat.value(), width, 0, format.value(), dataType.value(), data);
        GLX.checkError();
        return this;
    }

    @Override
    public Texture1D allocate(int level, InternalPixelFormat format, int width) {
        assertBinding();
        GLX.glTexImage1D(target.value(true), level, format.value(), width, 0, format.value(), DataType.UnsignedByte.value(), MemoryUtil.NULL);
        GLX.checkError();
        return this;
    }

    @Override
    public Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, ByteBuffer data) {
        return load(GLX::glTexImage1D, level, internalPixelFormat, width, format, dataType, data);
    }

    @Override
    public Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, ShortBuffer data) {
        return load(GLX::glTexImage1D, level, internalPixelFormat, width, format, dataType, data);
    }

    @Override
    public Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, IntBuffer data) {
        return load(GLX::glTexImage1D, level, internalPixelFormat, width, format, dataType, data);
    }

    @Override
    public Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, FloatBuffer data) {
        return load(GLX::glTexImage1D, level, internalPixelFormat, width, format, dataType, data);
    }

    @Override
    public Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, DoubleBuffer data) {
        return load(GLX::glTexImage1D, level, internalPixelFormat, width, format, dataType, data);
    }

    @Override
    public Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, short[] data) {
        return load(GLX::glTexImage1D, level, internalPixelFormat, width, format, dataType, data);
    }

    @Override
    public Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, int[] data) {
        return load(GLX::glTexImage1D, level, internalPixelFormat, width, format, dataType, data);
    }

    @Override
    public Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, float[] data) {
        return load(GLX::glTexImage1D, level, internalPixelFormat, width, format, dataType, data);
    }

    @Override
    public Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, double[] data) {
        return load(GLX::glTexImage1D, level, internalPixelFormat, width, format, dataType, data);
    }

    interface GlTexSubImage1D<D> {
        void apply(int target, int level, int xOffset, int width, int format, int type, D data);
    }

    private <D> Texture1D loadSub(GlTexSubImage1D<D> glTexSubImage1D, int level, int xOffset, int width, PixelFormat format, DataType dataType, D data) {
        assertBinding();
        glTexSubImage1D.apply(target.value(), level, xOffset, width, format.value(), dataType.value(), data);
        GLX.checkError();
        return this;
    }

    @Override
    public Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, ByteBuffer data) {
        return loadSub(GLX::glTexSubImage1D, level, xOffset, width, format, dataType, data);
    }

    @Override
    public Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, ShortBuffer data) {
        return loadSub(GLX::glTexSubImage1D, level, xOffset, width, format, dataType, data);
    }

    @Override
    public Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, IntBuffer data) {
        return loadSub(GLX::glTexSubImage1D, level, xOffset, width, format, dataType, data);
    }

    @Override
    public Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, FloatBuffer data) {
        return loadSub(GLX::glTexSubImage1D, level, xOffset, width, format, dataType, data);
    }

    @Override
    public Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, DoubleBuffer data) {
        return loadSub(GLX::glTexSubImage1D, level, xOffset, width, format, dataType, data);
    }

    @Override
    public Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, short[] data) {
        return loadSub(GLX::glTexSubImage1D, level, xOffset, width, format, dataType, data);
    }

    @Override
    public Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, int[] data) {
        return loadSub(GLX::glTexSubImage1D, level, xOffset, width, format, dataType, data);
    }

    @Override
    public Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, float[] data) {
        return loadSub(GLX::glTexSubImage1D, level, xOffset, width, format, dataType, data);
    }

    @Override
    public Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, double[] data) {
        return loadSub(GLX::glTexSubImage1D, level, xOffset, width, format, dataType, data);
    }

    @Override
    public Texture1D loadCompressed(int level, InternalPixelFormat.Compressed format, int width, ByteBuffer data) {
        assertBinding();
        GLX.glCompressedTexImage1D(target.value(true), level, format.value(), width, 0, data);
        GLX.checkError();
        return this;
    }

    @Override
    public Texture1D loadCompressedSub(int level, InternalPixelFormat.Compressed format, int xOffset, int width, ByteBuffer data) {
        assertBinding();
        GLX.glCompressedTexSubImage1D(target.value(), level, xOffset, width, format.value(), data);
        GLX.checkError();
        return this;
    }

    @Override
    public Texture1D copy(int level, InternalPixelFormat.Compressed format, int x, int y, int width) {
        assertBinding();
        GLX.glCopyTexImage1D(target.value(), level, format.value(), x, y, width, 0);
        GLX.checkError();
        return this;
    }

    @Override
    public Texture1D copySub(int level, int xOffset, int x, int y, int width) {
        assertBinding();
        GLX.glCopyTexSubImage1D(target.value(), level, xOffset, x, y, width);
        GLX.checkError();
        return this;
    }

    @Override
    public Texture1D setStorage(int levels, InternalPixelFormat.Sized format, int width) {
        assertBinding();
        GLX.glTexStorage1D(target.value(true), levels, format.value(), width);
        GLX.checkError();
        return this;
    }
}
