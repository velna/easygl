package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.graphics.program.FragmentSubroutineUniform;
import com.vanix.easygl.graphics.program.FragmentSubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlFragmentSubroutineUniformInterface extends GlSubroutineUniformInterface<FragmentSubroutineUniform> implements FragmentSubroutineUniformInterface {
    public GlFragmentSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.FragmentSubroutineUniform);
    }

    @Override
    protected FragmentSubroutineUniform newResource(int index) {
        return new GlFragmentSubroutineUniform(program, index);
    }

    @Override
    protected Shader.Type shaderStage() {
        return Shader.Type.Fragment;
    }
}
