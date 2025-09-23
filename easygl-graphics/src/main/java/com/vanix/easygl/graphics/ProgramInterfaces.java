package com.vanix.easygl.graphics;

import com.vanix.easygl.graphics.program.*;

public interface ProgramInterfaces {

    <B> B bindResources(B bean);

    UniformInterface uniform();

    UniformBlockInterface uniformBlock();

    AtomicCounterBufferInterface atomicCounterBuffer();

    ProgramInputInterface programInput();

    ProgramOutputInterface programOutput();

    default SubroutineInterface subroutine(Shader.Type shaderStage) {
        return switch (shaderStage) {
            case Fragment -> fragmentSubroutine();
            case Vertex -> vertexSubroutine();
            case Geometry -> geometrySubroutine();
            case TessControl -> tessControlSubroutine();
            case TessEvaluation -> tessEvaluationSubroutine();
            case Compute -> computeSubroutine();
        };
    }

    SubroutineInterface vertexSubroutine();

    SubroutineInterface tessControlSubroutine();

    SubroutineInterface tessEvaluationSubroutine();

    SubroutineInterface geometrySubroutine();

    SubroutineInterface fragmentSubroutine();

    SubroutineInterface computeSubroutine();

    SubroutineUniformInterface vertexSubroutineUniform();

    SubroutineUniformInterface tessControlSubroutineUniform();

    SubroutineUniformInterface tessEvaluationSubroutineUniform();

    SubroutineUniformInterface geometrySubroutineUniform();

    SubroutineUniformInterface fragmentSubroutineUniform();

    TransformFeedbackVaryingInterface transformFeedbackVarying();

    BufferVariableInterface bufferVariable();

    ShaderStorageBlockInterface shaderStorageBlock();

    TransformFeedbackBufferInterface transformFeedbackBuffer();
}
