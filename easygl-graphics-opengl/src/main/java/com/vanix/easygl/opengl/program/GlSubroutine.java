package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.Subroutine;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import org.lwjgl.system.MemoryStack;

import java.util.ArrayList;
import java.util.List;

public class GlSubroutine extends BaseResource<Subroutine> implements Subroutine {
    public GlSubroutine(Program program, GlProgramInterfaceType interfaceType, int index) {
        super(program, interfaceType, index);
    }

}
