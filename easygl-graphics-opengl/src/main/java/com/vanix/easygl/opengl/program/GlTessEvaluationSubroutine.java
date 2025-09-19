package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TessEvaluationSubroutine;

public class GlTessEvaluationSubroutine extends BaseResource<TessEvaluationSubroutine> implements TessEvaluationSubroutine {
    public GlTessEvaluationSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutine, index);
    }

}
