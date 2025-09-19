package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramResource;

public interface TransformFeedbackBuffer extends
        ProgramResource.BufferBinding<TransformFeedbackBuffer>,
        ProgramResource.NumActiveVariables<TransformFeedbackBuffer>,
        ProgramResource.ActiveVariables<TransformFeedbackBuffer>,
        ProgramResource.TransformFeedbackBufferIndex<TransformFeedbackBuffer>,
        ProgramResource.TransformFeedbackBufferStride<TransformFeedbackBuffer> {

}
