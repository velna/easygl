package com.vanix.easygl.graphics;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Texture2DMultiSampleArray extends Texture<Texture2DMultiSampleArray>,
        TextureOps.Parameters<Texture2DMultiSampleArray>,
        TextureOps.CopyImageSubData<Texture2DMultiSampleArray>,
        TextureOps.MakeView {
    Texture.TexTarget<Texture2DMultiSampleArray> Target = new Texture.TexTarget<>(
            MetaSystem.Graphics.queryInt("TEXTURE_2D_MULTISAMPLE_ARRAY"),
            "Texture2DMultiSampleArray",
            MetaHolder.Texture2DMultiSampleArray);

    Texture2DMultiSampleArray establish(int samples, InternalPixelFormat pixelFormat, int width, int height, int depth, boolean fixedSampleLocations);

    default Texture2DMultiSampleArray establish(int samples, InternalPixelFormat pixelFormat, int width, int height, int depth) {
        return establish(samples, pixelFormat, width, height, depth, true);
    }

    Texture2DMultiSampleArray setStorage(int samples, InternalPixelFormat pixelFormat, int width, int height, int depth, boolean fixedSampleLocations);

    default Texture2DMultiSampleArray setStorage(int samples, InternalPixelFormat pixelFormat, int width, int height, int depth) {
        return establish(samples, pixelFormat, width, height, depth, true);
    }

    static Texture2DMultiSampleArray of() {
        return MetaHolder.Texture2DMultiSampleArray.create();
    }

    static HandleArray<Texture2DMultiSampleArray> of(int n) {
        return MetaHolder.Texture2DMultiSampleArray.createArray(n);
    }
}
