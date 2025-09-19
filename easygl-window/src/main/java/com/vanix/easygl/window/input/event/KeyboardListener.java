package com.vanix.easygl.window.input.event;

import com.vanix.easygl.commons.event.EventListener;

public interface KeyboardListener extends EventListener {
    void keyboardOnKey(KeyboardEvent event);
}
