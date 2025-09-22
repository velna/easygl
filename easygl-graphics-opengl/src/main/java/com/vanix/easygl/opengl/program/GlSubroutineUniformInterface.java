package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.ProgramInterface;
import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.graphics.program.SubroutineUniform;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlGraphics;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public abstract class GlSubroutineUniformInterface<T extends SubroutineUniform<T>> extends BaseInterface<T> implements ProgramInterface.SubroutineUniform<T> {
    private static final InterfaceCore<GlSubroutineUniformInterface<?>> GL40 = new InterfaceCore<>() {
        @Override
        public int getIndexByName(GlSubroutineUniformInterface<?> baseInterface, String name) {
            return GLX.glGetSubroutineIndex(baseInterface.program.intHandle(), baseInterface.shaderStage().value(), name);
        }

        @Override
        public int getActiveResources(GlSubroutineUniformInterface<?> baseInterface) {
            return GLX.glGetProgramStagei(baseInterface.program.intHandle(), baseInterface.shaderStage().value(), GLX.GL_ACTIVE_SUBROUTINE_UNIFORMS);
        }

        @Override
        public int getMaxNumActiveVariables(GlSubroutineUniformInterface<?> baseInterface) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getMaxNumCompatibleSubroutines(GlSubroutineUniformInterface<?> baseInterface) {
            throw new UnsupportedOperationException();
        }
    };

    @SuppressWarnings("unchecked")
    public GlSubroutineUniformInterface(Program program, GlProgramInterfaceType type) {
        super(program, type, (InterfaceCore<BaseInterface<?>>) (GlGraphics.CAPABILITIES.OpenGL43 ? GL43 : GL40));
    }

    protected abstract Shader.Type shaderStage();

}
