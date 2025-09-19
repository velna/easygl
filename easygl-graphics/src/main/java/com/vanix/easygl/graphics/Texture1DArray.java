package com.vanix.easygl.graphics;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Texture1DArray extends Texture<Texture1DArray>,
        TextureOps.GetImage<Texture1DArray>,
        TextureOps.GetSubImage<Texture1DArray>,
        TextureOps.GetCompressedImage<Texture1DArray>,
        TextureOps.GetCompressedSubImage<Texture1DArray>,
        TextureOps.MakeView,
        TextureOps.Parameters<Texture1DArray>,
        TextureOps.CopyImageSubData<Texture1DArray>,
        TextureOps.GenerateMipmap<Texture1DArray>,
        TextureOps.Load2D<Texture1DArray>,
        TextureOps.Load2DSub<Texture1DArray>,
        TextureOps.Copy2DSub<Texture1DArray>,
        TextureOps.Load2DCompressed<Texture1DArray>,
        TextureOps.Load2DCompressedSub<Texture1DArray>,
        TextureOps.SetStorage2D<Texture1DArray> {
    Texture.TexTarget<Texture1DArray> Target = new Texture.TexTarget<>(
            MetaSystem.Graphics.queryInt("TEXTURE_1D_ARRAY"), "Texture1DArray", MetaHolder.Texture1DArray);

    static Texture1DArray of() {
        return MetaHolder.Texture1DArray.create();
    }

    static HandleArray<Texture1DArray> of(int n) {
        return MetaHolder.Texture1DArray.createArray(n);
    }
}
