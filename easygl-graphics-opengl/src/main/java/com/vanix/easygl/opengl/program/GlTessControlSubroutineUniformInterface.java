package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.graphics.program.TessControlSubroutineUniform;
import com.vanix.easygl.graphics.program.TessControlSubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessControlSubroutineUniformInterface extends GlSubroutineUniformInterface<TessControlSubroutineUniform> implements TessControlSubroutineUniformInterface {
    public GlTessControlSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected TessControlSubroutineUniform newResource(int index) {
        return new GlTessControlSubroutineUniform(program, index);
    }

    @Override
    protected Shader.Type shaderStage() {
        return Shader.Type.TessControlShader;
    }
}
