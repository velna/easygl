package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.FragmentSubroutineUniform;

public class GlFragmentSubroutineUniform extends BaseResource<FragmentSubroutineUniform> implements FragmentSubroutineUniform {
    public GlFragmentSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.FragmentSubroutineUniform, index);
    }

}
