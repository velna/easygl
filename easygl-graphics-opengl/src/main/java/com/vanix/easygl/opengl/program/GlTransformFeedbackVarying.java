package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TransformFeedbackVarying;

public class GlTransformFeedbackVarying extends BaseResource<TransformFeedbackVarying> implements TransformFeedbackVarying {
    public GlTransformFeedbackVarying(Program program, int index) {
        super(program, GlProgramInterfaceType.TransformFeedbackVarying, index);
    }

}
