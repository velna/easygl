package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.Subroutine;
import com.vanix.easygl.graphics.program.SubroutineInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlSubroutineInterface extends BaseInterface<Subroutine> implements SubroutineInterface {
    public GlSubroutineInterface(Program program, GlProgramInterfaceType type) {
        super(program, type);
    }

    @Override
    protected Subroutine newResource(GlProgramInterfaceType type, int index) {
        return new GlSubroutine(program, type, index);
    }
}
