package com.vanix.easygl.window.input.event;

import com.vanix.easygl.window.input.InputDevice;
import com.vanix.easygl.window.input.Mouse;

public class MouseEvent extends InputEvent<Mouse, Mouse.Button> {
    public MouseEvent(Mouse device) {
        super(device, null);
    }

    public MouseEvent(Mouse device, Mouse.Button button) {
        super(device, button);
    }

    public MouseEvent(Mouse device, Mouse.Button button, InputDevice.Action action) {
        super(device, button, action);
    }
}
