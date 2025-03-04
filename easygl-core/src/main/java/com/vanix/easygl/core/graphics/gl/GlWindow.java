package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.commons.event.ListenerKey;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.core.graphics.InputController;
import com.vanix.easygl.core.graphics.Window;
import com.vanix.easygl.core.graphics.event.WindowRefreshListener;
import com.vanix.easygl.core.graphics.event.WindowResizeListener;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

@Slf4j
public class GlWindow implements Window {
    private static final AtomicBoolean INIT = new AtomicBoolean();
    private static final ListenerKey<WindowResizeListener> WindowResizeKey = ListenerKey.of(0);
    private static final ListenerKey<WindowRefreshListener> WindowRefreshKey = ListenerKey.of(1);
    private final long handle;
    private final ListenerSupport listenerSupport = new ListenerSupport();
    private final InputController inputCtlr;

    private int width;
    private int height;

    private int frameBufferWidth;
    private int frameBufferHeight;
    private String title;

    public GlWindow(int width, int height, String title) {
        systemInit();
        this.width = width;
        this.height = height;
        this.title = title;
        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.out));
        this.handle = glfwCreateWindow(width, height, title, NULL, NULL);
        bind();
        GL.createCapabilities();
        var prevCallback = glfwSetFramebufferSizeCallback(handle, this::onWindowResized);
        if (prevCallback != null) {
            prevCallback.close();
        }
        onWindowResized(handle, width, height);
        inputCtlr = new GlInputController(this);
    }

    private void onWindowResized(long window, int width, int height) {
        log.info("window resized to {},{}", width, height);
        this.width = width;
        this.height = height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer bWidth = stack.callocInt(1);
            IntBuffer bHeight = stack.callocInt(1);
            org.lwjgl.glfw.GLFW.glfwGetFramebufferSize(window, bWidth, bHeight);
            frameBufferWidth = bWidth.get();
            frameBufferHeight = bHeight.get();
        }
        listenerSupport.forEach(WindowResizeKey, l -> l.onWindowResized(this));
    }

    @Override
    public Window swapBuffers() {
        glfwSwapBuffers(handle);
        return this;
    }

    @Override
    public Window pollEvents() {
        glfwPollEvents();
        return this;
    }

    @Override
    public InputController inputCtlr() {
        return inputCtlr;
    }

    @Override
    public long nativeHandle() {
        return handle;
    }

    @Override
    public boolean shouldClose() {
        return glfwWindowShouldClose(handle);
    }

    @Override
    public void shouldClose(boolean close) {
        glfwSetWindowShouldClose(handle, close);
    }

    @Override
    public Window bind() {
        glfwMakeContextCurrent(handle);
        return this;
    }

    @Override
    public Window unbind() {
        glfwMakeContextCurrent(NULL);
        return this;
    }

    @Override
    public Window assertBinding() throws IllegalStateException {
        if (glfwGetCurrentContext() != handle) {
            throw new IllegalStateException("Window not bind.");
        }
        return this;
    }

    @Override
    public void dispose() {
        glfwDestroyWindow(handle);
    }

    @Override
    public ListenerOperation<WindowResizeListener> onResize() {
        return listenerSupport.of(WindowResizeKey);
    }

    @Override
    public ListenerOperation<WindowRefreshListener> onRefresh() {
        return listenerSupport.of(WindowRefreshKey);
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public int frameBufferWidth() {
        return frameBufferWidth;
    }

    @Override
    public int frameBufferHeight() {
        return frameBufferHeight;
    }

    public static void systemInit() {
        if (INIT.compareAndSet(false, true)) {
            glfwInit();
        }
    }

    public static void systemTerminate() {
        if (INIT.compareAndSet(true, false)) {
            glfwTerminate();
        }
    }
}
