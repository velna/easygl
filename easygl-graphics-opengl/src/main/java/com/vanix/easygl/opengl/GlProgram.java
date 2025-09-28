package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.graphics.*;
import com.vanix.easygl.graphics.program.Uniform;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GlProgram extends AbstractBindable<BindTarget.Default<Program>, Program> implements Program {
    private int version;
    private final GlProgramInterfaces interfaces = new GlProgramInterfaces(this);

    protected GlProgram() {
        this(GLX.glCreateProgram());
    }

    protected GlProgram(int handle) {
        super(handle, Target, GLX::glDeleteProgram);
    }

    @Override
    public Program attach(Shader shader) {
        GLX.glAttachShader(intHandle(), shader.intHandle());
        GLX.checkError();
        return self();
    }

    @Override
    public ShaderArray getAttachedShaders() {
        int size = GLX.glGetProgrami(intHandle(), GLX.GL_ATTACHED_SHADERS);
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocInt(size);
            GLX.glGetAttachedShaders(intHandle(), null, buffer);
            List<Shader> shaders = new ArrayList<>(size);
            while (buffer.hasRemaining()) {
                shaders.add(new GlShader(buffer.get(), null));
            }
            return new GlShaderArray(shaders);
        }
    }

    @Override
    public Program detach(Shader shader) {
        GLX.glDetachShader(intHandle(), shader.intHandle());
        GLX.checkError();
        return self();
    }

    @Override
    public Program bindFragData(FrameInnerBuffer.DrawBuffer drawBuffer, String varyingOutVariableName) {
        GLX.glBindFragDataLocation(intHandle(), drawBuffer.value(), varyingOutVariableName);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameInnerBuffer.DrawBuffer getFragData(String varyingOutVariableName) {
        int location = GLX.glGetFragDataLocation(intHandle(), varyingOutVariableName);
        return location < 0 ? null : FrameInnerBuffer.DrawBuffer.of(location);
    }

    @Override
    public Program bindFragData(FrameInnerBuffer.DrawBuffer drawBuffer, String varyingOutVariableName, int index) {
        GLX.glBindFragDataLocationIndexed(intHandle(), drawBuffer.value(), index, varyingOutVariableName);
        GLX.checkError();
        return this;
    }

    @Override
    public int getFragDataIndex(String varyingOutVariableName) {
        return GLX.glGetFragDataIndex(intHandle(), varyingOutVariableName);
    }

    @Override
    public Program link() {
        int program = intHandle();
        GLX.glLinkProgram(program);
        try (var stack = MemoryStack.stackPush()) {
            IntBuffer success = stack.mallocInt(1);
            GLX.glGetProgramiv(program, GLX.GL_LINK_STATUS, success);
            if (success.get() == 0) {
                String infoLog = GLX.glGetProgramInfoLog(program);
                throw new GraphicsException("error link program: " + infoLog);
            }
            version++;
            interfaces.invalidate();
            return self();
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        GlProgram glProgram = (GlProgram) object;
        return version == glProgram.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), version);
    }

    @Override
    public Program validate() {
        GLX.glValidateProgram(intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public Program setBinaryRetrievable(boolean retrievable) {
        assertBinding();
        GLX.glProgramParameteri(intHandle(), GLX.GL_PROGRAM_BINARY_RETRIEVABLE_HINT, retrievable ? GLX.GL_TRUE : GLX.GL_FALSE);
        return this;
    }

    @Override
    public Binary getBinary() {
        assertBinding();
        int len = GLX.glGetProgrami(intHandle(), GLX.GL_PROGRAM_BINARY_LENGTH);
        if (len <= 0) {
            GLX.checkError();
            return null;
        }
        ByteBuffer buffer = ByteBuffer.allocate(len);
        int[] format = new int[1];
        GLX.glGetProgramBinary(intHandle(), null, format, buffer);
        GLX.checkError();
        return new Binary(format[0], buffer);
    }

    @Override
    public Program loadBinary(int format, ByteBuffer data) {
        assertBinding();
        GLX.glProgramBinary(intHandle(), format, data);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setSeparable(boolean separable) {
        assertBinding();
        GLX.glProgramParameteri(intHandle(), GLX.GL_PROGRAM_SEPARABLE, separable ? GLX.GL_TRUE : GLX.GL_FALSE);
        return this;
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public GlProgramInterfaces interfaces() {
        return interfaces;
    }

    @Override
    public <B> B bindResources(B bean) {
        return interfaces().bindResources(bean);
    }

    @Override
    public boolean containsUniform(String name) {
        return interfaces.uniform().containsResource(name);
    }

    @Override
    public Uniform getUniform(String name) {
        return interfaces.uniform().getResource(name);
    }

    @Override
    public boolean getAttribute(ProgramAttribute.Bool attribute) {
        return GLX.glGetProgrami(intHandle(), attribute.value()) == GLX.GL_TRUE;
    }

    @Override
    public int getAttribute(ProgramAttribute.Int attribute) {
        return GLX.glGetProgrami(intHandle(), attribute.value());
    }

    @Override
    public Program bindVertexAttribute(VertexAttribute vertexAttribute, String variableName) {
        GLX.glBindAttribLocation(intHandle(), vertexAttribute.value(), variableName);
        GLX.checkError();
        return this;
    }

    @Override
    public Variable getActiveVertexAttribute(VertexAttribute vertexAttribute) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer lengthBuffer = stack.mallocInt(1);
            IntBuffer sizeBuffer = stack.mallocInt(1);
            IntBuffer typeBuffer = stack.mallocInt(1);
            ByteBuffer nameBuffer = stack.malloc(getAttribute(ProgramAttribute.ActiveAttributeMaxLength));
            GLX.glGetActiveAttrib(intHandle(), vertexAttribute.value(), lengthBuffer, sizeBuffer, typeBuffer, nameBuffer);
            GLX.checkError();
            return new Variable(sizeBuffer.get(), Cache.DataType.get(typeBuffer.get()), MemoryUtil.memUTF8(nameBuffer, lengthBuffer.get()));
        }
    }

    @Override
    public int getVertexAttributeLocation(String name) {
        return GLX.glGetAttribLocation(intHandle(), name);
    }

    @Override
    public Program transformFeedbackVaryings(boolean interleaved, String... varyings) {
        GLX.glTransformFeedbackVaryings(intHandle(), varyings, interleaved ? GLX.GL_INTERLEAVED_ATTRIBS : GLX.GL_SEPARATE_ATTRIBS);
        GLX.checkError();
        return this;
    }

    @Override
    public Variable getTransformFeedbackVarying(int varyingsIndex) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer lengthBuffer = stack.mallocInt(1);
            IntBuffer sizeBuffer = stack.mallocInt(1);
            IntBuffer typeBuffer = stack.mallocInt(1);
            ByteBuffer nameBuffer = stack.malloc(getAttribute(ProgramAttribute.ActiveAttributeMaxLength));
            GLX.glGetTransformFeedbackVarying(intHandle(), varyingsIndex, lengthBuffer, sizeBuffer, typeBuffer, nameBuffer);
            GLX.checkError();
            return new Variable(sizeBuffer.get(), Cache.DataType.get(typeBuffer.get()), MemoryUtil.memUTF8(nameBuffer, lengthBuffer.get()));
        }
    }
}
