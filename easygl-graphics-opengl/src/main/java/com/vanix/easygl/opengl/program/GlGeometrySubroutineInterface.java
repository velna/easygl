package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.GeometrySubroutine;
import com.vanix.easygl.graphics.program.GeometrySubroutineInterface;

public class GlGeometrySubroutineInterface extends BaseInterface<GeometrySubroutine> implements GeometrySubroutineInterface {
    public GlGeometrySubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.GeometrySubroutine);
    }

    @Override
    protected GeometrySubroutine newResource(int index) {
        return new GlGeometrySubroutine(program, index);
    }
}
