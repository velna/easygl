package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.ProgramInput;

public class GlProgramInput extends BaseResource<ProgramInput> implements ProgramInput {
    public GlProgramInput(Program program, int index) {
        super(program, GlProgramInterfaceType.ProgramInput, index);
    }

}
