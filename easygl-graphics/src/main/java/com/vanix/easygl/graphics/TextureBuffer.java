package com.vanix.easygl.graphics;

import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

public interface TextureBuffer extends Texture<TextureBuffer> {
    int MAX_SIZE = MetaSystem.Graphics.queryInt("GET.MAX_TEXTURE_BUFFER_SIZE");
    int OffsetAlignment = MetaSystem.Graphics.queryInt("GET.TEXTURE_BUFFER_OFFSET_ALIGNMENT");
    Texture.TexTarget<TextureBuffer> Target = new Texture.TexTarget<>(MetaSystem.Graphics.queryInt("TEXTURE_3D"), "Texture3D", MetaHolder.TextureBuffer);

    @Support(since = Version.GL31)
    TextureBuffer attach(InternalPixelFormat.Sized format, Buffer buffer);

    @Support(since = Version.GL43)
    TextureBuffer attach(InternalPixelFormat.Sized format, Buffer buffer, long offset, long size);

    static TextureBuffer of() {
        return MetaHolder.TextureBuffer.create();
    }
}
