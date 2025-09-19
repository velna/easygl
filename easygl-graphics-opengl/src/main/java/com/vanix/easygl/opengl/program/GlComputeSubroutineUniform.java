package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.ComputeSubroutineUniform;

public class GlComputeSubroutineUniform extends BaseResource<ComputeSubroutineUniform> implements ComputeSubroutineUniform {
    public GlComputeSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.ComputeSubroutineUniform, index);
    }

}
