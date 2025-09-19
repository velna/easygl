package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TransformFeedbackVarying;
import com.vanix.easygl.graphics.program.TransformFeedbackVaryingInterface;

public class GlTransformFeedbackVaryingInterface extends BaseInterface<TransformFeedbackVarying> implements TransformFeedbackVaryingInterface {
    public GlTransformFeedbackVaryingInterface(Program program) {
        super(program, GlProgramInterfaceType.TransformFeedbackVarying);
    }

    @Override
    protected TransformFeedbackVarying newResource(int index) {
        return new GlTransformFeedbackVarying(program, index);
    }
}
