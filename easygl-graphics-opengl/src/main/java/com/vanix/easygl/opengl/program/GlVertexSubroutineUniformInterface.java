package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.VertexSubroutineUniform;
import com.vanix.easygl.graphics.program.VertexSubroutineUniformInterface;

public class GlVertexSubroutineUniformInterface extends BaseInterface<VertexSubroutineUniform> implements VertexSubroutineUniformInterface {
    public GlVertexSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected VertexSubroutineUniform newResource(int index) {
        return new GlVertexSubroutineUniform(program, index);
    }
}
