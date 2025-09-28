package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlGraphics;

public interface InterfaceCore<T extends BaseInterface<?>> {
    default int getIndexByName(T baseInterface, String name) {
        if (!GlGraphics.CAPABILITIES.OpenGL43) {
            return GLX.GL_INVALID_INDEX;
        }
        return GLX.glGetProgramResourceIndex(baseInterface.program.intHandle(), baseInterface.type.value(), name);
    }

    default int getActiveResources(T baseInterface) {
        if (!GlGraphics.CAPABILITIES.OpenGL43) {
            return 0;
        }
        return GLX.glGetProgramInterfacei(baseInterface.program.intHandle(), GLX.GL_ACTIVE_RESOURCES, baseInterface.type.value());
    }

    default int getMaxNumActiveVariables(T baseInterface) {
        if (!GlGraphics.CAPABILITIES.OpenGL43) {
            return 0;
        }
        return GLX.glGetProgramInterfacei(baseInterface.program.intHandle(), GLX.GL_MAX_NUM_ACTIVE_VARIABLES, baseInterface.type.value());
    }

    default int getMaxNumCompatibleSubroutines(T baseInterface) {
        if (!GlGraphics.CAPABILITIES.OpenGL43) {
            return 0;
        }
        return GLX.glGetProgramInterfacei(baseInterface.program.intHandle(), GLX.GL_MAX_NUM_COMPATIBLE_SUBROUTINES, baseInterface.type.value());
    }
}
