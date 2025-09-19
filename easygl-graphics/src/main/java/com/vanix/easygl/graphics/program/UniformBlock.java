package com.vanix.easygl.graphics.program;

import com.vanix.easygl.commons.bufferio.StructBufferIO;
import com.vanix.easygl.graphics.Buffer;
import com.vanix.easygl.graphics.ProgramResource;

import java.nio.ByteBuffer;

public interface UniformBlock extends
        ProgramResource.Named<UniformBlock>,
        ProgramResource.BufferBinding<UniformBlock>,
        ProgramResource.BufferDataSize<UniformBlock>,
        ProgramResource.NumActiveVariables<UniformBlock>,
        ProgramResource.ActiveVariables<UniformBlock>,
        ProgramResource.ReferencedByTessEvaluationShader<UniformBlock>,
        ProgramResource.ReferencedByComputeShader<UniformBlock>,
        ProgramResource.ReferencedByVertexShader<UniformBlock>,
        ProgramResource.ReferencedByGeometryShader<UniformBlock>,
        ProgramResource.ReferencedByFragmentShader<UniformBlock>,
        ProgramResource.ReferencedByTessControlShader<UniformBlock> {
    UniformBlock bind(Buffer.BindingPoint bindingPoint);

    <T> StructBufferIO<T> createBufferIO(T bean, ByteBuffer storage);
}
