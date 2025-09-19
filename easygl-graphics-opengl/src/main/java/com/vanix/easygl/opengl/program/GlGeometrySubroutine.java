package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.GeometrySubroutine;

public class GlGeometrySubroutine extends BaseResource<GeometrySubroutine> implements GeometrySubroutine {
    public GlGeometrySubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.GeometrySubroutine, index);
    }

}
