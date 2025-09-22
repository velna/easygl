package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.graphics.program.GeometrySubroutineUniform;
import com.vanix.easygl.graphics.program.GeometrySubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlGeometrySubroutineUniformInterface extends GlSubroutineUniformInterface<GeometrySubroutineUniform> implements GeometrySubroutineUniformInterface {
    public GlGeometrySubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected GeometrySubroutineUniform newResource(int index) {
        return new GlGeometrySubroutineUniform(program, index);
    }

    @Override
    protected Shader.Type shaderStage() {
        return Shader.Type.Geometry;
    }
}
