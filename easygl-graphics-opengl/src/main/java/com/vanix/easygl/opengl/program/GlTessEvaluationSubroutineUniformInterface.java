package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TessEvaluationSubroutineUniform;
import com.vanix.easygl.graphics.program.TessEvaluationSubroutineUniformInterface;

public class GlTessEvaluationSubroutineUniformInterface extends BaseInterface<TessEvaluationSubroutineUniform> implements TessEvaluationSubroutineUniformInterface {
    public GlTessEvaluationSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutineUniform);
    }

    @Override
    protected TessEvaluationSubroutineUniform newResource(int index) {
        return new GlTessEvaluationSubroutineUniform(program, index);
    }
}
