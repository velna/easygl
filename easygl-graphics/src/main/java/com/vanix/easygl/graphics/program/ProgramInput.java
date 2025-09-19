package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramResource;

public interface ProgramInput extends
        ProgramResource.Named<ProgramInput>,
        ProgramResource.Type<ProgramInput>,
        ProgramResource.ArraySize<ProgramInput>,
        ProgramResource.ReferencedByTessEvaluationShader<ProgramInput>,
        ProgramResource.ReferencedByComputeShader<ProgramInput>,
        ProgramResource.ReferencedByVertexShader<ProgramInput>,
        ProgramResource.ReferencedByGeometryShader<ProgramInput>,
        ProgramResource.ReferencedByFragmentShader<ProgramInput>,
        ProgramResource.ReferencedByTessControlShader<ProgramInput>,
        ProgramResource.Location<ProgramInput>,
        ProgramResource.IsPerPatch<ProgramInput>,
        ProgramResource.LocationComponent<ProgramInput> {

}
