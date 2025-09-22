package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.ComputeSubroutineUniform;

public class GlComputeSubroutineUniform extends GlSubroutineUniform<ComputeSubroutineUniform> implements ComputeSubroutineUniform {
    public GlComputeSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.ComputeSubroutineUniform, index);
    }

    @Override
    public Shader.Type shaderStage() {
        return Shader.Type.ComputeShader;
    }
}
