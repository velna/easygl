package com.vanix.easygl.glfw;

import com.vanix.easygl.core.AbstractHandle;
import com.vanix.easygl.window.input.Cursor;
import com.vanix.easygl.core.media.Image;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.system.MemoryStack;

public class GlCursor extends AbstractHandle implements Cursor {
    public GlCursor(StandardShape shape) {
        super(GLFW.glfwCreateStandardCursor(shape.value()), GLFW::glfwDestroyCursor);
    }

    public GlCursor(Image image, int xHotspot, int yHotspot) {
        super(createCursor(image, xHotspot, yHotspot), GLFW::glfwDestroyCursor);
    }

    private static long createCursor(Image image, int xHotspot, int yHotspot) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            var glfwImage = GLFWImage.malloc(stack);
            glfwImage.pixels(image.data())
                    .width(image.width())
                    .height(image.height());
            return GLFW.glfwCreateCursor(glfwImage, xHotspot, yHotspot);
        }
    }
}
