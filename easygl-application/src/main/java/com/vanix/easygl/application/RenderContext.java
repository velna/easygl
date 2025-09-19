package com.vanix.easygl.application;

import com.vanix.easygl.window.input.InputController;
import com.vanix.easygl.window.Window;
import com.vanix.easygl.graphics.Graphics;

public interface RenderContext {

    Graphics graphics();

    Window window();

    default InputController inputCtlr() {
        return window().inputs();
    }

    double tickDelta();
}
