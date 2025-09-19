package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramResource;

public interface AtomicCounterBuffer extends
        ProgramResource.BufferBinding<AtomicCounterBuffer>,
        ProgramResource.BufferDataSize<AtomicCounterBuffer>,
        ProgramResource.NumActiveVariables<AtomicCounterBuffer>,
        ProgramResource.ActiveVariables<AtomicCounterBuffer> {

}
