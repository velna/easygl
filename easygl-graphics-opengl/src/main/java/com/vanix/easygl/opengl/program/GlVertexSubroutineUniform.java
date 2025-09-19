package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.VertexSubroutineUniform;

public class GlVertexSubroutineUniform extends BaseResource<VertexSubroutineUniform> implements VertexSubroutineUniform {
    public GlVertexSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform, index);
    }

}
