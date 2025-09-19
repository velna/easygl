package com.vanix.easygl.opengl.program;

import com.vanix.easygl.commons.bufferio.BufferIO;
import com.vanix.easygl.commons.bufferio.StructBufferIO;
import com.vanix.easygl.graphics.Buffer;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.graphics.program.UniformBlock;

import java.nio.ByteBuffer;

public interface UniformBlockDefaults extends UniformBlock {

    @Override
    default UniformBlock bind(Buffer.BindingPoint bindingPoint) {
        GLX.glUniformBlockBinding(program().intHandle(), index(), bindingPoint.value());
        return this;
    }

    @Override
    default <T> StructBufferIO<T> createBufferIO(T bean, ByteBuffer storage) {
        int programHandle = program().intHandle();
        int n = GLX.glGetActiveUniformBlocki(programHandle, index(), GLX.GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS);
        GLX.checkError();
        int[] uniformIndices = new int[n];
        GLX.glGetActiveUniformBlockiv(programHandle, index(), GLX.GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES, uniformIndices);
        GLX.checkError();
        int[] uniformOffsets = new int[n];
        GLX.glGetActiveUniformsiv(programHandle, uniformIndices, GLX.GL_UNIFORM_OFFSET, uniformOffsets);
        GLX.checkError();
        var structBufferIoBuilder = new StructBufferIO.Builder<>((StructBufferIO<T>) BufferIO.ofBean(bean), getBufferDataSize());
        for (int i = 0; i < uniformIndices.length; i++) {
            var uniformName = GLX.glGetActiveUniformName(programHandle, uniformIndices[i]);
            GLX.checkError();
            structBufferIoBuilder.withField(uniformName, uniformOffsets[i]);
        }
        return structBufferIoBuilder.build();
    }
}
