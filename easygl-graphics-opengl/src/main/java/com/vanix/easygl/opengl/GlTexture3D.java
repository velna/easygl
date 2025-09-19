package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.Texture3D;

public class GlTexture3D extends AbstractTexture<Texture3D> implements Texture3D,
        GlTextureOps.GetImage<Texture3D>,
        GlTextureOps.GetSubImage<Texture3D>,
        GlTextureOps.GetCompressedImage<Texture3D>,
        GlTextureOps.GetCompressedSubImage<Texture3D>,
        GlTextureOps.Parameters<Texture3D>,
        GlTextureOps.CopyImageSubData<Texture3D>,
        GlTextureOps.MakeView<Texture3D>,
        GlTextureOps.GenerateMipmap<Texture3D>,
        GlTextureOps.Load3D<Texture3D>,
        GlTextureOps.Load3DSub<Texture3D>,
        GlTextureOps.Copy3DSub<Texture3D>,
        GlTextureOps.Load3DCompressed<Texture3D>,
        GlTextureOps.Load3DCompressedSub<Texture3D>,
        GlTextureOps.SetStorage3D<Texture3D> {
    public GlTexture3D() {
        this(GLX.glGenTextures());
    }

    public GlTexture3D(int handle) {
        super(handle, Target);
    }

    private GlTexture3D(int handle, TexTarget<Texture3D> target) {
        super(handle, target);
    }

    @Override
    public Texture3D proxy() {
        return new GlTexture3D(intHandle(), target.proxy(GLX.GL_PROXY_TEXTURE_3D));
    }

}
