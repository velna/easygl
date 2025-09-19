package com.vanix.easygl.core;

public interface Handle extends Closeable {
    long handle();

    default int intHandle() {
        return (int) handle();
    }

}
