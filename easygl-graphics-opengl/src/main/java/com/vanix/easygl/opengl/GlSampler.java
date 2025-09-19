package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.core.AbstractHandle;
import com.vanix.easygl.graphics.*;
import org.lwjgl.system.MemoryStack;

import java.util.function.IntConsumer;

public class GlSampler extends AbstractHandle implements Sampler {
    public GlSampler(int handle) {
        super(handle, (IntConsumer) GLX::glDeleteSamplers);
    }

    @Override
    public Sampler compareFunc(CompareFunction func) {
        GLX.glSamplerParameteri(intHandle(), GLX.GL_TEXTURE_COMPARE_FUNC, func.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Sampler compareModeRefToTexture() {
        GLX.glSamplerParameteri(intHandle(), GLX.GL_TEXTURE_COMPARE_MODE, GLX.GL_COMPARE_REF_TO_TEXTURE);
        GLX.checkError();
        return this;
    }

    @Override
    public Sampler compareModeNone() {
        GLX.glSamplerParameteri(intHandle(), GLX.GL_TEXTURE_COMPARE_MODE, GLX.GL_NONE);
        GLX.checkError();
        return this;
    }

    @Override
    public Sampler loadBias(float value) {
        GLX.glSamplerParameterf(intHandle(), GLX.GL_TEXTURE_LOD_BIAS, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Sampler minFilter(MinFilter mf) {
        GLX.glSamplerParameteri(intHandle(), GLX.GL_TEXTURE_MIN_FILTER, mf.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Sampler magFilter(MagFilter mf) {
        GLX.glSamplerParameteri(intHandle(), GLX.GL_TEXTURE_MAG_FILTER, mf.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Sampler minLoad(float value) {
        GLX.glSamplerParameterf(intHandle(), GLX.GL_TEXTURE_MIN_LOD, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Sampler maxLoad(float value) {
        GLX.glSamplerParameterf(intHandle(), GLX.GL_TEXTURE_MAX_LOD, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Sampler wrapS(Texture.Wrap wrap) {
        GLX.glSamplerParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_S, wrap.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Sampler wrapT(Texture.Wrap wrap) {
        GLX.glSamplerParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_T, wrap.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Sampler wrapR(Texture.Wrap wrap) {
        GLX.glSamplerParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_R, wrap.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Sampler borderColor(float red, float green, float blue, float alpha) {
        GLX.glSamplerParameterfv(intHandle(), GLX.GL_TEXTURE_BORDER_COLOR, new float[]{red, green, blue, alpha});
        GLX.checkError();
        return this;
    }

    @Override
    public CompareFunction compareFunc() {
        return Cache.CompareFunction.valueOf(GLX.glGetSamplerParameteri(intHandle(), GLX.GL_TEXTURE_COMPARE_FUNC));
    }

    @Override
    public boolean isCompareModeRefToTexture() {
        return GLX.glGetSamplerParameteri(intHandle(), GLX.GL_TEXTURE_COMPARE_MODE) == GLX.GL_COMPARE_REF_TO_TEXTURE;
    }

    @Override
    public float loadBias() {
        return GLX.glGetSamplerParameteri(intHandle(), GLX.GL_TEXTURE_LOD_BIAS);
    }

    @Override
    public MinFilter minFilter() {
        return Cache.MinFilter.get(GLX.glGetSamplerParameteri(intHandle(), GLX.GL_TEXTURE_MIN_FILTER));
    }

    @Override
    public MagFilter magFilter() {
        return Cache.MagFilter.valueOf(GLX.glGetSamplerParameteri(intHandle(), GLX.GL_TEXTURE_MAG_FILTER));
    }

    @Override
    public float minLoad() {
        return GLX.glGetSamplerParameterf(intHandle(), GLX.GL_TEXTURE_MIN_LOD);
    }

    @Override
    public float maxLoad() {
        return GLX.glGetSamplerParameterf(intHandle(), GLX.GL_TEXTURE_MAX_LOD);
    }

    @Override
    public Texture.Wrap wrapS() {
        return Cache.TextureWrap.valueOf(GLX.glGetSamplerParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_S));
    }

    @Override
    public Texture.Wrap wrapT() {
        return Cache.TextureWrap.valueOf(GLX.glGetSamplerParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_T));
    }

    @Override
    public Texture.Wrap wrapR() {
        return Cache.TextureWrap.valueOf(GLX.glGetSamplerParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_R));
    }

    @Override
    public Color borderColor() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(4);
            GLX.glGetSamplerParameterfv(intHandle(), GLX.GL_TEXTURE_BORDER_COLOR, buffer);
            return new Color(buffer.get(0), buffer.get(1), buffer.get(2), buffer.get(3));
        }
    }

}
