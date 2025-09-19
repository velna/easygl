package com.vanix.easygl.application;

import com.vanix.easygl.application.util.Counters;
import com.vanix.easygl.window.Window;
import com.vanix.easygl.graphics.Graphics;

public interface ClientApp {

    Graphics graphics();

    Window window();

    Ticket ticket();

    Counters counters();

    static ClientApp of(String id, Window window) {
        return new DefaultClientApp(id, window);
    }
}
