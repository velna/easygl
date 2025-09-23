package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.ProgramInterface;
import com.vanix.easygl.graphics.ProgramResource;
import com.vanix.easygl.opengl.program.*;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public enum GlProgramInterfaceType implements IntEnum {
    Uniform(GLX.GL_UNIFORM, GlUniformInterface.class, GlUniformInterface::new),
    UniformBlock(GLX.GL_UNIFORM_BLOCK, GlUniformBlockInterface.class, GlUniformBlockInterface::new),
    AtomicCounterBuffer(GLX.GL_ATOMIC_COUNTER_BUFFER, GlAtomicCounterBufferInterface.class, GlAtomicCounterBufferInterface::new),
    ProgramInput(GLX.GL_PROGRAM_INPUT, GlProgramInputInterface.class, GlProgramInputInterface::new),
    ProgramOutput(GLX.GL_PROGRAM_OUTPUT, GlProgramOutputInterface.class, GlProgramOutputInterface::new),
    VertexSubroutine(GLX.GL_VERTEX_SUBROUTINE, GlSubroutineInterface.class, GlSubroutineInterface::new),
    TessControlSubroutine(GLX.GL_TESS_CONTROL_SUBROUTINE, GlSubroutineInterface.class, GlSubroutineInterface::new),
    TessEvaluationSubroutine(GLX.GL_TESS_EVALUATION_SUBROUTINE, GlSubroutineInterface.class, GlSubroutineInterface::new),
    GeometrySubroutine(GLX.GL_GEOMETRY_SUBROUTINE, GlSubroutineInterface.class, GlSubroutineInterface::new),
    FragmentSubroutine(GLX.GL_FRAGMENT_SUBROUTINE, GlSubroutineInterface.class, GlSubroutineInterface::new),
    ComputeSubroutine(GLX.GL_COMPUTE_SUBROUTINE, GlSubroutineInterface.class, GlSubroutineInterface::new),
    VertexSubroutineUniform(GLX.GL_VERTEX_SUBROUTINE_UNIFORM, GlSubroutineUniformInterface.class, GlSubroutineUniformInterface::new),
    TessControlSubroutineUniform(GLX.GL_TESS_CONTROL_SUBROUTINE_UNIFORM, GlSubroutineUniformInterface.class, GlSubroutineUniformInterface::new),
    TessEvaluationSubroutineUniform(GLX.GL_TESS_EVALUATION_SUBROUTINE_UNIFORM, GlSubroutineUniformInterface.class, GlSubroutineUniformInterface::new),
    GeometrySubroutineUniform(GLX.GL_GEOMETRY_SUBROUTINE_UNIFORM, GlSubroutineUniformInterface.class, GlSubroutineUniformInterface::new),
    FragmentSubroutineUniform(GLX.GL_FRAGMENT_SUBROUTINE_UNIFORM, GlSubroutineUniformInterface.class, GlSubroutineUniformInterface::new),
    TransformFeedbackVarying(GLX.GL_TRANSFORM_FEEDBACK_VARYING, GlSubroutineUniformInterface.class, GlSubroutineUniformInterface::new),
    BufferVariable(GLX.GL_BUFFER_VARIABLE, GlBufferVariableInterface.class, GlBufferVariableInterface::new),
    ShaderStorageBlock(GLX.GL_SHADER_STORAGE_BLOCK, GlShaderStorageBlockInterface.class, GlShaderStorageBlockInterface::new),
    TransformFeedbackBuffer(GLX.GL_TRANSFORM_FEEDBACK_BUFFER, GlTransformFeedbackBufferInterface.class, GlTransformFeedbackBufferInterface::new);

    final int value;
    final BitSet<ProgramResource.PropertyKey> properties = BitSet.of((ToIntFunction<ProgramResource.PropertyKey>) ProgramResource.PropertyKey::mask);
    final Class<? extends BaseInterface<?>> interfaceType;
    final BiFunction<Program, GlProgramInterfaceType, ? extends BaseInterface<?>> factory;

    GlProgramInterfaceType(int value, Class<? extends BaseInterface<?>> interfaceType, BiFunction<Program, GlProgramInterfaceType, ? extends BaseInterface<?>> factory) {
        this.value = value;
        this.interfaceType = interfaceType;
        this.factory = factory;
        Class<?> resourceType = (Class<?>) TypeUtils.getTypeArguments(interfaceType, ProgramInterface.class)
                .get(ProgramInterface.class.getTypeParameters()[0]);
        for (var ifc : resourceType.getInterfaces()) {
            if (ProgramResource.class.isAssignableFrom(ifc) && ifc.getDeclaringClass() == ProgramResource.class) {
                var propertyKey = ProgramResource.PropertyKey.valueOf(ifc.getSimpleName());
                properties.add(propertyKey);
            }
        }
    }

    GlProgramInterfaceType(int value, Class<? extends BaseInterface<?>> interfaceType, Function<Program, ? extends BaseInterface<?>> factory) {
        this(value, interfaceType, ((program, interfaceType1) -> factory.apply(program)));
    }

    public BitSet<ProgramResource.PropertyKey> properties() {
        return properties;
    }

    @Override
    public int value() {
        return value;
    }
}
