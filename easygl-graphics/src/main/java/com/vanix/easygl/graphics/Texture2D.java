package com.vanix.easygl.graphics;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Texture2D extends Texture<Texture2D>,
        TextureOps.GetImage<Texture2D>,
        TextureOps.GetSubImage<Texture2D>,
        TextureOps.GetCompressedImage<Texture2D>,
        TextureOps.GetCompressedSubImage<Texture2D>,
        TextureOps.MakeView,
        TextureOps.Parameters<Texture2D>,
        TextureOps.CopyImageSubData<Texture2D>,
        TextureOps.GenerateMipmap<Texture2D>,
        TextureOps.Load2D<Texture2D>,
        TextureOps.Load2DSub<Texture2D>,
        TextureOps.Copy2D<Texture2D>,
        TextureOps.Copy2DSub<Texture2D>,
        TextureOps.Load2DCompressed<Texture2D>,
        TextureOps.Load2DCompressedSub<Texture2D>,
        TextureOps.SetStorage2D<Texture2D> {
    Texture.TexTarget<Texture2D> Target = new Texture.TexTarget<>(MetaSystem.Graphics.queryInt("TEXTURE_2D"), "Texture2D", MetaHolder.Texture2D);

    static Texture2D of() {
        return MetaHolder.Texture2D.create();
    }

    static HandleArray<Texture2D> of(int n) {
        return MetaHolder.Texture2D.createArray(n);
    }
}
