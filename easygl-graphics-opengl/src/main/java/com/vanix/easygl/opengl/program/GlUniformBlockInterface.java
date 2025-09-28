package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.ProgramAttribute;
import com.vanix.easygl.graphics.program.UniformBlock;
import com.vanix.easygl.graphics.program.UniformBlockInterface;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlGraphics;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlUniformBlockInterface extends BaseInterface<UniformBlock> implements UniformBlockInterface {
    private static final InterfaceCore<GlUniformBlockInterface> GL31 = new InterfaceCore<>() {
        @Override
        public int getIndexByName(GlUniformBlockInterface baseInterface, String name) {
            return GLX.glGetUniformBlockIndex(baseInterface.program.intHandle(), name);
        }

        @Override
        public int getActiveResources(GlUniformBlockInterface baseInterface) {
            return baseInterface.program.getAttribute(ProgramAttribute.ActiveUniformBlocks);
        }
    };

    public GlUniformBlockInterface(Program program) {
        super(program, GlProgramInterfaceType.UniformBlock, GlGraphics.CAPABILITIES.OpenGL43 ? GL43 : GL31);
    }

    @Override
    protected UniformBlock newResource(GlProgramInterfaceType type, int index) {
        if (GlGraphics.CAPABILITIES.OpenGL43) {
            return new GlUniformBlock(program, index);
        } else {
            return new Gl31UniformBlock(program, index, GLX.glGetActiveUniformBlockName(program.intHandle(), index));
        }
    }
}
