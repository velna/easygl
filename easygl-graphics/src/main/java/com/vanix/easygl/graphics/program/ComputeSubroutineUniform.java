package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramResource;

public interface ComputeSubroutineUniform extends
        ProgramResource.Named<ComputeSubroutineUniform>,
        ProgramResource.ArraySize<ComputeSubroutineUniform>,
        ProgramResource.NumCompatibleSubroutines<ComputeSubroutineUniform>,
        ProgramResource.CompatibleSubroutines<ComputeSubroutineUniform>,
        ProgramResource.Location<ComputeSubroutineUniform> {
}
