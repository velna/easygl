package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.ProgramInput;
import com.vanix.easygl.graphics.program.ProgramInputInterface;

public class GlProgramInputInterface extends BaseInterface<ProgramInput> implements ProgramInputInterface {
    public GlProgramInputInterface(Program program) {
        super(program, GlProgramInterfaceType.ProgramInput);
    }

    @Override
    protected ProgramInput newResource(GlProgramInterfaceType type, int index) {
        return new GlProgramInput(program, index);
    }
}
