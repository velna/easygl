package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.Shader;
import com.vanix.easygl.graphics.program.Subroutine;
import com.vanix.easygl.graphics.program.SubroutineUniform;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlGraphics;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.opengl.Invalidatable;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class GlSubroutineUniform extends BaseResource<SubroutineUniform> implements SubroutineUniform, Invalidatable {
    private static final ResourceCore<GlSubroutineUniform> GL40 = new ResourceCore<>() {
        @Override
        public IntBuffer doPreLoad(GlSubroutineUniform resource, List<PropertyKey> keys, MemoryStack stack) {
            var valueBuf = stack.callocInt(keys.size());
            for (int i = 0; i < keys.size(); i++) {
                var key = keys.get(i);
                if (key == PropertyKey.Location) {
                    valueBuf.put(i, GLX.glGetSubroutineUniformLocation(resource.program.intHandle(), resource.shaderStage().value(), resource.getName()));
                } else {
                    GLX.glGetActiveSubroutineUniformiv(resource.program.intHandle(), resource.shaderStage().value(), resource.index, switch (keys.get(i)) {
                        case ArraySize -> GLX.GL_UNIFORM_SIZE;
                        case CompatibleSubroutines -> GLX.GL_COMPATIBLE_SUBROUTINES;
                        case NumCompatibleSubroutines -> GLX.GL_NUM_COMPATIBLE_SUBROUTINES;
                        default -> throw new IllegalArgumentException("Invalid property key: " + key);
                    }, valueBuf.position(i));
                }
            }
            return valueBuf;
        }

        @Override
        public int doGet(GlSubroutineUniform resource, PropertyKey key) {
            if (key == PropertyKey.Location) {
                return GLX.glGetSubroutineUniformLocation(resource.program.intHandle(), resource.shaderStage().value(), resource.getName());
            } else {
                return GLX.glGetActiveSubroutineUniformi(resource.program.intHandle(), resource.shaderStage().value(), resource.index, switch (key) {
                    case ArraySize -> GLX.GL_UNIFORM_SIZE;
                    case CompatibleSubroutines -> GLX.GL_COMPATIBLE_SUBROUTINES;
                    case NumCompatibleSubroutines -> GLX.GL_NUM_COMPATIBLE_SUBROUTINES;
                    default -> throw new IllegalArgumentException("Invalid property key: " + key);
                });
            }
        }

        @Override
        public String doGetName(GlSubroutineUniform resource) {
            return GLX.glGetActiveSubroutineUniformName(resource.program.intHandle(), resource.shaderStage().value(), resource.index);
        }

        @Override
        public int[] doGetActiveVariables(GlSubroutineUniform resource) {
            throw new UnsupportedOperationException();
        }
    };

    private final Shader.Type shaderStage;
    private List<Subroutine> compatibleSubroutines;

    @SuppressWarnings("unchecked")
    public GlSubroutineUniform(Program program, GlProgramInterfaceType interfaceType, int index) {
        super(program, interfaceType, index, (ResourceCore<BaseResource<?>>) (GlGraphics.CAPABILITIES.OpenGL43 ? GL43 : GL40));
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
    public Shader.Type shaderStage() {
        return shaderStage;
    }

    @Override
    public void invalidate() {
        compatibleSubroutines = null;
    }

    @Override
    public List<Subroutine> getCompatibleSubroutines() {
        if (compatibleSubroutines == null) {
            try (var stack = MemoryStack.stackPush()) {
                var key = PropertyKey.CompatibleSubroutines;
                var props = stack.mallocInt(1);
                props.put(0, key.value());
                var intBuf = stack.mallocInt(getNumCompatibleSubroutines());
                if (GlGraphics.CAPABILITIES.OpenGL43) {
                    GLX.glGetProgramResourceiv(program.intHandle(), interfaceType.value(), index, props, null, intBuf);
                } else {
                    GLX.glGetActiveSubroutineUniformiv(program.intHandle(), shaderStage().value(), index, GLX.GL_COMPATIBLE_SUBROUTINES, intBuf);
                }
                compatibleSubroutines = new ArrayList<>(intBuf.remaining());
                while (intBuf.hasRemaining()) {
                    compatibleSubroutines.add(program.interfaces().subroutine(shaderStage()).getResource(intBuf.get()));
                }
            }
        }
        return compatibleSubroutines;
    }

    @Override
    public int getNumCompatibleSubroutines() {
        return queryInt(PropertyKey.NumCompatibleSubroutines);
    }

}
