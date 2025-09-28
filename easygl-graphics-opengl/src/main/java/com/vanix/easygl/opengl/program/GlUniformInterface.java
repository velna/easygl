package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.ProgramAttribute;
import com.vanix.easygl.graphics.ProgramResource.PropertyKey;
import com.vanix.easygl.graphics.program.Uniform;
import com.vanix.easygl.graphics.program.UniformInterface;
import com.vanix.easygl.opengl.Cache;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlGraphics;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class GlUniformInterface extends BaseInterface<Uniform> implements UniformInterface {
    private static final PropertyKey[] PRELOAD_KEYS = new PropertyKey[]{PropertyKey.Type, PropertyKey.ArraySize, PropertyKey.Location};
    private static final InterfaceCore<GlUniformInterface> GL31 = new InterfaceCore<GlUniformInterface>() {
        @Override
        public int getIndexByName(GlUniformInterface baseInterface, String name) {
            return GLX.glGetUniformIndices(baseInterface.program.intHandle(), name);
        }

        @Override
        public int getActiveResources(GlUniformInterface baseInterface) {
            return baseInterface.program.getAttribute(ProgramAttribute.ActiveUniforms);
        }

    };

    public GlUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.Uniform, GlGraphics.CAPABILITIES.OpenGL43 ? GL43 : GL31);
    }

    @Override
    protected Uniform newResource(GlProgramInterfaceType interfaceType, int index) {
        if (GlGraphics.CAPABILITIES.OpenGL43) {
            return new Gl43Uniform(program, index).preLoad(PRELOAD_KEYS);
        } else {
            try (var stack = MemoryStack.stackPush()) {
                var length = stack.mallocInt(1);
                var size = stack.mallocInt(1);
                var type = stack.mallocInt(1);
                var nameBuffer = stack.malloc(program.getAttribute(ProgramAttribute.ActiveUniformMaxLength));
                GLX.glGetActiveUniform(program.intHandle(), index, length, size, type, nameBuffer);
                String name = MemoryUtil.memUTF8(nameBuffer, length.get());
                return new Gl20Uniform(program, name, index, Cache.DataType.get(type.get()), size.get());
            }
        }
    }
}
