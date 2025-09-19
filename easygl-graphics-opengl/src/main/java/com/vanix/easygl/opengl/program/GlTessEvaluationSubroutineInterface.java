package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TessEvaluationSubroutine;
import com.vanix.easygl.graphics.program.TessEvaluationSubroutineInterface;

public class GlTessEvaluationSubroutineInterface extends BaseInterface<TessEvaluationSubroutine> implements TessEvaluationSubroutineInterface {
    public GlTessEvaluationSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutine);
    }

    @Override
    protected TessEvaluationSubroutine newResource(int index) {
        return new GlTessEvaluationSubroutine(program, index);
    }
}
