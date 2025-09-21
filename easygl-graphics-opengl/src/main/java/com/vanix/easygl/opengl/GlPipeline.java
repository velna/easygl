package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.graphics.Pipeline;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.Shader;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;
import java.util.function.IntConsumer;

public class GlPipeline extends AbstractBindable<BindTarget.Default<Pipeline>, Pipeline> implements Pipeline {
    public GlPipeline() {
        this(GLX.glGenProgramPipelines());
    }

    public GlPipeline(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteProgramPipelines);
    }

    @Override
    public Pipeline useProgramStages(Program program, Stage stage) {
        assertBinding();
        GLX.glUseProgramStages(intHandle(), stage.value(), program.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public Pipeline useProgramStages(Program program, BitSet<Stage> stages) {
        assertBinding();
        GLX.glUseProgramStages(intHandle(), stages.value(), program.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public Pipeline activeShaderProgram(Program program) {
        assertBinding();
        GLX.glActiveShaderProgram(intHandle(), program.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public Pipeline validate() {
        GLX.glValidateProgramPipeline(intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public Program getActiveProgram() {
        int handle = GLX.glGetProgramPipelinei(intHandle(), GLX.GL_ACTIVE_PROGRAM);
        return handle > 0 ? new GlProgram(handle) : null;
    }

    @Override
    public Program getShaderProgram(Shader.Type shaderType) {
        int handle = GLX.glGetProgramPipelinei(intHandle(), shaderType.value());
        return handle > 0 ? new GlProgram(handle) : null;
    }

    @Override
    public String getInfoLog() {
        int logLen = GLX.glGetProgramPipelinei(intHandle(), GLX.GL_INFO_LOG_LENGTH);
        try (var stack = MemoryStack.stackPush()) {
            var logBuf = stack.malloc(logLen);
            GLX.glGetProgramInfoLog(intHandle(), (IntBuffer) null, logBuf);
            return MemoryUtil.memUTF8(logBuf, logLen);
        }
    }
}
