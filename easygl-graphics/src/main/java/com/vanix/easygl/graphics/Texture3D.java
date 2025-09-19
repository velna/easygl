package com.vanix.easygl.graphics;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Texture3D extends Texture<Texture3D>,
        TextureOps.GetImage<Texture3D>,
        TextureOps.GetSubImage<Texture3D>,
        TextureOps.GetCompressedImage<Texture3D>,
        TextureOps.GetCompressedSubImage<Texture3D>,
        TextureOps.MakeView,
        TextureOps.Parameters<Texture3D>,
        TextureOps.CopyImageSubData<Texture3D>,
        TextureOps.GenerateMipmap<Texture3D>,
        TextureOps.Load3D<Texture3D>,
        TextureOps.Load3DSub<Texture3D>,
        TextureOps.Copy3DSub<Texture3D>,
        TextureOps.Load3DCompressed<Texture3D>,
        TextureOps.Load3DCompressedSub<Texture3D>,
        TextureOps.SetStorage3D<Texture3D> {
    Texture.TexTarget<Texture3D> Target = new Texture.TexTarget<>(MetaSystem.Graphics.queryInt("TEXTURE_3D"), "Texture3D", MetaHolder.Texture3D);

    static Texture3D of() {
        return MetaHolder.Texture3D.create();
    }

    static HandleArray<Texture3D> of(int n) {
        return MetaHolder.Texture3D.createArray(n);
    }
}
