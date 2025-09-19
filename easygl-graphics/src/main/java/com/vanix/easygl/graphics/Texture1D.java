package com.vanix.easygl.graphics;

import com.vanix.easygl.core.*;
import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.*;

public interface Texture1D extends Texture<Texture1D>,
        TextureOps.GetImage<Texture1D>,
        TextureOps.GetSubImage<Texture1D>,
        TextureOps.GetCompressedImage<Texture1D>,
        TextureOps.GetCompressedSubImage<Texture1D>,
        TextureOps.MakeView,
        TextureOps.Parameters<Texture1D>,
        TextureOps.CopyImageSubData<Texture1D>,
        TextureOps.GenerateMipmap<Texture1D> {
    Texture.TexTarget<Texture1D> Target = new Texture.TexTarget<>(MetaSystem.Graphics.queryInt("TEXTURE_1D"), "Texture1D", MetaHolder.Texture1D);

    default Texture1D allocate(InternalPixelFormat format, int width) {
        return allocate(0, format, width);
    }

    Texture1D allocate(int level, InternalPixelFormat format, int width);

    Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, ByteBuffer data);

    Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, ShortBuffer data);

    Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, IntBuffer data);

    Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, FloatBuffer data);

    Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, DoubleBuffer data);

    Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, short[] data);

    Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, int[] data);

    Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, float[] data);

    Texture1D load(int level, InternalPixelFormat internalPixelFormat, int width, PixelFormat format, DataType dataType, double[] data);

    Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, ByteBuffer data);

    Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, ShortBuffer data);

    Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, IntBuffer data);

    Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, FloatBuffer data);

    Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, DoubleBuffer data);

    Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, short[] data);

    Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, int[] data);

    Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, float[] data);

    Texture1D loadSub(int level, int xOffset, int width, PixelFormat format, DataType dataType, double[] data);

    Texture1D loadCompressed(int level, InternalPixelFormat.Compressed format, int width, ByteBuffer data);

    Texture1D loadCompressedSub(int level, InternalPixelFormat.Compressed format, int xOffset, int width, ByteBuffer data);

    Texture1D copy(int level, InternalPixelFormat.Compressed format, int x, int y, int width);

    Texture1D copySub(int level, int xOffset, int x, int y, int width);

    @Support(since = Version.GL42)
    Texture1D setStorage(int levels, InternalPixelFormat.Sized format, int width);

    static Texture1D of() {
        return MetaHolder.Texture1D.create();
    }

    static HandleArray<Texture1D> of(int n) {
        return MetaHolder.Texture1D.createArray(n);
    }
}
