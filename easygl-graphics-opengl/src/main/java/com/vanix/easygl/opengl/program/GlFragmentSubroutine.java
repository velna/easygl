package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.FragmentSubroutine;

public class GlFragmentSubroutine extends BaseResource<FragmentSubroutine> implements FragmentSubroutine {
    public GlFragmentSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.FragmentSubroutine, index);
    }

}
