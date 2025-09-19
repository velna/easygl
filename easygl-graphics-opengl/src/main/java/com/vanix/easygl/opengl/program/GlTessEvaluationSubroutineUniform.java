package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TessEvaluationSubroutineUniform;

public class GlTessEvaluationSubroutineUniform extends BaseResource<TessEvaluationSubroutineUniform> implements TessEvaluationSubroutineUniform {
    public GlTessEvaluationSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutineUniform, index);
    }

}
