package com.vanix.easygl.graphics;

import com.vanix.easygl.core.*;
import com.vanix.easygl.core.media.Image;
import org.joml.Vector3i;
import org.joml.primitives.AABBi;

import java.nio.*;

public interface TextureOps {

    interface ClearImage<T> {
        @Support(since = Version.GL44)
        T clearImage(int level, PixelFormat format, DataType dataType, ByteBuffer data);

        @Support(since = Version.GL44)
        T clearImage(int level, PixelFormat format, DataType dataType, short[] data);

        @Support(since = Version.GL44)
        T clearImage(int level, PixelFormat format, DataType dataType, int[] data);

        @Support(since = Version.GL44)
        T clearImage(int level, PixelFormat format, DataType dataType, float[] data);

        T clearImage(int level, PixelFormat format, DataType dataType, double[] data);

        @Support(since = Version.GL44)
        T clearImage(int level, PixelFormat format, DataType dataType, ShortBuffer data);

        @Support(since = Version.GL44)
        T clearImage(int level, PixelFormat format, DataType dataType, IntBuffer data);

        @Support(since = Version.GL44)
        T clearImage(int level, PixelFormat format, DataType dataType, FloatBuffer data);

        @Support(since = Version.GL44)
        T clearImage(int level, PixelFormat format, DataType dataType, DoubleBuffer data);
    }

    interface ClearSubImage<T> {
        @Support(since = Version.GL44)
        T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                        int width, int height, int depth,
                        PixelFormat format, DataType dataType, ByteBuffer data);

