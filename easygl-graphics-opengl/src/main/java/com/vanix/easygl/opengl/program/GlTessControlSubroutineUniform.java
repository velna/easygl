package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TessControlSubroutineUniform;

public class GlTessControlSubroutineUniform extends GlSubroutineUniform<TessControlSubroutineUniform> implements TessControlSubroutineUniform {
    public GlTessControlSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.TessControlSubroutineUniform, index);
    }

    @Override
    public Shader.Type shaderStage() {
        return Shader.Type.TessControlShader;
    }
}
