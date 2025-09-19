package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.attr.AbstractAttribute;
import com.vanix.easygl.commons.attr.BooleanAttribute;
import com.vanix.easygl.commons.attr.UpdatableBooleanAttribute;
import com.vanix.easygl.window.Window;
import com.vanix.easygl.window.WindowAttributes;
import org.lwjgl.glfw.GLFW;

public class GlWindowAttributes extends WindowAttributes {


    public GlWindowAttributes(Window window) {
        super(window);
    }

    @Override
    protected BooleanAttribute ofBoolean(int key) {
        return new WindowBooleanAttribute(key);
    }

    @Override
    protected UpdatableBooleanAttribute<WindowAttributes> ofUpdatableBoolean(int key) {
        return new WindowBooleanAttribute(key);
    }

    private static class AbstractWindowAttribute extends AbstractAttribute {
        AbstractWindowAttribute(int key) {
            super(key);
        }
    }

    private class WindowBooleanAttribute extends AbstractWindowAttribute implements UpdatableBooleanAttribute<WindowAttributes> {
        public WindowBooleanAttribute(int key) {
            super(key);
        }

        @Override
        public boolean getAsBoolean() {
            return GLFW.glfwGetWindowAttrib(owner.handle(), key()) == GLFW.GLFW_TRUE;
        }

        @Override
        public WindowAttributes set(boolean value) {
            GLFW.glfwSetWindowAttrib(owner.handle(), key(), value ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
            return GlWindowAttributes.this;
        }
    }

}
