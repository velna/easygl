package com.vanix.easygl.media;

import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.meta.AbstractMetaService;
import com.vanix.easygl.core.meta.DefaultMeta;
import com.vanix.easygl.core.meta.ErrorCode;
import com.vanix.easygl.core.meta.SystemName;
import com.vanix.easygl.media.stb.StbImage;
import org.lwjgl.assimp.Assimp;
import org.lwjgl.stb.STBImage;
import org.lwjgl.stb.STBImageResize;

@SystemName("Media")
public class MediaMetaService extends AbstractMetaService {
    public MediaMetaService() {
        register(Image.class, new DefaultMeta<>(args -> StbImage.load((String) args[0], (boolean) args[1], (boolean) args[2])));
    }

    @Override
    public int queryInt(String id) {
        return queryStaticIntField(STBImage.class, "STBI_", id)
                .or(() -> queryStaticIntField(STBImageResize.class, "STBIR_", id))
                .or(() -> queryStaticIntField(Assimp.class, "ai", id))
                .orElseThrow();
    }

    @Override
    public ErrorCode getError() {
        return null;
    }

    @Override
    public void close() {

    }
}
