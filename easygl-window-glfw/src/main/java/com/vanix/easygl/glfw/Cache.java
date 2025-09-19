package com.vanix.easygl.glfw;

import com.vanix.easygl.window.input.InputDevice;
import com.vanix.easygl.window.input.Keyboard;
import com.vanix.easygl.window.input.Mouse;
import com.vanix.easygl.window.WindowErrorCode;
import org.lwjgl.glfw.GLFW;

class Cache {
    private static final Mouse.Button[] BUTTONS = new Mouse.Button[GLFW.GLFW_MOUSE_BUTTON_LAST + 1];
    private static final Keyboard.Key[] KEYS = new Keyboard.Key[GLFW.GLFW_KEY_LAST + 1];
    private static final InputDevice.Action[] ACTIONS = {InputDevice.Action.Release, InputDevice.Action.Press, InputDevice.Action.Repeat};

    private static final WindowErrorCode[] ERROR_CODES = new WindowErrorCode[WindowErrorCode.values().length - 1];

    static {
        for (var key : Keyboard.PrintableKey.values()) {
            KEYS[key.code()] = key;
        }
        for (var key : Keyboard.FunctionKey.values()) {
            KEYS[key.code()] = key;
        }
        for (var button : Mouse.Button.values()) {
            BUTTONS[button.code()] = button;
        }
        for (var errorCode : WindowErrorCode.values()) {
            if (errorCode.code() >= 0) {
                ERROR_CODES[errorCode.code() & 0xf] = errorCode;
            }
        }
    }

    static WindowErrorCode errorCodeOf(int code) {
        int index = code & 0xf;
        if (index >= ERROR_CODES.length) {
            return WindowErrorCode.UnKnown;
        } else {
            return ERROR_CODES[index];
        }
    }

    static InputDevice.Action actionOfCode(int code) {
        return ACTIONS[code];
    }

    static Keyboard.Key keyOfCode(int code, int scancode) {
        if (code < 0 || code >= KEYS.length) {
            return new Keyboard.UnknownKey(scancode);
        }
        return KEYS[code];
    }

    static Mouse.Button buttonOfCode(int code) {
        return BUTTONS[code];
    }
}
