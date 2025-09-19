package com.vanix.easygl.window.event;

import com.vanix.easygl.commons.event.EventListener;

public interface WindowFpsListener extends EventListener {
    void windowOnFpsUpdate(WindowFpsEvent event);
}
