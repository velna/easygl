package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.GeometrySubroutineUniform;

public class GlGeometrySubroutineUniform extends GlSubroutineUniform<GeometrySubroutineUniform> implements GeometrySubroutineUniform {
    public GlGeometrySubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.GeometrySubroutineUniform, index);
    }

    @Override
    public Shader.Type shaderStage() {
        return Shader.Type.Geometry;
    }
}
