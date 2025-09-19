package com.vanix.easygl.graphics;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.meta.MetaSystem;

public interface TextureCubeMapArray extends Texture<TextureCubeMapArray>,
        TextureOps.GetImage<TextureCubeMapArray>,
        TextureOps.GetSubImage<TextureCubeMapArray>,
        TextureOps.GetCompressedImage<TextureCubeMapArray>,
        TextureOps.GetCompressedSubImage<TextureCubeMapArray>,
        TextureOps.MakeView,
        TextureOps.Parameters<TextureCubeMapArray>,
        TextureOps.CopyImageSubData<TextureCubeMapArray>,
        TextureOps.GenerateMipmap<TextureCubeMapArray>,
        TextureOps.Copy3DSub<TextureCubeMapArray>,
        TextureOps.Load3DCompressedSub<TextureCubeMapArray>,
        TextureOps.SetStorage3D<TextureCubeMapArray> {
    Texture.TexTarget<TextureCubeMapArray> Target = new Texture.TexTarget<>(
            MetaSystem.Graphics.queryInt("TEXTURE_CUBE_MAP_ARRAY"),
            "TextureCubeMapArray",
            MetaHolder.TextureCubeMapArray);

    static TextureCubeMapArray of() {
        return MetaHolder.TextureCubeMapArray.create();
    }

    static HandleArray<TextureCubeMapArray> of(int n) {
        return MetaHolder.TextureCubeMapArray.createArray(n);
    }

}
