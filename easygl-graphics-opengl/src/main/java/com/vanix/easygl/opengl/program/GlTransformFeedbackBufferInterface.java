package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TransformFeedbackBuffer;
import com.vanix.easygl.graphics.program.TransformFeedbackBufferInterface;

public class GlTransformFeedbackBufferInterface extends BaseInterface<TransformFeedbackBuffer> implements TransformFeedbackBufferInterface {
    public GlTransformFeedbackBufferInterface(Program program) {
        super(program, GlProgramInterfaceType.TransformFeedbackBuffer);
    }

    @Override
    protected TransformFeedbackBuffer newResource(int index) {
        return new GlTransformFeedbackBuffer(program, index);
    }
}
