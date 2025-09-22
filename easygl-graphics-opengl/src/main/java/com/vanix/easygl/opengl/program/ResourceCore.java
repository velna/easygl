package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.ProgramResource;
import com.vanix.easygl.opengl.GLX;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.List;

public interface ResourceCore<B extends BaseResource<?>> {
    default IntBuffer doPreLoad(B resource, List<ProgramResource.PropertyKey> keys, MemoryStack stack) {
        var valueBuf = stack.mallocInt(keys.size());
        var props = stack.mallocInt(keys.size());
        for (int i = 0; i < keys.size(); i++) {
            props.put(i, keys.get(i).value());
        }
        GLX.glGetProgramResourceiv(resource.program.intHandle(), resource.interfaceType.value(), resource.index, props, null, valueBuf);
        return valueBuf;
    }

    default int doGet(B resource, ProgramResource.PropertyKey key) {
        try (var stack = MemoryStack.stackPush()) {
            var prop = stack.mallocInt(1).put(0, key.value());
            var value = stack.mallocInt(1);
            GLX.glGetProgramResourceiv(resource.program.intHandle(), resource.interfaceType.value(), resource.index, prop, null, value);
            return value.get(0);
        }
    }

    default String doGetName(B resource) {
        return GLX.glGetProgramResourceName(resource.program.intHandle(), resource.interfaceType.value(), resource.index);
    }

    default int[] doGetActiveVariables(B resource) {
        int activeResources = resource.queryInt(ProgramResource.PropertyKey.ActiveVariables);
        int[] props = new int[]{ProgramResource.PropertyKey.ActiveVariables.value()};
        int[] data = new int[activeResources];
        GLX.glGetProgramResourceiv(resource.program.intHandle(), resource.interfaceType.value(), resource.index, props, null, data);
        return data;
    }

}
