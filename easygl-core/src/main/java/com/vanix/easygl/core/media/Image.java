package com.vanix.easygl.core.media;

import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.core.meta.Meta;
import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.ByteBuffer;

public interface Image extends Closeable {
    Meta<Image> Meta = MetaSystem.Media.of(Image.class);

    Format format();

    int width();

    int height();

    ByteBuffer data();

    static Image load(String resourceFile) {
        return load(resourceFile, false, false);
    }

    static Image load(String resourceFile, boolean flipVertically) {
        return load(resourceFile, flipVertically, false);
    }

    static Image load(String resourceFile, boolean flipVertically, boolean unPremultiply) {
        return Meta.create(resourceFile, flipVertically, unPremultiply);
    }

    static Image empty(Format format, int width, int height) {
        return new SimpleImage(format, width, height, null);
    }

    enum Format {
        RED,
        RGB,
        RGBA;

        public static Format ofChannels(int n) {
            return switch (n) {
                case 1 -> RED;
                case 3 -> RGB;
                case 4 -> RGBA;
                default -> throw new IllegalArgumentException("Invalid channel number: " + n);
            };
        }

    }
}
