package com.vanix.easygl.opengl;

import org.lwjgl.opengl.GL45C;

public class GLX extends GL45C {
    public static final int GL_BLEND_COLOR = 0x8005;

    protected GLX() {
    }

    public static void checkError() {
        int errno = GLX.glGetError();
        if (errno != GLX.GL_NO_ERROR) {
            throw new IllegalStateException(switch (errno) {
                case GLX.GL_INVALID_ENUM -> "INVALID_ENUM";
                case GLX.GL_INVALID_OPERATION -> "INVALID_OPERATION";
                case GLX.GL_INVALID_VALUE -> "INVALID_VALUE";
                case GLX.GL_STACK_OVERFLOW -> "STACK_OVERFLOW";
                case GLX.GL_STACK_UNDERFLOW -> "STACK_UNDERFLOW";
                case GLX.GL_INVALID_FRAMEBUFFER_OPERATION -> "INVALID_FRAMEBUFFER_OPERATION";
                case GLX.GL_OUT_OF_MEMORY -> "OUT_OF_MEMORY";
                default -> "UNKNOWN_ERROR";
            });
        }
    }
}
