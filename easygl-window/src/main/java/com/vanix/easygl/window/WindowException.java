package com.vanix.easygl.window;

public class WindowException extends RuntimeException {
    public WindowException(String message) {
        super(message);
    }

    public WindowException(String message, Throwable cause) {
        super(message, cause);
    }
}
