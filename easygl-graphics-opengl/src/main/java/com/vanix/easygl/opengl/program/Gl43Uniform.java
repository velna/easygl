package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.Uniform;

public class Gl43Uniform extends BaseResource<Uniform> implements GlUniform {
    public Gl43Uniform(Program program, int index) {
        super(program, GlProgramInterfaceType.Uniform, index);
    }

}
