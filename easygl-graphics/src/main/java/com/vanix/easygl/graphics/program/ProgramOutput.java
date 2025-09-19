package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramResource;

public interface ProgramOutput extends
        ProgramResource.Named<ProgramOutput>,
        ProgramResource.Type<ProgramOutput>,
        ProgramResource.ArraySize<ProgramOutput>,
        ProgramResource.ReferencedByTessEvaluationShader<ProgramOutput>,
        ProgramResource.ReferencedByComputeShader<ProgramOutput>,
        ProgramResource.ReferencedByVertexShader<ProgramOutput>,
        ProgramResource.ReferencedByGeometryShader<ProgramOutput>,
        ProgramResource.ReferencedByFragmentShader<ProgramOutput>,
        ProgramResource.ReferencedByTessControlShader<ProgramOutput>,
        ProgramResource.Location<ProgramOutput>,
        ProgramResource.LocationIndex<ProgramOutput>,
        ProgramResource.IsPerPatch<ProgramOutput>,
        ProgramResource.LocationComponent<ProgramOutput> {

}
