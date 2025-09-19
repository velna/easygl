package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramResource;

public interface VertexSubroutineUniform extends
        ProgramResource.Named<VertexSubroutineUniform>,
        ProgramResource.ArraySize<VertexSubroutineUniform>,
        ProgramResource.NumCompatibleSubroutines<VertexSubroutineUniform>,
        ProgramResource.CompatibleSubroutines<VertexSubroutineUniform>,
        ProgramResource.Location<VertexSubroutineUniform> {
}
