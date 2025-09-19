package com.vanix.easygl.commons.bufferio;

import java.nio.ByteBuffer;

public interface BufferList {
    int size();

    int saveInto(ByteBuffer buffer);

    void loadFrom(ByteBuffer buffer, int count);
}
