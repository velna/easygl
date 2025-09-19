package com.vanix.easygl.core.media;

import java.nio.ByteBuffer;

public class SimpleImage implements Image {
    private final Format format;
    private final int width;
    private final int height;
    private ByteBuffer data;

    public SimpleImage(int channels, int width, int height, ByteBuffer data) {
        this(Format.ofChannels(channels), width, height, data);
    }

    public SimpleImage(Format format, int width, int height, ByteBuffer data) {
        this.format = format;
        this.width = width;
        this.height = height;
        this.data = data;
    }

    @Override
    public Format format() {
        return format;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public ByteBuffer data() {
        return data;
    }

    @Override
    public void close() {
        data = null;
    }
}
