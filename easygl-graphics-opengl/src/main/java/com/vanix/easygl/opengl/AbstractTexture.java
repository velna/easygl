package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.InternalPixelFormat;
import com.vanix.easygl.graphics.Texture;
import org.eclipse.collections.api.factory.primitive.IntObjectMaps;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import java.util.function.IntConsumer;

public abstract class AbstractTexture<T extends Texture<T>> extends AbstractBindable<Texture.TexTarget<T>, T> implements Texture<T>,
        GlTextureOps.ClearImage<T>,
        GlTextureOps.ClearSubImage<T> {

    private static final MutableIntObjectMap<Texture<?>> CACHE = IntObjectMaps.mutable.of();

    protected AbstractTexture(int handle, Texture.TexTarget<T> target) {
        super(handle, target, (IntConsumer) GLX::glDeleteTextures);
        CACHE.put(handle, this);
    }

    @Override
    public void close() {
        super.close();
        CACHE.remove(intHandle());
    }

    static Texture<?> get(int handle) {
        return CACHE.get(handle);
    }

    public int targetValue(boolean proxy) {
        return target.value(proxy);
    }

    @Override
    public int alphaSize(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_ALPHA_SIZE);
    }

    @Override
    public DataType alphaType(int level) {
        return Cache.DataType.get(GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_ALPHA_TYPE));
    }

    @Override
    public int blueSize(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_BLUE_SIZE);
    }

    @Override
    public DataType blueType(int level) {
        return Cache.DataType.get(GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_BLUE_TYPE));
    }

    @Override
    public int bufferOffset(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_BUFFER_OFFSET);
    }

    @Override
    public int bufferSize(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_BUFFER_SIZE);
    }

    @Override
    public int compressedImageSize(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_COMPRESSED_IMAGE_SIZE);
    }

    @Override
    public int depth(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_DEPTH);
    }

    @Override
    public int depthSize(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_DEPTH_SIZE);
    }

    @Override
    public DataType depthType(int level) {
        return Cache.DataType.get(GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_DEPTH_TYPE));
    }

    @Override
    public int greenSize(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_GREEN_SIZE);
    }

    @Override
    public DataType greenType(int level) {
        return Cache.DataType.get(GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_GREEN_TYPE));
    }

    @Override
    public int height(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_HEIGHT);
    }

    @Override
    public InternalPixelFormat internalFormat(int level) {
        return Cache.InternalPixelFormatCache.get(GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_INTERNAL_FORMAT));
    }

    @Override
    public boolean isCompressed(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_COMPRESSED) == GLX.GL_TRUE;
    }

    @Override
    public int redSize(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_RED_SIZE);
    }

    @Override
    public DataType redType(int level) {
        return Cache.DataType.get(GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_RED_TYPE));
    }

    @Override
    public int width(int level) {
        return GLX.glGetTexLevelParameteri(target.value(), level, GLX.GL_TEXTURE_WIDTH);
    }

    @Override
    public T invalidate(int level) {
        GLX.glInvalidateTexImage(intHandle(), level);
        return self();
    }

    @Override
    public T invalidateSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth) {
        GLX.glInvalidateTexSubImage(intHandle(), level, xOffset, yOffset, zOffset, width, height, depth);
        GLX.checkError();
        return self();
    }
}
