package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.commons.primitives.Positiondc;
import com.vanix.easygl.window.input.Cursor;
import com.vanix.easygl.window.input.Mouse;
import com.vanix.easygl.window.input.event.*;
import com.vanix.easygl.window.Window;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import java.nio.DoubleBuffer;

public class GlMouse implements Mouse {
    private final ListenerSupport<MouseScrollListener> scrollListeners;
    private final ListenerSupport<MouseMoveListener> moveListeners;
    private final ListenerSupport<MouseButtonListener> buttonListeners;
    private final ListenerSupport<MouseEnterListener> enterListeners;
    private final Window window;
    private final DoubleBuffer xPosBuf = BufferUtils.createDoubleBuffer(1);
    private final long xPosAddress = MemoryUtil.memAddressSafe(xPosBuf);
    private final DoubleBuffer yPosBuf = BufferUtils.createDoubleBuffer(1);
    private final long yPosAddress = MemoryUtil.memAddressSafe(yPosBuf);
    private final boolean rawMotionSupported;

    public GlMouse(GlWindow window) {
        this.window = window;
//        yaw.addInterceptor((oldV, newV) -> newV % 360.0f);
        moveListeners = window.newListenerSupport(1, GLFW::glfwSetCursorPosCallback, this::moveCallback);
        scrollListeners = window.newListenerSupport(1, GLFW::glfwSetScrollCallback, this::scrollCallback);
        enterListeners = window.newListenerSupport(1, GLFW::glfwSetCursorEnterCallback, this::enterCallback);
        buttonListeners = window.newListenerSupport(
                GLFW.GLFW_MOUSE_BUTTON_LAST + 1,
                GLFW::glfwSetMouseButtonCallback,
                this::buttonCallback);
        rawMotionSupported = GLFW.glfwRawMouseMotionSupported();
    }

    private void buttonCallback(long window, int button, int action, int mods) {
        MouseButtonEvent event = new MouseButtonEvent(
                this, Cache.buttonOfCode(button), Cache.actionOfCode(action), mods);
        buttonListeners.forEach(button, l -> l.mouseOnButton(event));
    }

    private void moveCallback(long window, double xpos, double ypos) {
        MouseMoveEvent event = new MouseMoveEvent(this, xpos, ypos);
        moveListeners.forEach(0, l -> l.mouseOnMove(event));
    }

    private void scrollCallback(long window, double xOffset, double yOffset) {
        MouseScrollEvent event = new MouseScrollEvent(this, xOffset, yOffset);
        scrollListeners.forEach(0, l -> l.mouseOnScroll(event));
    }

    private void enterCallback(long window, boolean enter) {
        var event = new MouseEnterEvent(this, enter);
        enterListeners.forEach(0, l -> l.mouseOnEnter(event));
    }

    @Override
    public Action actionOf(Button input) {
        return Cache.actionOfCode(GLFW.glfwGetMouseButton(window.handle(), input.code()));
    }

    @Override
    public CursorMode cursorMode() {
        int mode = GLFW.glfwGetInputMode(window.handle(), GLFW.GLFW_CURSOR);
        return CursorMode.valueOf(mode);
    }

    @Override
    public Mouse cursorMode(CursorMode mode) {
        GLFW.glfwSetInputMode(window.handle(), GLFW.GLFW_CURSOR, mode.value());
        return this;
    }

    @Override
    public ListenerOperation<MouseButtonListener> onButton(Button... buttons) {
        return buttonListeners.listen(Button::code, buttons);
    }

    @Override
    public ListenerOperation<MouseMoveListener> onMove() {
        return moveListeners.listen();
    }

    @Override
    public ListenerOperation<MouseScrollListener> onScroll() {
        return scrollListeners.listen();
    }

    @Override
    public ListenerOperation<MouseEnterListener> onEnter() {
        return enterListeners.listen();
    }

    @Override
    public Window window() {
        return window;
    }

    @Override
    public double getX() {
        GLFW.nglfwGetCursorPos(window.handle(), xPosAddress, MemoryUtil.NULL);
        return xPosBuf.get();
    }

    @Override
    public double getY() {
        GLFW.nglfwGetCursorPos(window.handle(), MemoryUtil.NULL, yPosAddress);
        return yPosBuf.get();
    }

    @Override
    public Positiondc<?> getPosition() {
        GLFW.nglfwGetCursorPos(window.handle(), xPosAddress, yPosAddress);
        return Positiondc.of(xPosBuf.get(0), yPosBuf.get(0));
    }

    @Override
    public Mouse setPosition(Positiondc<?> position) {
        GLFW.glfwSetCursorPos(window.handle(), position.getX(), position.getY());
        return this;
    }

    @Override
    public Mouse setX(double x) {
        GLFW.glfwSetCursorPos(window.handle(), x, yPosBuf.get(0));
        return this;
    }

    @Override
    public Mouse setY(double y) {
        GLFW.glfwSetCursorPos(window.handle(), xPosBuf.get(0), y);
        return this;
    }

    @Override
    public boolean isRawMotionSupported() {
        return rawMotionSupported;
    }

    @Override
    public boolean rawMotion() {
        return rawMotionSupported && GLFW.glfwGetInputMode(window.handle(), GLFW.GLFW_RAW_MOUSE_MOTION) == GLFW.GLFW_TRUE;
    }

    @Override
    public Mouse rawMotion(boolean enable) {
        if (rawMotionSupported) {
            GLFW.glfwSetInputMode(window.handle(), GLFW.GLFW_RAW_MOUSE_MOTION, enable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
        }
        return this;
    }

    @Override
    public Mouse cursor(Cursor cursor) {
        GLFW.glfwSetCursor(window.handle(), cursor.handle());
        return this;
    }

    @Override
    public boolean stickButtons() {
        int ret = GLFW.glfwGetInputMode(window.handle(), GLFW.GLFW_STICKY_MOUSE_BUTTONS);
        return ret == GLFW.GLFW_TRUE;
    }

    @Override
    public Mouse stickButtons(boolean value) {
        GLFW.glfwSetInputMode(window.handle(), GLFW.GLFW_STICKY_MOUSE_BUTTONS, value ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
        return this;
    }
}
