package com.vanix.easygl.window.input.event;

import com.vanix.easygl.commons.event.Event;
import com.vanix.easygl.window.input.InputDevice;

public class InputEvent<T extends InputDevice<I>, I extends InputDevice.Input> extends Event<T> {
    protected final I input;
    protected final InputDevice.Action action;

    public InputEvent(T device) {
        this(device, null, null);
    }

    public InputEvent(T device, I input) {
        this(device, input, null);
    }

    public InputEvent(T device, I input, InputDevice.Action action) {
        super(device);
        this.input = input;
        this.action = action;
    }

}
