package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.graphics.program.TessEvaluationSubroutineUniform;
import com.vanix.easygl.graphics.program.TessEvaluationSubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessEvaluationSubroutineUniformInterface extends GlSubroutineUniformInterface<TessEvaluationSubroutineUniform> implements TessEvaluationSubroutineUniformInterface {
    public GlTessEvaluationSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutineUniform);
    }

    @Override
    protected TessEvaluationSubroutineUniform newResource(GlProgramInterfaceType type, int index) {
        return new GlTessEvaluationSubroutineUniform(program, index);
    }

    @Override
    protected Shader.Type shaderStage() {
        return Shader.Type.TessEvaluationShader;
    }
}
