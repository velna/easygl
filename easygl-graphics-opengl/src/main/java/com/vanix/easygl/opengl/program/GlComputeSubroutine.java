package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.ComputeSubroutine;

public class GlComputeSubroutine extends BaseResource<ComputeSubroutine> implements ComputeSubroutine {
    public GlComputeSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.ComputeSubroutine, index);
    }

}
