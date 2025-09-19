package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.meta.MetaSystem;

public interface TextureCubeMap extends Texture<TextureCubeMap>,
        TextureOps.GetSubImage<TextureCubeMap>,
        TextureOps.GetCompressedSubImage<TextureCubeMap>,
        TextureOps.MakeView,
        TextureOps.Parameters<TextureCubeMap>,
        TextureOps.CopyImageSubData<TextureCubeMap>,
        TextureOps.GenerateMipmap<TextureCubeMap>,
        TextureOps.SetStorage2D<TextureCubeMap> {
    Texture.TexTarget<TextureCubeMap> Target = new Texture.TexTarget<>(MetaSystem.Graphics.queryInt("TEXTURE_CUBE_MAP"), "TextureCube", MetaHolder.TextureCubeMap);

    default TextureCubeMap load(String... resources) {
        Image[] images = new Image[resources.length];
        try {
            for (int i = 0; i < resources.length; i++) {
                images[i] = Image.load(resources[i]);
            }
            return load(images);
        } finally {
            for (var image : images) {
                if (image != null) {
                    image.close();
                }
            }
        }
    }


    TextureCubeMap load(Image... images);

    Face getFace(FaceTarget target);

    static TextureCubeMap of() {
        return MetaHolder.TextureCubeMap.create();
    }

    static HandleArray<TextureCubeMap> of(int n) {
        return MetaHolder.TextureCubeMap.createArray(n);
    }

    interface Face extends
            TextureOps.GetImage<Face>,
            TextureOps.GetCompressedImage<Face>,
            TextureOps.Load2D<Face>,
            TextureOps.Load2DSub<Face>,
            TextureOps.Copy2D<Face>,
            TextureOps.Copy2DSub<Face>,
            TextureOps.Load2DCompressed<Face>,
            TextureOps.Load2DCompressedSub<Face> {
        TextureCubeMap cubeMap();

        Face proxy();

        FaceTarget target();
    }

    enum FaceTarget implements IntEnum {
        PositiveX("TEXTURE_CUBE_MAP_POSITIVE_X"),
        NegativeX("TEXTURE_CUBE_MAP_NEGATIVE_X"),
        PositiveY("TEXTURE_CUBE_MAP_POSITIVE_Y"),
        NegativeY("TEXTURE_CUBE_MAP_NEGATIVE_Y"),
        PositiveZ("TEXTURE_CUBE_MAP_POSITIVE_Z"),
        NegativeZ("TEXTURE_CUBE_MAP_NEGATIVE_Z");
        private final int value;

        FaceTarget(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }
}
