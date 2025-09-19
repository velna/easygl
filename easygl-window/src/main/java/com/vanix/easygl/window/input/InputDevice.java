package com.vanix.easygl.window.input;

import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.window.Window;

public interface InputDevice<I extends InputDevice.Input> {

    Action actionOf(I input);

    default boolean isPressed(I input) {
        return actionOf(input) == Action.Press;
    }

    Window window();

    Type type();

    interface Input {
        int code();
    }

    enum Type {
        Mouse,
        Keyboard
    }

    enum Action {
        Release("RELEASE"),
        Press("PRESS"),
        Repeat("REPEAT");
        private final int value;

        Action(String id) {
            this.value = MetaSystem.Window.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    enum Modifier {
        Shift("MOD_SHIFT"),
        Control("MOD_CONTROL"),
        Alt("MOD_ALT"),
        Super("MOD_SUPER"),
        CapsLock("MOD_CAPS_LOCK"),
        NumLock("MOD_NUM_LOCK");
        private final int value;

        Modifier(String id) {
            this.value = MetaSystem.Window.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

}
