package com.vanix.easygl.opengl;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.graphics.InternalPixelFormat;
import com.vanix.easygl.graphics.Access;
import com.vanix.easygl.graphics.ImageUnit;
import com.vanix.easygl.graphics.Texture;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

public class GlImageUnit extends ImageUnit {
    public GlImageUnit(int index) {
        super(index);
    }

    @Override
    public ImageUnit bindTexture(Texture<?> texture, int level, boolean layered, int layer, Access access, InternalPixelFormat.Sized format) {
        GLX.glBindImageTexture(value, texture.intHandle(), level, layered, layer, access.value(), format.value());
        GLX.checkError();
        return this;
    }

    @Override
    public ImageUnit bindTextures(Iterable<Texture<?>> textures) {
        if (textures instanceof HandleArray<Texture<?>> handleArray) {
            GLX.glBindImageTextures(value, handleArray.getHandles());
        } else {
            try (MemoryStack stack = MemoryStack.stackPush()) {
                IntBuffer buffer = stack.mallocInt(MAX_IMAGE_UNITS);
                for (var texture : textures) {
                    buffer.put(texture.intHandle());
                }
                GLX.glBindImageTextures(value, buffer.flip());
            }
        }
        GLX.checkError();
        return this;
    }

    @Override
    public Texture<?> bindingTexture() {
        return AbstractTexture.get(GLX.glGetIntegeri(GLX.GL_IMAGE_BINDING_NAME, value));
    }

    @Override
    public int bindingLevel() {
        return GLX.glGetIntegeri(GLX.GL_IMAGE_BINDING_LEVEL, value);
    }

    @Override
    public boolean isBindingLayered() {
        return GLX.glGetBooleani(GLX.GL_IMAGE_BINDING_LAYERED, value);
    }

    @Override
    public Access bindingAccess() {
        return Cache.Access.valueOf(GLX.glGetIntegeri(GLX.GL_IMAGE_BINDING_ACCESS, value));
    }

    @Override
    public InternalPixelFormat.Sized bindingFormat() {
        return (InternalPixelFormat.Sized) Cache.InternalPixelFormatCache.get(GLX.glGetIntegeri(GLX.GL_IMAGE_BINDING_FORMAT, value));
    }
}
