package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.VertexSubroutine;

public class GlVertexSubroutine extends BaseResource<VertexSubroutine> implements VertexSubroutine {
    public GlVertexSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.VertexSubroutine, index);
    }

}
