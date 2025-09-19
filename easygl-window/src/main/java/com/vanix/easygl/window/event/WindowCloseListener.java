package com.vanix.easygl.window.event;

import com.vanix.easygl.commons.event.EventListener;

public interface WindowCloseListener extends EventListener {
    void windowOnClose(WindowEvent event);
}
