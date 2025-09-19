package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.window.input.Keyboard;
import com.vanix.easygl.window.input.event.KeyboardEvent;
import com.vanix.easygl.window.input.event.KeyboardListener;
import com.vanix.easygl.window.Window;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

public class GlKeyboard implements Keyboard {
    private final ListenerSupport<KeyboardListener> listenerSupport;
    private final Window window;

    public GlKeyboard(GlWindow window) {
        this.window = window;
        listenerSupport = window.newListenerSupport(GLFW.GLFW_KEY_LAST + 1, GLFW::glfwSetKeyCallback, this::onKey);
    }

    private void onKey(long window, int key, int scancode, int action, int mods) {
        KeyboardEvent event = new KeyboardEvent(this, Cache.keyOfCode(key, scancode), Cache.actionOfCode(action), scancode, mods);
        Consumer<KeyboardListener> consumer = l -> l.keyboardOnKey(event);
        listenerSupport.forEach(0, consumer);
        listenerSupport.forEach(key, consumer);
    }

    @Override
    public Action actionOf(Key input) {
        return Cache.actionOfCode(GLFW.glfwGetKey(window.handle(), input.code()));
    }

    @Override
    public ListenerOperation<KeyboardListener> onKey(Key... keys) {
        return listenerSupport.listen(Key::code, keys);
    }

    @Override
    public Window window() {
        return window;
    }

    @Override
    public boolean stickKeys() {
        int ret = GLFW.glfwGetInputMode(window.handle(), GLFW.GLFW_STICKY_KEYS);
        return ret == GLFW.GLFW_TRUE;
    }

    @Override
    public Keyboard stickKeys(boolean value) {
        GLFW.glfwSetInputMode(window.handle(), GLFW.GLFW_STICKY_KEYS, value ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
        return this;
    }

    @Override
    public boolean lockModifiers() {
        int ret = GLFW.glfwGetInputMode(window.handle(), GLFW.GLFW_LOCK_KEY_MODS);
        return ret == GLFW.GLFW_TRUE;
    }

    @Override
    public Keyboard lockModifiers(boolean value) {
        GLFW.glfwSetInputMode(window.handle(), GLFW.GLFW_LOCK_KEY_MODS, value ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
        return this;
    }
}
