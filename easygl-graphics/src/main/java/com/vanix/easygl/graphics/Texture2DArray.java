package com.vanix.easygl.graphics;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Texture2DArray extends Texture<Texture2DArray>,
        TextureOps.GetImage<Texture2DArray>,
        TextureOps.GetSubImage<Texture2DArray>,
        TextureOps.GetCompressedImage<Texture2DArray>,
        TextureOps.GetCompressedSubImage<Texture2DArray>,
        TextureOps.MakeView,
        TextureOps.Parameters<Texture2DArray>,
        TextureOps.CopyImageSubData<Texture2DArray>,
        TextureOps.GenerateMipmap<Texture2DArray>,
        TextureOps.Load3D<Texture2DArray>,
        TextureOps.Load3DSub<Texture2DArray>,
        TextureOps.Copy3DSub<Texture2DArray>,
        TextureOps.Load3DCompressed<Texture2DArray>,
        TextureOps.Load3DCompressedSub<Texture2DArray>,
        TextureOps.SetStorage3D<Texture2DArray> {
    Texture.TexTarget<Texture2DArray> Target = new Texture.TexTarget<>(
            MetaSystem.Graphics.queryInt("TEXTURE_2D_ARRAY"), "Texture2DArray", MetaHolder.Texture2DArray);

    static Texture2DArray of() {
        return MetaHolder.Texture2DArray.create();
    }

    static HandleArray<Texture2DArray> of(int n) {
        return MetaHolder.Texture2DArray.createArray(n);
    }
}
