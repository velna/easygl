package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.AtomicCounterBuffer;
import com.vanix.easygl.graphics.program.AtomicCounterBufferInterface;

public class GlAtomicCounterBufferInterface extends BaseInterface<AtomicCounterBuffer> implements AtomicCounterBufferInterface {
    public GlAtomicCounterBufferInterface(Program program) {
        super(program, GlProgramInterfaceType.AtomicCounterBuffer);
    }

    @Override
    protected AtomicCounterBuffer newResource(int index) {
        return new GlAtomicCounterBuffer(program, index);
    }
}
