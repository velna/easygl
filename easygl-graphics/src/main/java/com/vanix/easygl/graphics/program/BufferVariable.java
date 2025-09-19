package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramResource;

public interface BufferVariable extends
        ProgramResource.Named<BufferVariable>,
        ProgramResource.Type<BufferVariable>,
        ProgramResource.ArraySize<BufferVariable>,
        ProgramResource.Offset<BufferVariable>,
        ProgramResource.BlockIndex<BufferVariable>,
        ProgramResource.ArrayStride<BufferVariable>,
        ProgramResource.MatrixStride<BufferVariable>,
        ProgramResource.IsRowMajor<BufferVariable>,
        ProgramResource.ReferencedByTessEvaluationShader<BufferVariable>,
        ProgramResource.ReferencedByComputeShader<BufferVariable>,
        ProgramResource.ReferencedByVertexShader<BufferVariable>,
        ProgramResource.ReferencedByGeometryShader<BufferVariable>,
        ProgramResource.ReferencedByFragmentShader<BufferVariable>,
        ProgramResource.ReferencedByTessControlShader<BufferVariable>,
        ProgramResource.TopLevelArraySize<BufferVariable>,
        ProgramResource.TopLevelArrayStride<BufferVariable> {

}
