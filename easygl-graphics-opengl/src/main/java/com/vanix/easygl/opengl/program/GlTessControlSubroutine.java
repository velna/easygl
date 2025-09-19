package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TessControlSubroutine;

public class GlTessControlSubroutine extends BaseResource<TessControlSubroutine> implements TessControlSubroutine {
    public GlTessControlSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.TessControlSubroutine, index);
    }

}
