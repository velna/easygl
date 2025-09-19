package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.ComputeSubroutineUniform;
import com.vanix.easygl.graphics.program.ComputeSubroutineUniformInterface;

public class GlComputeSubroutineUniformInterface extends BaseInterface<ComputeSubroutineUniform> implements ComputeSubroutineUniformInterface {
    public GlComputeSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.ComputeSubroutineUniform);
    }

    @Override
    protected ComputeSubroutineUniform newResource(int index) {
        return new GlComputeSubroutineUniform(program, index);
    }
}
