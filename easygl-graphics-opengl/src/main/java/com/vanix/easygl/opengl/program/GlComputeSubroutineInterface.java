package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.ComputeSubroutine;
import com.vanix.easygl.graphics.program.ComputeSubroutineInterface;

public class GlComputeSubroutineInterface extends BaseInterface<ComputeSubroutine> implements ComputeSubroutineInterface {
    public GlComputeSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.ComputeSubroutine);
    }

    @Override
    protected ComputeSubroutine newResource(int index) {
        return new GlComputeSubroutine(program, index);
    }
}
