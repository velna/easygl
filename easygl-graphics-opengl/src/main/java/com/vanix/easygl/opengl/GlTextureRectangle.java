package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.TextureRectangle;

public class GlTextureRectangle extends AbstractTexture<TextureRectangle> implements TextureRectangle,
        GlTextureOps.GetImage<TextureRectangle>,
        GlTextureOps.GetSubImage<TextureRectangle>,
        GlTextureOps.GetCompressedImage<TextureRectangle>,
        GlTextureOps.GetCompressedSubImage<TextureRectangle>,
        GlTextureOps.Parameters<TextureRectangle>,
        GlTextureOps.CopyImageSubData<TextureRectangle>,
        GlTextureOps.MakeView<TextureRectangle>,
        GlTextureOps.Copy2DSub<TextureRectangle>,
        GlTextureOps.SetStorage2D<TextureRectangle> {
    public GlTextureRectangle() {
        this(GLX.glGenTextures());
    }

    public GlTextureRectangle(int handle) {
        super(handle, Target);
    }

    private GlTextureRectangle(int handle, TexTarget<TextureRectangle> target) {
        super(handle, target);
    }

    @Override
    public TextureRectangle proxy() {
        return new GlTextureRectangle(intHandle(), target.proxy(GLX.GL_PROXY_TEXTURE_RECTANGLE));
    }

}
