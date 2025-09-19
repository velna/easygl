package com.vanix.easygl.commons.bufferio;

public interface FieldBufferIO<T> extends BufferIO<T> {
    String getName();

    int getOffset();
}
