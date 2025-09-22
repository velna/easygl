package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.graphics.program.VertexSubroutineUniform;
import com.vanix.easygl.graphics.program.VertexSubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlVertexSubroutineUniformInterface extends GlSubroutineUniformInterface<VertexSubroutineUniform> implements VertexSubroutineUniformInterface {
    public GlVertexSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected VertexSubroutineUniform newResource(GlProgramInterfaceType type, int index) {
        return new GlVertexSubroutineUniform(program, index);
    }

    @Override
    protected Shader.Type shaderStage() {
        return Shader.Type.Vertex;
    }
}
