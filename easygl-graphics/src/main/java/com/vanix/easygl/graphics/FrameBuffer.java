package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.primitives.Rectanglei;
import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.Support;

public interface FrameBuffer extends BaseFrameBuffer<FrameBuffer> {

    FrameBuffer selectDrawBuffer(FrameInnerBuffer.ColorBuffer colorBuffer);

    @Support(since = Version.GL30)
    FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture1D texture1D, int level);

    @Support(since = Version.GL30)
    FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture2D texture2D, int level);

    @Support(since = Version.GL30)
    FrameBuffer attach(FrameInnerBuffer.Attachment attachment, TextureRectangle textureRectangle);

    @Support(since = Version.GL30)
    FrameBuffer attach(FrameInnerBuffer.Attachment attachment, TextureCubeMap.Face face, int level);

    @Support(since = Version.GL30)
    FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture2DMultiSample textureMultiSample);

    @Support(since = Version.GL30)
    FrameBuffer attach(FrameInnerBuffer.Attachment attachment, Texture3D texture3D, int level, int layer);

    @Support(since = Version.GL30)
    FrameBuffer detach(FrameInnerBuffer.Attachment attachment, Texture.TexTarget<?> target, int level);

    @Support(since = Version.GL30)
    FrameBuffer detachCubeMap(FrameInnerBuffer.Attachment attachment, TextureCubeMap.FaceTarget target, int level);

    @Support(since = Version.GL30)
    FrameBuffer detach3D(FrameInnerBuffer.Attachment attachment, int level, int layer);

    @Support(since = Version.GL30)
    FrameBuffer attachLayer(FrameInnerBuffer.Attachment attachment, Texture1DArray texture1DArray, int level, int layer);

    @Support(since = Version.GL30)
    FrameBuffer attachLayer(FrameInnerBuffer.Attachment attachment, Texture2DArray texture2DArray, int level, int layer);

    @Support(since = Version.GL30)
    FrameBuffer attachLayer(FrameInnerBuffer.Attachment attachment, TextureCubeMapArray textureCubeMapArray, int level, int layer);

    @Support(since = Version.GL30)
    FrameBuffer attachLayer(FrameInnerBuffer.Attachment attachment, Texture2DMultiSampleArray texture2DMultiSampleArray, int level, int layer);

    @Support(since = Version.GL30)
    FrameBuffer attachLayer(FrameInnerBuffer.Attachment attachment, Texture3D texture3D, int level, int layer);

    FrameBuffer detachLayer(FrameInnerBuffer.Attachment attachment, int level, int layer);

    @Support(since = Version.GL32)
    FrameBuffer attachLayered(FrameInnerBuffer.Attachment attachment, Texture<?> texture, int level);

    FrameBuffer detachLayered(FrameInnerBuffer.Attachment attachment, int level);

    @Support(since = Version.GL43)
    FrameBuffer invalidate(FrameInnerBuffer.Attachment attachment);

    @Support(since = Version.GL43)
    FrameBuffer invalidate(int x, int y, int width, int height, FrameInnerBuffer.Attachment attachment);

    default FrameBuffer invalidate(Rectanglei<?> rectangle, FrameInnerBuffer.Attachment attachment) {
        return invalidate(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), attachment);
    }

    @Support(since = Version.GL43)
    FrameBuffer setDefaultWidth(int width);

    @Support(since = Version.GL43)
    int getDefaultWidth();

    @Support(since = Version.GL43)
    FrameBuffer setDefaultHeight(int height);

    @Support(since = Version.GL43)
    int getDefaultHeight();

    @Support(since = Version.GL43)
    FrameBuffer setDefaultLayers(int layers);

    @Support(since = Version.GL43)
    int getDefaultLayers();

    @Support(since = Version.GL43)
    FrameBuffer setDefaultSamples(int samples);

    @Support(since = Version.GL43)
    int getDefaultSamples();

    @Support(since = Version.GL43)
    FrameBuffer setDefaultFixedSampleLocations(int fixedSampleLocations);

    @Support(since = Version.GL43)
    int getDefaultFixedSampleLocations();

    static FrameBuffer of() {
        return MetaHolder.FrameBuffer.create();
    }

    static HandleArray<FrameBuffer> of(int n) {
        return MetaHolder.FrameBuffer.createArray(n);
    }

}
