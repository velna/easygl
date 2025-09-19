package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.Texture2D;

public class GlTexture2D extends AbstractTexture<Texture2D> implements Texture2D,
        GlTextureOps.GetImage<Texture2D>,
        GlTextureOps.GetSubImage<Texture2D>,
        GlTextureOps.GetCompressedImage<Texture2D>,
        GlTextureOps.GetCompressedSubImage<Texture2D>,
        GlTextureOps.Parameters<Texture2D>,
        GlTextureOps.CopyImageSubData<Texture2D>,
        GlTextureOps.Load2D<Texture2D>,
        GlTextureOps.MakeView<Texture2D>,
        GlTextureOps.GenerateMipmap<Texture2D>,
        GlTextureOps.Load2DSub<Texture2D>,
        GlTextureOps.Copy2D<Texture2D>,
        GlTextureOps.Copy2DSub<Texture2D>,
        GlTextureOps.Load2DCompressed<Texture2D>,
        GlTextureOps.Load2DCompressedSub<Texture2D>,
        GlTextureOps.SetStorage2D<Texture2D> {

    protected GlTexture2D() {
        this(GLX.glGenTextures());
    }

    protected GlTexture2D(int handle) {
        super(handle, Target);
    }

    private GlTexture2D(int handle, TexTarget<Texture2D> target) {
        super(handle, target);
    }

    @Override
    public Texture2D proxy() {
        return new GlTexture2D(intHandle(), target.proxy(GLX.GL_PROXY_TEXTURE_2D));
    }

}
