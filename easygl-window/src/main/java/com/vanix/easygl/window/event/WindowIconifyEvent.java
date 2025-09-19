package com.vanix.easygl.window.event;

import com.vanix.easygl.window.Window;
import lombok.Getter;

@Getter
public class WindowIconifyEvent extends WindowEvent {
    private final boolean iconify;

    public WindowIconifyEvent(Window source, boolean iconify) {
        super(source);
        this.iconify = iconify;
    }
}
