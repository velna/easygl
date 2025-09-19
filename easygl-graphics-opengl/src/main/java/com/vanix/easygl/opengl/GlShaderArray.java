package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.graphics.ShaderArray;

import java.nio.ByteBuffer;
import java.util.List;

public class GlShaderArray extends ShaderArray {
    public GlShaderArray(List<Shader> list) {
        super(list);
    }

    public GlShaderArray(Shader... shaders) {
        super(shaders);
    }

    @Override
    public ShaderArray binary(int format, ByteBuffer data) {
        GLX.glShaderBinary(handles, format, data);
        return this;
    }
}
