package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.attr.AbstractAttribute;
import com.vanix.easygl.commons.attr.UpdatableAnyPrimitiveAttribute;
import com.vanix.easygl.commons.attr.UpdatableBooleanAttribute;
import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.PixelStorageMode;

public class GlPixelStorageMode extends PixelStorageMode {
    public GlPixelStorageMode(Graphics graphics) {
        super(graphics);
    }

    @Override
    protected UpdatableBooleanAttribute<PixelStorageMode> ofUpdatableBoolean(int key) {
        return new Attribute(key);
    }

    @Override
    protected UpdatableAnyPrimitiveAttribute<PixelStorageMode> ofUpdatableAny(int key) {
        return new Attribute(key);
    }

    private class Attribute extends AbstractAttribute implements UpdatableAnyPrimitiveAttribute<PixelStorageMode> {
        public Attribute(int key) {
            super(key);
        }

        @Override
        public PixelStorageMode set(boolean value) {
            GLX.glPixelStorei(key, value ? GLX.GL_TRUE : GLX.GL_FALSE);
            return GlPixelStorageMode.this;
        }

        @Override
        public PixelStorageMode set(int value) {
            GLX.glPixelStorei(key, value);
            return GlPixelStorageMode.this;
        }

        @Override
        public PixelStorageMode set(float value) {
            GLX.glPixelStoref(key, value);
            return GlPixelStorageMode.this;
        }

        @Override
        public float getAsFloat() {
            return GLX.glGetFloat(key);
        }

        @Override
        public boolean getAsBoolean() {
            return GLX.glGetBoolean(key);
        }

        @Override
        public int getAsInt() {
            return GLX.glGetInteger(key);
        }
    }
}
