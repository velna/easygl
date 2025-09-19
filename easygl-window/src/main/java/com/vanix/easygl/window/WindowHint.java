package com.vanix.easygl.window;


import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.ByteBuffer;

public interface WindowHint {

    static BooleanHint ofBoolean(String id) {
        return newHint(BooleanHint.class, id);
    }

    static IntHint ofInt(String id) {
        return newHint(IntHint.class, id);
    }

    static StringHint ofString(String id) {
        return newHint(StringHint.class, id);
    }

    int key();

    private static <T extends WindowHint> T newHint(Class<T> type, String id) {
        return MetaSystem.Window.of(type, MetaSystem.Window.queryInt(id));
    }

    interface BooleanHint extends WindowHint {
        void set(boolean value);

        default void enable() {
            set(true);
        }

        default void disable() {
            set(false);
        }
    }

    interface IntHint extends WindowHint {
        void set(int value);

        void doNotCare();
    }

    interface StringHint extends WindowHint {
        void set(CharSequence value);

        void set(ByteBuffer value);
    }

}
