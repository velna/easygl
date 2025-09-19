package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TessControlSubroutine;
import com.vanix.easygl.graphics.program.TessControlSubroutineInterface;

public class GlTessControlSubroutineInterface extends BaseInterface<TessControlSubroutine> implements TessControlSubroutineInterface {
    public GlTessControlSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.TessControlSubroutine);
    }

    @Override
    protected TessControlSubroutine newResource(int index) {
        return new GlTessControlSubroutine(program, index);
    }
}
