package com.vanix.easygl.window.event;

import com.vanix.easygl.commons.event.EventListener;

public interface WindowFocusListener extends EventListener {
    void windowOnFocus(WindowFocusEvent event);
}