        @Support(since = Version.GL44)
        T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                        int width, int height, int depth,
                        PixelFormat format, DataType dataType, short[] data);

        @Support(since = Version.GL44)
        T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                        int width, int height, int depth,
                        PixelFormat format, DataType dataType, int[] data);

        @Support(since = Version.GL44)
        T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                        int width, int height, int depth,
                        PixelFormat format, DataType dataType, float[] data);

        T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                        int width, int height, int depth,
                        PixelFormat format, DataType dataType, double[] data);

        @Support(since = Version.GL44)
        T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                        int width, int height, int depth,
                        PixelFormat format, DataType dataType, ShortBuffer data);

        @Support(since = Version.GL44)
        T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                        int width, int height, int depth,
                        PixelFormat format, DataType dataType, IntBuffer data);

        @Support(since = Version.GL44)
        T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                        int width, int height, int depth,
                        PixelFormat format, DataType dataType, FloatBuffer data);

        @Support(since = Version.GL44)
        T clearSubImage(int level, int xOffset, int yOffset, int zOffset,
                        int width, int height, int depth,
                        PixelFormat format, DataType dataType, DoubleBuffer data);
    }

    interface Copy2D<T> {
        T copy(int level, InternalPixelFormat internalPixelFormat, int x, int y, int width, int height);
    }

    interface Copy2DSub<T> {
        T copySub(int level, int xOffset, int yOffset, int x, int y, int width, int height);
    }

    interface Copy3DSub<T> {
        T copySub(int level, int xOffset, int yOffset, int zOffset, int x, int y, int width, int height);
    }

    interface CopyImageSubData<T> {
        @Support(since = Version.GL43)
        T copyImageSubData(int srcMipMapLevel, int srcX, int srcY, int srcZ, int width, int height, int depth,
                           RenderBuffer dst, int dstX, int dstY, int dstZ);

        @Support(since = Version.GL43)
        T copyImageSubData(int srcMipMapLevel, int srcX, int srcY, int srcZ, int width, int height, int depth,
                           Texture<?> dst, int dstMipmapLevel, int dstX, int dstY, int dstZ);

        @Support(since = Version.GL43)
        default T copyImageSubData(int srcMipMapLevel, Vector3i srcXyz, Vector3i size, RenderBuffer dst, Vector3i dstXyz) {
            return copyImageSubData(srcMipMapLevel, srcXyz.x, srcXyz.y, srcXyz.z, size.x, size.y, size.z,
                    dst, dstXyz.x, dstXyz.y, dstXyz.z);
        }

        @Support(since = Version.GL43)
        default T copyImageSubData(int srcMipMapLevel, Vector3i srcXyz, Vector3i size, Texture<?> dst, int dstMipmapLevel, Vector3i dstXyz) {
            return copyImageSubData(srcMipMapLevel, srcXyz.x, srcXyz.y, srcXyz.z, size.x, size.y, size.z,
                    dst, dstMipmapLevel, dstXyz.x, dstXyz.y, dstXyz.z);
        }

        @Support(since = Version.GL43)
        default T copyImageSubData(int srcMipMapLevel, AABBi src, RenderBuffer dst, Vector3i dstXyz) {
            return copyImageSubData(srcMipMapLevel, src.minX, src.minY, src.minZ, src.lengthX(), src.lengthY(), src.lengthZ(),
                    dst, dstXyz.x, dstXyz.y, dstXyz.z);
        }

        @Support(since = Version.GL43)
        default T copyImageSubData(int srcMipMapLevel, AABBi src, Texture<?> dst, int dstMipmapLevel, Vector3i dstXyz) {
            return copyImageSubData(srcMipMapLevel, src.minX, src.minY, src.minZ, src.lengthX(), src.lengthY(), src.lengthZ(),
                    dst, dstMipmapLevel, dstXyz.x, dstXyz.y, dstXyz.z);
        }
    }

    interface GenerateMipmap<T> {
        T generateMipmap();
    }

    interface GetCompressedImage<T> {
        T getCompressedImage(int level, ByteBuffer data);
    }

    interface GetCompressedSubImage<T> {
        @Support(since = Version.GL45)
        T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                ByteBuffer data);

        @Support(since = Version.GL45)
        T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                short[] data);

        @Support(since = Version.GL45)
        T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                int[] data);

        @Support(since = Version.GL45)
        T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                float[] data);

        @Support(since = Version.GL45)
        T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                double[] data);

        @Support(since = Version.GL45)
        T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                ShortBuffer data);

        @Support(since = Version.GL45)
        T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                IntBuffer data);

        @Support(since = Version.GL45)
        T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                FloatBuffer data);

        @Support(since = Version.GL45)
        T getCompressedSubImage(int level, int xOffset, int yOffset, int zOffset,
                                int width, int height, int depth,
                                DoubleBuffer data);
    }

    interface GetImage<T> {
        T getImage(int level, PixelFormat format, DataType dataType, ByteBuffer data);

        T getImage(int level, PixelFormat format, DataType dataType, short[] data);

        T getImage(int level, PixelFormat format, DataType dataType, int[] data);

        T getImage(int level, PixelFormat format, DataType dataType, float[] data);

        T getImage(int level, PixelFormat format, DataType dataType, double[] data);

        T getImage(int level, PixelFormat format, DataType dataType, ShortBuffer data);

        T getImage(int level, PixelFormat format, DataType dataType, IntBuffer data);

        T getImage(int level, PixelFormat format, DataType dataType, FloatBuffer data);

        T getImage(int level, PixelFormat format, DataType dataType, DoubleBuffer data);
    }

    interface GetSubImage<T> {
        @Support(since = Version.GL45)
        T getSubImage(int level, int xOffset, int yOffset, int zOffset,
                      int width, int height, int depth,
                      PixelFormat format, DataType dataType, ByteBuffer data);

        @Support(since = Version.GL45)
        T getSubImage(int level, int xOffset, int yOffset, int zOffset,
                      int width, int height, int depth,
                      PixelFormat format, DataType dataType, short[] data);

        @Support(since = Version.GL45)
        T getSubImage(int level, int xOffset, int yOffset, int zOffset,
                      int width, int height, int depth,
                      PixelFormat format, DataType dataType, int[] data);

        @Support(since = Version.GL45)
        T getSubImage(int level, int xOffset, int yOffset, int zOffset,
                      int width, int height, int depth,
                      PixelFormat format, DataType dataType, float[] data);

        @Support(since = Version.GL45)
        T getSubImage(int level, int xOffset, int yOffset, int zOffset,
                      int width, int height, int depth,
                      PixelFormat format, DataType dataType, double[] data);

        @Support(since = Version.GL45)
        T getSubImage(int level, int xOffset, int yOffset, int zOffset,
                      int width, int height, int depth,
                      PixelFormat format, DataType dataType, ShortBuffer data);

        @Support(since = Version.GL45)
        T getSubImage(int level, int xOffset, int yOffset, int zOffset,
                      int width, int height, int depth,
                      PixelFormat format, DataType dataType, IntBuffer data);

        @Support(since = Version.GL45)
        T getSubImage(int level, int xOffset, int yOffset, int zOffset,
                      int width, int height, int depth,
                      PixelFormat format, DataType dataType, FloatBuffer data);

        @Support(since = Version.GL45)
        T getSubImage(int level, int xOffset, int yOffset, int zOffset,
                      int width, int height, int depth,
                      PixelFormat format, DataType dataType, DoubleBuffer data);
    }

    interface Load2D<T> {
        default T allocate(InternalPixelFormat format, int width, int height) {
            return allocate(0, format, width, height);
        }

        T allocate(int level, InternalPixelFormat format, int width, int height);

        default T load(String imageResource) {
            return load(imageResource, false);
        }

        default T load(String imageResource, boolean flipVertically) {
            try (var image = Image.load(imageResource, flipVertically)) {
                return load(0, PixelFormat.ofImageFormat(image.format()).internalPixelFormat(), image);
            }
        }

        default T load(Image image) {
            return load(0, PixelFormat.ofImageFormat(image.format()).internalPixelFormat(), image);
        }

        default T load(int level, Image image) {
            return load(level, PixelFormat.ofImageFormat(image.format()).internalPixelFormat(), image);
        }

        default T load(int level, InternalPixelFormat internalFormat, Image image) {
            return load(level, internalFormat, image.width(), image.height(), PixelFormat.ofImageFormat(image.format()), DataType.UnsignedByte, image.data());
        }

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, ByteBuffer data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, ShortBuffer data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, FloatBuffer data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, DoubleBuffer data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, short[] data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, int[] data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, float[] data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, PixelFormat format, DataType dataType, double[] data);

    }

    interface Load2DCompressed<T> {
        T loadCompressed(int level, InternalPixelFormat.Compressed format, int width, int height, ByteBuffer data);
    }

    interface Load2DCompressedSub<T> {
        T loadCompressedSub(int level, InternalPixelFormat.Compressed format, int xOffset, int yOffset, int width, int height, ByteBuffer data);
    }

    interface Load2DSub<T> {
        default T loadSub(int xOffset, int yOffset, String imageResource) {
            return loadSub(xOffset, yOffset, imageResource, false);
        }

        default T loadSub(int xOffset, int yOffset, String imageResource, boolean flipVertically) {
            try (var image = Image.load(imageResource, flipVertically)) {
                return loadSub(xOffset, yOffset, image);
            }
        }

        default T loadSub(int xOffset, int yOffset, Image image) {
            return loadSub(0, xOffset, yOffset, image.width(), image.height(), PixelFormat.ofImageFormat(image.format()), DataType.UnsignedByte, image.data());
        }

        default T loadSub(int level, int xOffset, int yOffset, Image image) {
            return loadSub(level, xOffset, yOffset, image.width(), image.height(), PixelFormat.ofImageFormat(image.format()), DataType.UnsignedByte, image.data());
        }

        default T loadSub(int level, int xOffset, int yOffset, Image image, DataType dataType) {
            return loadSub(level, xOffset, yOffset, image.width(), image.height(), PixelFormat.ofImageFormat(image.format()), dataType, image.data());
        }

        T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, ByteBuffer data);

        T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, ShortBuffer data);

        T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, FloatBuffer data);

        T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, DoubleBuffer data);

        T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, short[] data);

        T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, int[] data);

        T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, float[] data);

        T loadSub(int level, int xOffset, int yOffset, int width, int height, PixelFormat format, DataType dataType, double[] data);
    }

    interface Load3D<T> {
        default T allocate(InternalPixelFormat format, int width, int height, int depth) {
            return allocate(0, format, width, height, depth);
        }

        T allocate(int level, InternalPixelFormat format, int width, int height, int depth);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, ByteBuffer data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, ShortBuffer data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, FloatBuffer data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, DoubleBuffer data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, short[] data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, int[] data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, float[] data);

        T load(int level, InternalPixelFormat internalPixelFormat, int width, int height, int depth, PixelFormat format, DataType dataType, double[] data);

    }

    interface Load3DCompressed<T> {
        T loadCompressed(int level, InternalPixelFormat.Compressed format, int width, int height, int depth, ByteBuffer data);
    }

    interface Load3DCompressedSub<T> {
        T loadCompressedSub(int level, InternalPixelFormat.Compressed format,
                            int xOffset, int yOffset, int zOffset,
                            int width, int height, int depth,
                            ByteBuffer data);
    }

    interface Load3DSub<T> {
        T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, ByteBuffer data);

        T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, ShortBuffer data);

        T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, FloatBuffer data);

        T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, DoubleBuffer data);

        T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, short[] data);

        T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, int[] data);

        T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, float[] data);

        T loadSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth, PixelFormat format, DataType dataType, double[] data);
    }

    interface MakeView {
        @Support(since = Version.GL43)
        <V extends Texture<V>> V makeView(Texture.TexTarget<V> target, InternalPixelFormat format,
                                          int minLevel, int numLevels, int minLayer, int numLayers);
    }

    interface Parameters<T> extends SamplerParameters<T> {
        T baseLevel(int value);

        @Support(since = Version.GL43)
        T depthStencilMode(Texture.DepthStencilMode mode);

        @Support(since = Version.GL43)
        int immutableLevels();

        boolean isImmutableFormat();

        T maxLevel(int value);

        @Support(since = Version.GL33)
        T swizzle(Texture.Swizzle r, Texture.Swizzle g, Texture.Swizzle b, Texture.Swizzle a);

        @Support(since = Version.GL33)
        T swizzleA(Texture.Swizzle swizzle);

        @Support(since = Version.GL33)
        T swizzleB(Texture.Swizzle swizzle);

        @Support(since = Version.GL33)
        T swizzleG(Texture.Swizzle swizzle);

        @Support(since = Version.GL33)
        T swizzleR(Texture.Swizzle swizzle);

        @Support(since = Version.GL43)
        int viewMinLayer();

        @Support(since = Version.GL43)
        int viewMinLevel();

        @Support(since = Version.GL43)
        int viewNumLayers();

        @Support(since = Version.GL43)
        int viewNumLevels();
    }

    interface SetStorage2D<T> {
        @Support(since = Version.GL42)
        T setStorage(int levels, InternalPixelFormat.Sized format, int width, int height);
    }

    interface SetStorage2DMultisample<T> {
        @Support(since = Version.GL43)
        T setStorage(int samples, InternalPixelFormat.Sized format, int width, int height, boolean fixedSampleLocations);
    }

    interface SetStorage3D<T> {
        @Support(since = Version.GL42)
        T setStorage(int levels, InternalPixelFormat.Sized format, int width, int height, int depth);
    }

}
