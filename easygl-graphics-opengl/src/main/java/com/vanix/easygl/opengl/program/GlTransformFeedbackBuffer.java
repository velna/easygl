package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TransformFeedbackBuffer;

public class GlTransformFeedbackBuffer extends BaseResource<TransformFeedbackBuffer> implements TransformFeedbackBuffer {
    public GlTransformFeedbackBuffer(Program program, int index) {
        super(program, GlProgramInterfaceType.TransformFeedbackBuffer, index);
    }

}
