package com.vanix.easygl.media.stb;

import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.media.MediaException;
import com.vanix.easygl.core.media.SimpleImage;
import com.vanix.easygl.core.util.ResourceUtils;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;

public class StbImage {
    public static Image load(String resourceFile, boolean flipVertically, boolean unPremultiply) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer tWidth = stack.mallocInt(1);
            IntBuffer tHeight = stack.mallocInt(1);
            IntBuffer tChannels = stack.mallocInt(1);
            String textureFile = ResourceUtils.resolve(resourceFile);
            stbi_set_flip_vertically_on_load(flipVertically);
            stbi_set_unpremultiply_on_load(unPremultiply);
            ByteBuffer data = stbi_load(stack.UTF8(textureFile), tWidth, tHeight, tChannels, 0);
            if (null != data) {
                return new SimpleImage(tChannels.get(), tWidth.get(), tHeight.get(), data);
            } else {
                throw new MediaException("Image load failed: " + resourceFile);
            }
        }
    }
}
