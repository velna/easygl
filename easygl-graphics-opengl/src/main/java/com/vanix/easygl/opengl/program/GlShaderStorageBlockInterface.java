package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.ShaderStorageBlock;
import com.vanix.easygl.graphics.program.ShaderStorageBlockInterface;

public class GlShaderStorageBlockInterface extends BaseInterface<ShaderStorageBlock> implements ShaderStorageBlockInterface {
    public GlShaderStorageBlockInterface(Program program) {
        super(program, GlProgramInterfaceType.ShaderStorageBlock);
    }

    @Override
    protected ShaderStorageBlock newResource(GlProgramInterfaceType type, int index) {
        return new GlShaderStorageBlock(program, index);
    }
}
