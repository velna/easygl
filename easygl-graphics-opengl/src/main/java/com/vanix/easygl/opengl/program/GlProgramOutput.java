package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.ProgramOutput;

public class GlProgramOutput extends BaseResource<ProgramOutput> implements ProgramOutput {
    public GlProgramOutput(Program program, int index) {
        super(program, GlProgramInterfaceType.ProgramOutput, index);
    }

}
