package com.vanix.easygl.opengl;


import com.vanix.easygl.graphics.Sampler;
import com.vanix.easygl.graphics.Texture;
import com.vanix.easygl.graphics.TextureUnit;

public class GlTextureUnit extends TextureUnit {
    public GlTextureUnit(int index) {
        super(index);
    }

    @Override
    public TextureUnit binTextures(Texture<?>... textures) {
        var handles = new int[textures.length];
        for (int i = 0; i < textures.length; i++) {
            handles[i] = textures[i].intHandle();
        }
        GLX.glBindTextures(index, handles);
        return this;
    }

    @Override
    public TextureUnit bindSampler(Sampler sampler) {
        GLX.glBindSampler(index, sampler.intHandle());
        return this;
    }

    @Override
    public TextureUnit bindSamplers(Sampler... samplers) {
        var handles = new int[samplers.length];
        for (int i = 0; i < samplers.length; i++) {
            handles[i] = samplers[i].intHandle();
        }
        GLX.glBindSamplers(index, handles);
        return this;
    }
}
