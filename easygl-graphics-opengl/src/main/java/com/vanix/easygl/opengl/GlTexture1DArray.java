package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.Texture1DArray;

public class GlTexture1DArray extends AbstractTexture<Texture1DArray> implements Texture1DArray,
        GlTextureOps.GetImage<Texture1DArray>,
        GlTextureOps.GetSubImage<Texture1DArray>,
        GlTextureOps.GetCompressedImage<Texture1DArray>,
        GlTextureOps.GetCompressedSubImage<Texture1DArray>,
        GlTextureOps.CopyImageSubData<Texture1DArray>,
        GlTextureOps.Parameters<Texture1DArray>,
        GlTextureOps.MakeView<Texture1DArray>,
        GlTextureOps.GenerateMipmap<Texture1DArray>,
        GlTextureOps.Load2D<Texture1DArray>,
        GlTextureOps.Load2DSub<Texture1DArray>,
        GlTextureOps.Copy2DSub<Texture1DArray>,
        GlTextureOps.Load2DCompressed<Texture1DArray>,
        GlTextureOps.Load2DCompressedSub<Texture1DArray>,
        GlTextureOps.SetStorage2D<Texture1DArray> {
    public GlTexture1DArray() {
        this(GLX.glGenTextures());
    }

    public GlTexture1DArray(int handle) {
        super(handle, Target);
    }

    private GlTexture1DArray(int handle, TexTarget<Texture1DArray> target) {
        super(handle, target);
    }

    @Override
    public Texture1DArray proxy() {
        return new GlTexture1DArray(intHandle(), target.proxy(GLX.GL_PROXY_TEXTURE_1D_ARRAY));
    }

}
