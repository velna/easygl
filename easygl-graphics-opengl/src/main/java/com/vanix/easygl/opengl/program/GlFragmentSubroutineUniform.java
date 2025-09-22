package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.graphics.program.FragmentSubroutineUniform;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlFragmentSubroutineUniform extends GlSubroutineUniform<FragmentSubroutineUniform> implements FragmentSubroutineUniform {
    public GlFragmentSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.FragmentSubroutineUniform, index);
    }

    @Override
    public Shader.Type shaderStage() {
        return Shader.Type.Fragment;
    }
}
