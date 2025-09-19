package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.VertexSubroutine;
import com.vanix.easygl.graphics.program.VertexSubroutineInterface;

public class GlVertexSubroutineInterface extends BaseInterface<VertexSubroutine> implements VertexSubroutineInterface {
    public GlVertexSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutine);
    }

    @Override
    protected VertexSubroutine newResource(int index) {
        return new GlVertexSubroutine(program, index);
    }
}
