package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.UniformBlock;

public class Gl31UniformBlock extends BaseGl31Resource implements UniformBlockDefaults {

    public Gl31UniformBlock(Program program, int index, String name) {
        super(program, index, name);
    }

    @Override
    public UniformBlock preLoad(PropertyKey... keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getBufferBinding() {
        return GLX.glGetActiveUniformBlocki(program.intHandle(), index, GLX.GL_UNIFORM_BLOCK_BINDING);
    }

    @Override
    public int getBufferDataSize() {
        return GLX.glGetActiveUniformBlocki(program.intHandle(), index, GLX.GL_UNIFORM_BLOCK_DATA_SIZE);
    }

    @Override
    public int getNumActiveVariables() {
        return GLX.glGetActiveUniformBlocki(program.intHandle(), index, GLX.GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS);
    }

    @Override
    public int[] getActiveVariables() {
        int[] indices = new int[getNumActiveVariables()];
        GLX.glGetActiveUniformBlockiv(program.intHandle(), index, GLX.GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES, indices);
        return indices;
    }

    @Override
    public boolean isReferencedByVertexShader() {
        return GLX.glGetActiveUniformBlocki(program.intHandle(), index, GLX.GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER) == GLX.GL_TRUE;
    }

    @Override
    public boolean isReferencedByTessControlShader() {
        return GLX.glGetActiveUniformBlocki(program.intHandle(), index, GLX.GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER) == GLX.GL_TRUE;
    }

    @Override
    public boolean isReferencedByTessEvaluationShader() {
        return GLX.glGetActiveUniformBlocki(program.intHandle(), index, GLX.GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER) == GLX.GL_TRUE;
    }

    @Override
    public boolean isReferencedByGeometryShader() {
        return GLX.glGetActiveUniformBlocki(program.intHandle(), index, GLX.GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER) == GLX.GL_TRUE;
    }

    @Override
    public boolean isReferencedByFragmentShader() {
        return GLX.glGetActiveUniformBlocki(program.intHandle(), index, GLX.GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER) == GLX.GL_TRUE;
    }

    @Override
    public boolean isReferencedByComputeShader() {
        return GLX.glGetActiveUniformBlocki(program.intHandle(), index, GLX.GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER) == GLX.GL_TRUE;
    }

}
