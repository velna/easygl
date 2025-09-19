package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.InternalPixelFormat;
import com.vanix.easygl.graphics.Buffer;
import com.vanix.easygl.graphics.TextureBuffer;

public class GlTextureBuffer extends AbstractTexture<TextureBuffer> implements TextureBuffer {
    public GlTextureBuffer() {
        this(GLX.glGenTextures());
    }

    public GlTextureBuffer(int handle) {
        super(handle, Target);
    }

    @Override
    public TextureBuffer attach(InternalPixelFormat.Sized format, Buffer buffer) {
        assertBinding();
        GLX.glTexBuffer(target.value(), format.value(), buffer.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public TextureBuffer attach(InternalPixelFormat.Sized format, Buffer buffer, long offset, long size) {
        assertBinding();
        GLX.glTexBufferRange(target.value(), format.value(), buffer.intHandle(), offset, size);
        GLX.checkError();
        return this;
    }

    @Override
    public TextureBuffer proxy() {
        throw new UnsupportedOperationException();
    }

}
