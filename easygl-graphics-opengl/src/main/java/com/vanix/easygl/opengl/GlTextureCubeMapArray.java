package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.TextureCubeMapArray;

public class GlTextureCubeMapArray extends AbstractTexture<TextureCubeMapArray> implements TextureCubeMapArray,
        GlTextureOps.GetImage<TextureCubeMapArray>,
        GlTextureOps.GetSubImage<TextureCubeMapArray>,
        GlTextureOps.GetCompressedImage<TextureCubeMapArray>,
        GlTextureOps.GetCompressedSubImage<TextureCubeMapArray>,
        GlTextureOps.Parameters<TextureCubeMapArray>,
        GlTextureOps.CopyImageSubData<TextureCubeMapArray>,
        GlTextureOps.MakeView<TextureCubeMapArray>,
        GlTextureOps.GenerateMipmap<TextureCubeMapArray>,
        GlTextureOps.Copy3DSub<TextureCubeMapArray>,
        GlTextureOps.Load3DCompressedSub<TextureCubeMapArray>,
        GlTextureOps.SetStorage3D<TextureCubeMapArray> {
    public GlTextureCubeMapArray() {
        this(GLX.glGenTextures());
    }

    public GlTextureCubeMapArray(int handle) {
        super(handle, Target);
    }

    private GlTextureCubeMapArray(int handle, TexTarget<TextureCubeMapArray> target) {
        super(handle, target);
    }

    @Override
    public TextureCubeMapArray proxy() {
        return new GlTextureCubeMapArray(intHandle(), target.proxy(GLX.GL_PROXY_TEXTURE_CUBE_MAP_ARRAY));
    }

}
