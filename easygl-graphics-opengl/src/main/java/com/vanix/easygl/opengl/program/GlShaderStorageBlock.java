package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Buffer;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.ShaderStorageBlock;

public class GlShaderStorageBlock extends BaseResource<ShaderStorageBlock> implements ShaderStorageBlock {
    public GlShaderStorageBlock(Program program, int index) {
        super(program, GlProgramInterfaceType.ShaderStorageBlock, index);
    }

    @Override
    public ShaderStorageBlock bind(Buffer.BindingPoint bindingPoint) {
        GLX.glShaderStorageBlockBinding(program.intHandle(), index, bindingPoint.value());
        return this;
    }
}
