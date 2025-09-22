package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.VertexSubroutineUniform;

public class GlVertexSubroutineUniform extends GlSubroutineUniform<VertexSubroutineUniform> implements VertexSubroutineUniform {
    public GlVertexSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform, index);
    }

    @Override
    public Shader.Type shaderStage() {
        return Shader.Type.Vertex;
    }
}
