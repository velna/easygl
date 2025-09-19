package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.GeometrySubroutineUniform;
import com.vanix.easygl.graphics.program.GeometrySubroutineUniformInterface;

public class GlGeometrySubroutineUniformInterface extends BaseInterface<GeometrySubroutineUniform> implements GeometrySubroutineUniformInterface {
    public GlGeometrySubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected GeometrySubroutineUniform newResource(int index) {
        return new GlGeometrySubroutineUniform(program, index);
    }
}
