package com.vanix.easygl.window.input;

import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.primitives.Positiond;
import com.vanix.easygl.commons.primitives.Positiondc;
import com.vanix.easygl.window.input.event.MouseButtonListener;
import com.vanix.easygl.window.input.event.MouseEnterListener;
import com.vanix.easygl.window.input.event.MouseMoveListener;
import com.vanix.easygl.window.input.event.MouseScrollListener;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Mouse extends Positiond<Mouse>, InputDevice<Mouse.Button> {

    Mouse cursor(Cursor cursor);

    Mouse cursorMode(CursorMode mode);

    CursorMode cursorMode();

    boolean stickButtons();

    Mouse stickButtons(boolean value);

    boolean isRawMotionSupported();

    boolean rawMotion();

    Mouse rawMotion(boolean enable);

    ListenerOperation<MouseButtonListener> onButton(Button... buttons);

    ListenerOperation<MouseScrollListener> onScroll();

    ListenerOperation<MouseMoveListener> onMove();

    ListenerOperation<MouseEnterListener> onEnter();

    Positiondc<?> getPosition();

    @Override
    default Type type() {
        return Type.Mouse;
    }

    enum Button implements Input {
        BTN1("MOUSE_BUTTON_1"),
        BTN2("MOUSE_BUTTON_2"),
        BTN3("MOUSE_BUTTON_3"),
        BTN4("MOUSE_BUTTON_4"),
        BTN5("MOUSE_BUTTON_5"),
        BTN6("MOUSE_BUTTON_6"),
        BTN7("MOUSE_BUTTON_7"),
        BTN8("MOUSE_BUTTON_8"),
        LAST("MOUSE_BUTTON_LAST"),
        LEFT("MOUSE_BUTTON_LEFT"),
        RIGHT("MOUSE_BUTTON_RIGHT"),
        MIDDLE("MOUSE_BUTTON_MIDDLE");

        private final int code;

        Button(String id) {
            this.code = MetaSystem.Window.queryInt(id);
        }

        @Override
        public int code() {
            return code;
        }

    }

    enum CursorMode {
        UNKNOWN(-1), //
        CURSOR_NORMAL("CURSOR_NORMAL"), //
        CURSOR_HIDDEN("CURSOR_HIDDEN"), //
        CURSOR_DISABLED("CURSOR_DISABLED"), //
        CURSOR_CAPTURED("CURSOR_CAPTURED");

        private final int value;

        CursorMode(int value) {
            this.value = value;
        }

        CursorMode(String id) {
            this(MetaSystem.Window.queryInt(id));
        }

        public int value() {
            return value;
        }

        public static CursorMode valueOf(int v) {
            for (CursorMode mode : CursorMode.values()) {
                if (mode.value == v) {
                    return mode;
                }
            }
            return UNKNOWN;
        }
    }

}
