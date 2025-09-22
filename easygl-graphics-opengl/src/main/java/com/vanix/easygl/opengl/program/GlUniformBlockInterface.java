package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.UniformBlock;
import com.vanix.easygl.graphics.program.UniformBlockInterface;

public class GlUniformBlockInterface extends BaseInterface<UniformBlock> implements UniformBlockInterface {
    public GlUniformBlockInterface(Program program) {
        super(program, GlProgramInterfaceType.UniformBlock);
    }

    @Override
    protected UniformBlock newResource(GlProgramInterfaceType type, int index) {
        return new GlUniformBlock(program, index);
    }
}
