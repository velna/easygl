package com.vanix.easygl.graphics;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.meta.MetaSystem;

public interface TextureRectangle extends Texture<TextureRectangle>,
        TextureOps.GetImage<TextureRectangle>,
        TextureOps.GetSubImage<TextureRectangle>,
        TextureOps.GetCompressedImage<TextureRectangle>,
        TextureOps.GetCompressedSubImage<TextureRectangle>,
        TextureOps.MakeView,
        TextureOps.Parameters<TextureRectangle>,
        TextureOps.CopyImageSubData<TextureRectangle>,
        TextureOps.Copy2DSub<TextureRectangle>,
        TextureOps.SetStorage2D<TextureRectangle> {
    Texture.TexTarget<TextureRectangle> Target = new Texture.TexTarget<>(
            MetaSystem.Graphics.queryInt("TEXTURE_RECTANGLE"), "TextureRectangle", MetaHolder.TextureRectangle);

    static TextureRectangle of() {
        return MetaHolder.TextureRectangle.create();
    }

    static HandleArray<TextureRectangle> of(int n) {
        return MetaHolder.TextureRectangle.createArray(n);
    }

}
