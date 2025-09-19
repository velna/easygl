package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramResource;

public interface Uniform extends
        UniformOps<Uniform>,
        ProgramResource.Named<Uniform>,
        ProgramResource.Type<Uniform>,
        ProgramResource.ArraySize<Uniform>,
        ProgramResource.Offset<Uniform>,
        ProgramResource.BlockIndex<Uniform>,
        ProgramResource.ArrayStride<Uniform>,
        ProgramResource.MatrixStride<Uniform>,
        ProgramResource.IsRowMajor<Uniform>,
        ProgramResource.AtomicCounterBufferIndex<Uniform>,
        ProgramResource.ReferencedByTessEvaluationShader<Uniform>,
        ProgramResource.ReferencedByComputeShader<Uniform>,
        ProgramResource.ReferencedByVertexShader<Uniform>,
        ProgramResource.ReferencedByGeometryShader<Uniform>,
        ProgramResource.ReferencedByFragmentShader<Uniform>,
        ProgramResource.ReferencedByTessControlShader<Uniform>,
        ProgramResource.Location<Uniform> {

}
