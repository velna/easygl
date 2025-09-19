package com.vanix.easygl.window.input.event;

import com.vanix.easygl.window.input.Mouse;
import lombok.Getter;

@Getter
public class MouseEnterEvent extends MouseEvent {
    private final boolean enter;

    public MouseEnterEvent(Mouse device, boolean enter) {
        super(device);
        this.enter = enter;
    }

    public boolean isLeave() {
        return !enter;
    }
}
