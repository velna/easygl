package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.program.SubroutineUniform;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlGraphics;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.List;

public abstract class GlSubroutineUniform<T extends SubroutineUniform<T>> extends BaseResource<T> implements SubroutineUniform<T> {
    private static final ResourceCore<GlSubroutineUniform<?>> GL40 = new ResourceCore<>() {
        @Override
        public IntBuffer doPreLoad(GlSubroutineUniform<?> resource, List<PropertyKey> keys, MemoryStack stack) {
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
        public int doGet(GlSubroutineUniform<?> resource, PropertyKey key) {
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
        public String doGetName(GlSubroutineUniform<?> resource) {
            return GLX.glGetActiveSubroutineUniformName(resource.program.intHandle(), resource.shaderStage().value(), resource.index);
        }

        @Override
        public int[] doGetActiveVariables(GlSubroutineUniform<?> resource) {
            throw new UnsupportedOperationException();
        }
    };

    @SuppressWarnings("unchecked")
    public GlSubroutineUniform(Program program, GlProgramInterfaceType interfaceType, int index) {
        super(program, interfaceType, index, (ResourceCore<BaseResource<?>>) (GlGraphics.CAPABILITIES.OpenGL43 ? GL43 : GL40));
    }

}
