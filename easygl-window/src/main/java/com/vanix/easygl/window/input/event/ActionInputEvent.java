package com.vanix.easygl.window.input.event;

import com.vanix.easygl.window.input.InputDevice;

public interface ActionInputEvent {

    InputDevice.Action action();

    default boolean isActionPress() {
        return action() == InputDevice.Action.Press;
    }

    default boolean isActionRelease() {
        return action() == InputDevice.Action.Release;
    }

    default boolean isActionRepeat() {
        return action() == InputDevice.Action.Repeat;
    }
}
