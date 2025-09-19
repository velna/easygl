package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.primitives.Dimensionic;
import com.vanix.easygl.core.*;
import com.vanix.easygl.core.meta.MetaSystem;
import org.joml.Vector3i;
import org.joml.primitives.AABBi;

@Support(since = Version.GL30)
public interface RenderBuffer extends Bindable<BindTarget.Default<RenderBuffer>, RenderBuffer>, Dimensionic<RenderBuffer> {
    BindTarget.Default<RenderBuffer> Target = new BindTarget.Default<>(
            MetaSystem.Graphics.queryInt("RENDERBUFFER"), "RenderBuffer", MetaHolder.RenderBuffer);

    @Support(since = Version.GL30)
    RenderBuffer storage(InternalPixelFormat internalFormat, int width, int height);

    @Support(since = Version.GL30)
    RenderBuffer storageMultiSample(int samples, InternalPixelFormat internalFormat, int width, int height);

    @Support(since = Version.GL43)
    RenderBuffer copyImageSubData(int srcX, int srcY, int srcZ, int width, int height, int depth,
                                  RenderBuffer dst, int dstX, int dstY, int dstZ);

    @Support(since = Version.GL43)
    RenderBuffer copyImageSubData(int srcX, int srcY, int srcZ, int width, int height, int depth,
                                  Texture<?> dst, int dstMipmapLevel, int dstX, int dstY, int dstZ);

    @Support(since = Version.GL43)
    default RenderBuffer copyImageSubData(Vector3i srcXyz, Vector3i size, RenderBuffer dst, Vector3i dstXyz) {
        return copyImageSubData(srcXyz.x, srcXyz.y, srcXyz.z, size.x, size.y, size.z,
                dst, dstXyz.x, dstXyz.y, dstXyz.z);
    }

    @Support(since = Version.GL43)
    default RenderBuffer copyImageSubData(Vector3i srcXyz, Vector3i size, Texture<?> dst, int dstMipmapLevel, Vector3i dstXyz) {
        return copyImageSubData(srcXyz.x, srcXyz.y, srcXyz.z, size.x, size.y, size.z,
                dst, dstMipmapLevel, dstXyz.x, dstXyz.y, dstXyz.z);
    }

    @Support(since = Version.GL43)
    default RenderBuffer copyImageSubData(AABBi src, RenderBuffer dst, Vector3i dstXyz) {
        return copyImageSubData(src.minX, src.minY, src.minZ, src.lengthX(), src.lengthY(), src.lengthZ(),
                dst, dstXyz.x, dstXyz.y, dstXyz.z);
    }

    @Support(since = Version.GL43)
    default RenderBuffer copyImageSubData(AABBi src, Texture<?> dst, int dstMipmapLevel, Vector3i dstXyz) {
        return copyImageSubData(src.minX, src.minY, src.minZ, src.lengthX(), src.lengthY(), src.lengthZ(),
                dst, dstMipmapLevel, dstXyz.x, dstXyz.y, dstXyz.z);
    }

    @Support(since = Version.GL30)
    InternalPixelFormat getInternalFormat();

    @Support(since = Version.GL30)
    int getNumSamples();

    @Support(since = Version.GL30)
    int getRedBits();

    @Support(since = Version.GL30)
    int getGreenBits();

    @Support(since = Version.GL30)
    int getBlueBits();

    @Support(since = Version.GL30)
    int getAlphaBits();

    @Support(since = Version.GL30)
    int getDepthBits();

    @Support(since = Version.GL30)
    int getStencilBits();

    static RenderBuffer of() {
        return MetaHolder.RenderBuffer.create();
    }
}
