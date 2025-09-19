package com.vanix.easygl.graphics;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Texture2DMultiSample extends Texture<Texture2DMultiSample>,
        TextureOps.MakeView,
        TextureOps.CopyImageSubData<Texture2DMultiSample>,
        TextureOps.Parameters<Texture2DMultiSample>,
        TextureOps.SetStorage2DMultisample<Texture2DMultiSample> {
    Texture.TexTarget<Texture2DMultiSample> Target = new Texture.TexTarget<>(MetaSystem.Graphics.queryInt("TEXTURE_2D_MULTISAMPLE"), "Texture2DMultiSampleArray", MetaHolder.Texture2DMultiSample);

    Texture2DMultiSample establish(int samples, InternalPixelFormat pixelFormat, int width, int height, boolean fixedSampleLocations);

    default Texture2DMultiSample establish(int samples, InternalPixelFormat pixelFormat, int width, int height) {
        return establish(samples, pixelFormat, width, height, true);
    }

    Texture2DMultiSample setStorage(int samples, InternalPixelFormat pixelFormat, int width, int height, boolean fixedSampleLocations);

    default Texture2DMultiSample setStorage(int samples, InternalPixelFormat pixelFormat, int width, int height) {
        return establish(samples, pixelFormat, width, height, true);
    }

    static Texture2DMultiSample of() {
        return MetaHolder.Texture2DMultiSample.create();
    }

    static HandleArray<Texture2DMultiSample> of(int n) {
        return MetaHolder.Texture2DMultiSample.createArray(n);
    }
}
