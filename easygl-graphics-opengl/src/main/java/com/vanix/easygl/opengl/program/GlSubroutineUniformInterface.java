package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.graphics.program.Subroutine;
import com.vanix.easygl.graphics.program.SubroutineUniform;
import com.vanix.easygl.graphics.program.SubroutineUniformInterface;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlGraphics;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import org.lwjgl.system.MemoryStack;

import java.util.List;

public class GlSubroutineUniformInterface extends BaseInterface<SubroutineUniform> implements SubroutineUniformInterface {
    private static final InterfaceCore<GlSubroutineUniformInterface> GL40 = new InterfaceCore<>() {
        @Override
        public int getIndexByName(GlSubroutineUniformInterface baseInterface, String name) {
            return GLX.glGetSubroutineIndex(baseInterface.program.intHandle(), baseInterface.shaderStage.value(), name);
        }

        @Override
        public int getActiveResources(GlSubroutineUniformInterface baseInterface) {
            return GLX.glGetProgramStagei(baseInterface.program.intHandle(), baseInterface.shaderStage.value(), GLX.GL_ACTIVE_SUBROUTINE_UNIFORMS);
        }

        @Override
        public int getMaxNumActiveVariables(GlSubroutineUniformInterface baseInterface) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getMaxNumCompatibleSubroutines(GlSubroutineUniformInterface baseInterface) {
            throw new UnsupportedOperationException();
        }
    };

    private final Shader.Type shaderStage;

    @SuppressWarnings("unchecked")
    public GlSubroutineUniformInterface(Program program, GlProgramInterfaceType interfaceType) {
        super(program, interfaceType, (InterfaceCore<BaseInterface<?>>) (GlGraphics.CAPABILITIES.OpenGL43 ? GL43 : GL40));
        shaderStage = switch (interfaceType) {
            case VertexSubroutineUniform -> Shader.Type.Vertex;
            case FragmentSubroutineUniform -> Shader.Type.Fragment;
            case TessEvaluationSubroutineUniform -> Shader.Type.TessEvaluation;
            case TessControlSubroutineUniform -> Shader.Type.TessControl;
            case GeometrySubroutineUniform -> Shader.Type.Geometry;
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    protected SubroutineUniform newResource(GlProgramInterfaceType type, int index) {
        return new GlSubroutineUniform(program, type, index);
    }

    @Override
    public int getMaxNumCompatibleSubroutines() {
        return interfaceCore.getMaxNumCompatibleSubroutines(this);
    }

    @Override
    public SubroutineUniformInterface load(List<Subroutine> subroutines) {
        if (getActiveResources() == 0) {
            if (!subroutines.isEmpty()) {
                throw new IllegalArgumentException("No subroutine uniform to load.");
            }
            return this;
        }
        program.assertBinding();
        try (var stack = MemoryStack.stackPush()) {
            var subroutineUniforms = getResources();
            var indices = stack.mallocInt(subroutineUniforms.size());
            for (var su : subroutineUniforms) {
                indices.put(su.index());
            }
            GLX.glUniformSubroutinesuiv(shaderStage.value(), indices.clear());
            GLX.checkError();
        }
        return this;
    }
}
