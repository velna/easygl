package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.FragmentSubroutine;
import com.vanix.easygl.graphics.program.FragmentSubroutineInterface;

public class GlFragmentSubroutineInterface extends BaseInterface<FragmentSubroutine> implements FragmentSubroutineInterface {
    public GlFragmentSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.FragmentSubroutine);
    }

    @Override
    protected FragmentSubroutine newResource(int index) {
        return new GlFragmentSubroutine(program, index);
    }
}
