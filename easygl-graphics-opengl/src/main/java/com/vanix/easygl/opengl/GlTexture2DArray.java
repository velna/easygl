package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.Texture2DArray;

public class GlTexture2DArray extends AbstractTexture<Texture2DArray> implements Texture2DArray,
        GlTextureOps.GetImage<Texture2DArray>,
        GlTextureOps.GetSubImage<Texture2DArray>,
        GlTextureOps.GetCompressedImage<Texture2DArray>,
        GlTextureOps.GetCompressedSubImage<Texture2DArray>,
        GlTextureOps.Parameters<Texture2DArray>,
        GlTextureOps.CopyImageSubData<Texture2DArray>,
        GlTextureOps.MakeView<Texture2DArray>,
        GlTextureOps.GenerateMipmap<Texture2DArray>,
        GlTextureOps.Load3D<Texture2DArray>,
        GlTextureOps.Load3DSub<Texture2DArray>,
        GlTextureOps.Copy3DSub<Texture2DArray>,
        GlTextureOps.Load3DCompressed<Texture2DArray>,
        GlTextureOps.Load3DCompressedSub<Texture2DArray>,
        GlTextureOps.SetStorage3D<Texture2DArray> {
    public GlTexture2DArray() {
        this(GLX.glGenTextures());
    }

    public GlTexture2DArray(int handle) {
        super(handle, Target);
    }

    private GlTexture2DArray(int handle, TexTarget<Texture2DArray> target) {
        super(handle, target);
    }

    @Override
    public Texture2DArray proxy() {
        return new GlTexture2DArray(intHandle(), target.proxy(GLX.GL_PROXY_TEXTURE_2D_ARRAY));
    }

}
