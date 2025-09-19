package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.BufferVariable;

public class GlBufferVariable extends BaseResource<BufferVariable> implements BufferVariable {
    public GlBufferVariable(Program program, int index) {
        super(program, GlProgramInterfaceType.BufferVariable, index);
    }

}
