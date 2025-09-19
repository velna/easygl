package com.vanix.easygl.window.input;

public interface InputController extends Iterable<InputDevice<?>> {
    Mouse mouse();

    Keyboard keyboard();
}
