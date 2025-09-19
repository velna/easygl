package com.vanix.easygl.window.input.event;

import com.vanix.easygl.window.input.InputDevice;

public interface ModifierInputEvent {
    int modifiers();

    default boolean hasModifier(InputDevice.Modifier modifier) {
        return (modifiers() & modifier.value()) != 0;
    }
}
