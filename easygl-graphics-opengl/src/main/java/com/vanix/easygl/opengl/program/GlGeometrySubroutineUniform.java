package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.GeometrySubroutineUniform;

public class GlGeometrySubroutineUniform extends BaseResource<GeometrySubroutineUniform> implements GeometrySubroutineUniform {
    public GlGeometrySubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.GeometrySubroutineUniform, index);
    }

}
