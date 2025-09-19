package com.vanix.easygl.window.event;

import com.vanix.easygl.commons.event.EventListener;

public interface WindowMaximizeListener extends EventListener {
    void windowOnMaximize(WindowMaximizeEvent event);
}