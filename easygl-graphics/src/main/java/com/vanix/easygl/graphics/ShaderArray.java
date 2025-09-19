package com.vanix.easygl.graphics;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.Support;

import java.nio.ByteBuffer;
import java.util.List;

public abstract class ShaderArray extends HandleArray<Shader> {
    public ShaderArray(List<Shader> list) {
        super(list);
    }

    public ShaderArray(Shader... shaders) {
        super(List.of(shaders));
    }

    @Support(since = Version.GL41)
    public abstract ShaderArray binary(int format, ByteBuffer data);
}
