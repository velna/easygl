package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.UniformBlock;

public class GlUniformBlock extends BaseResource<UniformBlock> implements UniformBlockDefaults {
    public GlUniformBlock(Program program, int index) {
        super(program, GlProgramInterfaceType.UniformBlock, index);
    }

}
