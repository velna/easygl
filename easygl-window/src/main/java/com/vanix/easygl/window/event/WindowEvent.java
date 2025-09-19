package com.vanix.easygl.window.event;

import com.vanix.easygl.commons.event.Event;
import com.vanix.easygl.window.Window;

public class WindowEvent extends Event<Window> {
    public WindowEvent(Window source) {
        super(source);
    }
}
