package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.util.BeanUtils;
import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.graphics.*;
import com.vanix.easygl.graphics.program.SubroutineUniform;
import com.vanix.easygl.graphics.program.Uniform;
import com.vanix.easygl.graphics.program.UniformBlock;
import com.vanix.easygl.opengl.program.Gl20Uniform;
import com.vanix.easygl.opengl.program.Gl31UniformBlock;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.*;

public class GlProgram extends AbstractBindable<BindTarget.Default<Program>, Program> implements Program {
    private final Map<String, Uniform> uniforms = new LinkedHashMap<>();
    private final Map<Shader.Type, Map<String, SubroutineUniform>> subroutineUniforms = new EnumMap<>(Shader.Type.class);
    private int version;
    private GlProgramInterfaces interfaces;

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
            uniforms.clear();
            subroutineUniforms.clear();
            if (interfaces != null) {
                interfaces.invalidate();
            }
            int uniformCount = getAttribute(ProgramAttribute.ActiveUniforms);
            for (int i = 0; i < uniformCount; i++) {
                if (GlGraphics.CAPABILITIES.OpenGL43) {
                    var uniform = interfaces().uniform().getResource(i);
                    uniforms.put(uniform.getName(), uniform);
                } else {
                    var length = stack.mallocInt(1);
                    var size = stack.mallocInt(1);
                    var type = stack.mallocInt(1);
                    var nameBuffer = stack.malloc(getAttribute(ProgramAttribute.ActiveUniformMaxLength));
                    GLX.glGetActiveUniform(program, i, length, size, type, nameBuffer);
                    String name = MemoryUtil.memUTF8(nameBuffer, length.get());
                    uniforms.put(name, new Gl20Uniform(this, name, i, Cache.DataType.get(type.get()), size.get()));
                }
            }
            if (GlGraphics.CAPABILITIES.OpenGL40) {
                for (var shaderType : Shader.Type.values()) {
                    var subroutineUniformInterface = switch (shaderType) {
                        case Vertex -> interfaces().vertexSubroutineUniform();
                        case Fragment -> interfaces().fragmentSubroutineUniform();
                        case Geometry -> interfaces().geometrySubroutineUniform();
                        case TessControl -> interfaces().tessControlSubroutineUniform();
                        case TessEvaluation -> interfaces().tessEvaluationSubroutineUniform();
                        case Compute -> null;
                    };
                    if (subroutineUniformInterface != null) {
                        var subroutineUniformCount = subroutineUniformInterface.getActiveResources();
                        for (var i = 0; i < subroutineUniformCount; i++) {
                            var subroutineUniform = subroutineUniformInterface.getResource(i);
                            subroutineUniforms.computeIfAbsent(shaderType, key -> new HashMap<>()).put(subroutineUniform.getName(), subroutineUniform);
                        }
                    }
                }
            }
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
        if (interfaces == null) {
            interfaces = new GlProgramInterfaces(this);
        }
        return interfaces;
    }

    @Override
    public <B> B bindResources(B bean) {
        if (interfaces != null) {
            return interfaces.bindResources(bean);
        }
        BeanUtils.forEachSettersOfType(bean, Uniform.class, (name, setter) -> {
            var uniform = uniforms.get(name);
            if (uniform != null) {
                setter.accept(uniform);
            }
        });
        return bean;
    }

    @Override
    public boolean containsUniform(String name) {
        return uniforms.containsKey(name);
    }

    @Override
    public List<String> uniformNames() {
        return new ArrayList<>(uniforms.keySet());
    }

    @Override
    public Uniform getUniform(String name) {
        var uniform = uniforms.get(name);
        if (uniform == null) {
            throw new NoSuchElementException("Can not find uniform: " + name);
        }
        return uniform;
    }

    @Override
    public UniformBlock getUniformBlock(String name) {
        if (GlGraphics.CAPABILITIES.OpenGL43) {
            return interfaces.uniformBlock().getResource(name);
        }
        int index = GLX.glGetUniformBlockIndex(intHandle(), name);
        if (index == GLX.GL_INVALID_INDEX) {
            throw new NoSuchElementException("No uniform block of name find: " + name);
        }
        return new Gl31UniformBlock(this, index, name);
    }

    @Override
    public SubroutineUniform getSubroutineUniform(Shader.Type shaderType, String name) {
        return subroutineUniforms.getOrDefault(shaderType, Collections.emptyMap()).get(name);
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
