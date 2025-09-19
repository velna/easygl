package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.ProgramOutput;
import com.vanix.easygl.graphics.program.ProgramOutputInterface;

public class GlProgramOutputInterface extends BaseInterface<ProgramOutput> implements ProgramOutputInterface {
    public GlProgramOutputInterface(Program program) {
        super(program, GlProgramInterfaceType.ProgramOutput);
    }

    @Override
    protected ProgramOutput newResource(int index) {
        return new GlProgramOutput(program, index);
    }
}
