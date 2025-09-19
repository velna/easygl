package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.FragmentSubroutineUniform;
import com.vanix.easygl.graphics.program.FragmentSubroutineUniformInterface;

public class GlFragmentSubroutineUniformInterface extends BaseInterface<FragmentSubroutineUniform> implements FragmentSubroutineUniformInterface {
    public GlFragmentSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.FragmentSubroutineUniform);
    }

    @Override
    protected FragmentSubroutineUniform newResource(int index) {
        return new GlFragmentSubroutineUniform(program, index);
    }
}
