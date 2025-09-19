package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.AtomicCounterBuffer;

public class GlAtomicCounterBuffer extends BaseResource<AtomicCounterBuffer> implements AtomicCounterBuffer {
    public GlAtomicCounterBuffer(Program program, int index) {
        super(program, GlProgramInterfaceType.AtomicCounterBuffer, index);
    }

}
