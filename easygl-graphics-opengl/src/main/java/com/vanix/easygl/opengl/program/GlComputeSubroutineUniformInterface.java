package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.graphics.program.ComputeSubroutineUniform;
import com.vanix.easygl.graphics.program.ComputeSubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlComputeSubroutineUniformInterface extends GlSubroutineUniformInterface<ComputeSubroutineUniform> implements ComputeSubroutineUniformInterface {
    public GlComputeSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.ComputeSubroutineUniform);
    }

    @Override
    protected ComputeSubroutineUniform newResource(int index) {
        return new GlComputeSubroutineUniform(program, index);
    }

    @Override
    public Shader.Type shaderStage() {
        return Shader.Type.ComputeShader;
    }
}
