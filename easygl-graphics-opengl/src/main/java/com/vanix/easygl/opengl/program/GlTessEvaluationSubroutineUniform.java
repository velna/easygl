package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.TessEvaluationSubroutineUniform;

public class GlTessEvaluationSubroutineUniform extends GlSubroutineUniform<TessEvaluationSubroutineUniform> implements TessEvaluationSubroutineUniform {
    public GlTessEvaluationSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutineUniform, index);
    }

    @Override
    public Shader.Type shaderStage() {
        return Shader.Type.TessEvaluationShader;
    }
}
