package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.ProgramResource.PropertyKey;
import com.vanix.easygl.graphics.program.Uniform;
import com.vanix.easygl.graphics.program.UniformInterface;

public class GlUniformInterface extends BaseInterface<Uniform> implements UniformInterface {
    private static final PropertyKey[] PRELOAD_KEYS = new PropertyKey[]{PropertyKey.Type, PropertyKey.ArraySize, PropertyKey.Location};

    public GlUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.Uniform);
    }

    @Override
    protected Uniform newResource(GlProgramInterfaceType type, int index) {
        return new Gl43Uniform(program, index).preLoad(PRELOAD_KEYS);
    }
}
