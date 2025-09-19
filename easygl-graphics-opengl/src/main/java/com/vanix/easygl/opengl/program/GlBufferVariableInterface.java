package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.BufferVariable;
import com.vanix.easygl.graphics.program.BufferVariableInterface;

public class GlBufferVariableInterface extends BaseInterface<BufferVariable> implements BufferVariableInterface {
    public GlBufferVariableInterface(Program program) {
        super(program, GlProgramInterfaceType.BufferVariable);
    }

    @Override
    protected BufferVariable newResource(int index) {
        return new GlBufferVariable(program, index);
    }
}
