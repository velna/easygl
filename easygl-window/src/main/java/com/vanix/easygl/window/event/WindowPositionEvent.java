package com.vanix.easygl.window.event;

import com.vanix.easygl.window.Window;
import lombok.Getter;

@Getter
public class WindowPositionEvent extends WindowEvent {
    private final int x;
    private final int y;

    public WindowPositionEvent(Window source, int x, int y) {
        super(source);
        this.x = x;
        this.y = y;
    }
}
