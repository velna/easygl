package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TessControlSubroutineUniform;
import com.vanix.easygl.graphics.program.TessControlSubroutineUniformInterface;

public class GlTessControlSubroutineUniformInterface extends BaseInterface<TessControlSubroutineUniform> implements TessControlSubroutineUniformInterface {
    public GlTessControlSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected TessControlSubroutineUniform newResource(int index) {
        return new GlTessControlSubroutineUniform(program, index);
    }
}
