package com.vanix.easygl.window.input;

import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.window.input.event.KeyboardListener;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Keyboard extends InputDevice<Keyboard.Key> {

    boolean stickKeys();

    Keyboard stickKeys(boolean value);

    boolean lockModifiers();

    Keyboard lockModifiers(boolean value);

    ListenerOperation<KeyboardListener> onKey(Key... keys);

    @Override
    default Type type() {
        return Type.Keyboard;
    }

    interface Key extends Input {
        int scancode();

        String keyName();

        boolean isPrintableKey();

        boolean isFunctionKey();
    }

    class UnknownKey implements Key {
        private final int scancode;

        public UnknownKey(int scancode) {
            this.scancode = scancode;
        }

        @Override
        public int code() {
            return -1;
        }

        @Override
        public int scancode() {
            return scancode;
        }

        @Override
        public String keyName() {
            return null;
        }

        @Override
        public boolean isPrintableKey() {
            return false;
        }

        @Override
        public boolean isFunctionKey() {
            return false;
        }
    }

    enum PrintableKey implements Key {
        SPACE("KEY_SPACE"),
        APOSTROPHE("KEY_APOSTROPHE"),
        COMMA("KEY_COMMA"),
        MINUS("KEY_MINUS"),
        PERIOD("KEY_PERIOD"),
        SLASH("KEY_SLASH"),
        N0("KEY_0"),
        N1("KEY_1"),
        N2("KEY_2"),
        N3("KEY_3"),
        N4("KEY_4"),
        N5("KEY_5"),
        N6("KEY_6"),
        N7("KEY_7"),
        N8("KEY_8"),
        N9("KEY_9"),
        SEMICOLON("KEY_SEMICOLON"),
        EQUAL("KEY_EQUAL"),
        A("KEY_A"),
        B("KEY_B"),
        C("KEY_C"),
        D("KEY_D"),
        E("KEY_E"),
        F("KEY_F"),
        G("KEY_G"),
        H("KEY_H"),
        I("KEY_I"),
        J("KEY_J"),
        K("KEY_K"),
        L("KEY_L"),
        M("KEY_M"),
        N("KEY_N"),
        O("KEY_O"),
        P("KEY_P"),
        Q("KEY_Q"),
        R("KEY_R"),
        S("KEY_S"),
        T("KEY_T"),
        U("KEY_U"),
        V("KEY_V"),
        W("KEY_W"),
        X("KEY_X"),
        Y("KEY_Y"),
        Z("KEY_Z"),
        LEFT_BRACKET("KEY_LEFT_BRACKET"),
        BACKSLASH("KEY_BACKSLASH"),
        RIGHT_BRACKET("KEY_RIGHT_BRACKET"),
        GRAVE_ACCENT("KEY_GRAVE_ACCENT"),
        WORLD_1("KEY_WORLD_1");
//        WORLD_2("KEY_WORLD_2"); // Reports error on MacOS, scancode returns -1

        private final int value;
        private final int scancode;
        private final String keyName;

        PrintableKey(String id) {
            Object[] array = MetaSystem.Window.queryArray(id);
            this.value = (Integer) array[0];
            this.scancode = (Integer) array[1];
            this.keyName = (String) array[2];
        }

        @Override
        public int code() {
            return value;
        }

        @Override
        public int scancode() {
            return scancode;
        }

        @Override
        public String keyName() {
            return keyName;
        }

        @Override
        public boolean isPrintableKey() {
            return true;
        }

        @Override
        public boolean isFunctionKey() {
            return false;
        }
    }

    enum FunctionKey implements Key {
        ESCAPE("KEY_ESCAPE"),
        ENTER("KEY_ENTER"),
        TAB("KEY_TAB"),
        BACKSPACE("KEY_BACKSPACE"),
        INSERT("KEY_INSERT"),
        DELETE("KEY_DELETE"),
        RIGHT("KEY_RIGHT"),
        LEFT("KEY_LEFT"),
        DOWN("KEY_DOWN"),
        UP("KEY_UP"),
        PAGE_UP("KEY_PAGE_UP"),
        PAGE_DOWN("KEY_PAGE_DOWN"),
        HOME("KEY_HOME"),
        END("KEY_END"),
        CAPS_LOCK("KEY_CAPS_LOCK"),
        SCROLL_LOCK("KEY_SCROLL_LOCK"),
        NUM_LOCK("KEY_NUM_LOCK"),
        PRINT_SCREEN("KEY_PRINT_SCREEN"),
        PAUSE("KEY_PAUSE"),
        F1("KEY_F1"),
        F2("KEY_F2"),
        F3("KEY_F3"),
        F4("KEY_F4"),
        F5("KEY_F5"),
        F6("KEY_F6"),
        F7("KEY_F7"),
        F8("KEY_F8"),
        F9("KEY_F9"),
        F10("KEY_F10"),
        F11("KEY_F11"),
        F12("KEY_F12"),
        F13("KEY_F13"),
        F14("KEY_F14"),
        F15("KEY_F15"),
        F16("KEY_F16"),
        F17("KEY_F17"),
        F18("KEY_F18"),
        F19("KEY_F19"),
        F20("KEY_F20"),
        F21("KEY_F21"),
        F22("KEY_F22"),
        F23("KEY_F23"),
        F24("KEY_F24"),
        F25("KEY_F25"),
        KP_0("KEY_KP_0"),
        KP_1("KEY_KP_1"),
        KP_2("KEY_KP_2"),
        KP_3("KEY_KP_3"),
        KP_4("KEY_KP_4"),
        KP_5("KEY_KP_5"),
        KP_6("KEY_KP_6"),
        KP_7("KEY_KP_7"),
        KP_8("KEY_KP_8"),
        KP_9("KEY_KP_9"),
        KP_DECIMAL("KEY_KP_DECIMAL"),
        KP_DIVIDE("KEY_KP_DIVIDE"),
        KP_MULTIPLY("KEY_KP_MULTIPLY"),
        KP_SUBTRACT("KEY_KP_SUBTRACT"),
        KP_ADD("KEY_KP_ADD"),
        KP_ENTER("KEY_KP_ENTER"),
        KP_EQUAL("KEY_KP_EQUAL"),
        LEFT_SHIFT("KEY_LEFT_SHIFT"),
        LEFT_CONTROL("KEY_LEFT_CONTROL"),
        LEFT_ALT("KEY_LEFT_ALT"),
        LEFT_SUPER("KEY_LEFT_SUPER"),
        RIGHT_SHIFT("KEY_RIGHT_SHIFT"),
        RIGHT_CONTROL("KEY_RIGHT_CONTROL"),
        RIGHT_ALT("KEY_RIGHT_ALT"),
        RIGHT_SUPER("KEY_RIGHT_SUPER"),
        MENU("KEY_MENU"),
        LAST("KEY_LAST");
        private final int value;
        private final int scancode;
        private final String keyName;

        FunctionKey(String id) {
            Object[] array = MetaSystem.Window.queryArray(id);
            this.value = (Integer) array[0];
            this.scancode = (Integer) array[1];
            this.keyName = (String) array[2];
        }

        @Override
        public int code() {
            return value;
        }

        @Override
        public int scancode() {
            return scancode;
        }

        @Override
        public String keyName() {
            return keyName;
        }

        @Override
        public boolean isPrintableKey() {
            return false;
        }

        @Override
        public boolean isFunctionKey() {
            return true;
        }
    }

}
