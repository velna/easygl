package com.vanix.easygl.glfw;

import com.vanix.easygl.window.WindowHint;
import org.lwjgl.glfw.GLFW;

import java.nio.ByteBuffer;

public class GlWindowHint implements WindowHint {

    protected final int key;

    public GlWindowHint(int key) {
        this.key = key;
    }

    @Override
    public int key() {
        return key;
    }

    public static class GlIntHint extends GlWindowHint implements IntHint {
        public GlIntHint(int key) {
            super(key);
        }

        @Override
        public void set(int value) {
            GLFW.glfwWindowHint(key, value);
        }

        @Override
        public void doNotCare() {
            GLFW.glfwWindowHint(key, GLFW.GLFW_DONT_CARE);
        }
    }

    public static class GlStringHint extends GlWindowHint implements StringHint {
        public GlStringHint(int key) {
            super(key);
        }

        @Override
        public void set(CharSequence value) {
            GLFW.glfwWindowHintString(key, value);
        }

        @Override
        public void set(ByteBuffer value) {
            GLFW.glfwWindowHintString(key, value);
        }
    }

    public static class GlBooleanHint extends GlWindowHint implements BooleanHint {
        public GlBooleanHint(int key) {
            super(key);
        }

        @Override
        public void set(boolean value) {
            GLFW.glfwWindowHint(key, value ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
        }
    }
}
