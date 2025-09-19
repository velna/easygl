package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlGraphics;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.Uniform;
import org.lwjgl.system.MemoryStack;

public class Gl20Uniform implements GlUniform {

    private final Program program;
    private final String name;
    private final int index;
    private final DataType dataType;
    private final int size;
    private final int location;

    public Gl20Uniform(Program program, String name, int index, DataType dataType, int size) {
        this.program = program;
        this.name = name;
        this.index = index;
        this.dataType = dataType;
        this.size = size;
        this.location = GLX.glGetUniformLocation(program.intHandle(), name);
    }

    @Override
    public Program program() {
        return program;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public Uniform preLoad(PropertyKey... keys) {
        // preload nothing
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DataType getType() {
        return dataType;
    }

    @Override
    public int getArraySize() {
        return size;
    }

    @Override
    public int getOffset() {
        return getParamGl31(GLX.GL_UNIFORM_OFFSET);
    }

    @Override
    public int getBlockIndex() {
        return getParamGl31(GLX.GL_UNIFORM_BLOCK_INDEX);
    }

    @Override
    public int getArrayStride() {
        return getParamGl31(GLX.GL_UNIFORM_ARRAY_STRIDE);
    }

    @Override
    public int getMatrixStride() {
        return getParamGl31(GLX.GL_UNIFORM_MATRIX_STRIDE);
    }

    @Override
    public boolean isRowMajor() {
        return getParamGl31(GLX.GL_UNIFORM_IS_ROW_MAJOR) == GLX.GL_TRUE;
    }

    @Override
    public int getAtomicCounterBufferIndex() {
        return getParamGl31(GLX.GL_UNIFORM_ATOMIC_COUNTER_BUFFER_INDEX);
    }

    @Override
    public boolean isReferencedByVertexShader() {
        return getBooleanParamGl43(GLX.GL_REFERENCED_BY_VERTEX_SHADER);
    }

    @Override
    public boolean isReferencedByTessControlShader() {
        return getBooleanParamGl43(GLX.GL_REFERENCED_BY_TESS_CONTROL_SHADER);
    }

    @Override
    public boolean isReferencedByTessEvaluationShader() {
        return getBooleanParamGl43(GLX.GL_REFERENCED_BY_TESS_EVALUATION_SHADER);
    }

    @Override
    public boolean isReferencedByGeometryShader() {
        return getBooleanParamGl43(GLX.GL_REFERENCED_BY_GEOMETRY_SHADER);
    }

    @Override
    public boolean isReferencedByFragmentShader() {
        return getBooleanParamGl43(GLX.GL_REFERENCED_BY_FRAGMENT_SHADER);
    }

    @Override
    public boolean isReferencedByComputeShader() {
        return getBooleanParamGl43(GLX.GL_REFERENCED_BY_COMPUTE_SHADER);
    }

    @Override
    public int getLocation() {
        return location;
    }

    private int getParamGl31(int param) {
        if (GlGraphics.CAPABILITIES.OpenGL31) {
            try (var stack = MemoryStack.stackPush()) {
                var indexBuffer = stack.mallocInt(1).put(0, index);
                var valueBuffer = stack.mallocInt(1);
                GLX.glGetActiveUniformsiv(program.intHandle(), indexBuffer, param, valueBuffer);
                GLX.checkError();
                return valueBuffer.get();
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private boolean getBooleanParamGl43(int param) {
        if (GlGraphics.CAPABILITIES.OpenGL43) {
            int[][] data = new int[2][1];
            data[0][0] = param;
            GLX.glGetProgramResourceiv(program.intHandle(), GLX.GL_UNIFORM, index, data[0], null, data[1]);
            return data[1][0] == GLX.GL_TRUE;
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
