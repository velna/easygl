package com.vanix.easygl.window.event;

import com.vanix.easygl.window.Window;
import lombok.Getter;

@Getter
public class WindowMaximizeEvent extends WindowEvent {
    private final boolean maximize;

    public WindowMaximizeEvent(Window source, boolean maximize) {
        super(source);
        this.maximize = maximize;
    }
}
