package com.vanix.easygl.opengl.program;

import com.vanix.easygl.commons.util.LazyList;
import com.vanix.easygl.graphics.*;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.opengl.Invalidatable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseInterface<T extends ProgramResource<T>> implements Invalidatable,
        ProgramInterface.Named<T>,
        ProgramInterface.Variable<T>,
        ProgramInterface.SubroutineUniform<T> {
    protected final Program program;
    private final GlProgramInterfaceType type;
    private final List<T> resources = new ArrayList<>();
    private final List<T> resourcesLazy = LazyList.lazyList(resources, this::newResource);

    public BaseInterface(Program program, GlProgramInterfaceType type) {
        this.program = program;
        this.type = type;
    }

    protected abstract T newResource(int index);

    @Override
    public void invalidate() {
        resources.clear();
    }

    @Override
    public T getResource(int index) {
        return index >= getActiveResources() ? null : resourcesLazy.get(index);
    }

    @Override
    public List<T> getResources() {
        int n = getActiveResources();
        if (n <= 0) {
            return Collections.emptyList();
        } else {
            List<T> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(resourcesLazy.get(i));
            }
            return list;
        }
    }

    @Override
    public T getResource(String name) {
        int index = GLX.glGetProgramResourceIndex(program.intHandle(), type.value(), name);
        return index == GLX.GL_INVALID_INDEX ? null : resourcesLazy.get(index);
    }

    @Override
    public int getActiveResources() {
        return GLX.glGetProgramInterfacei(program.intHandle(), GLX.GL_ACTIVE_RESOURCES, type.value());
    }

    @Override
    public int getMaxNumActiveVariables() {
        return GLX.glGetProgramInterfacei(program.intHandle(), GLX.GL_MAX_NUM_ACTIVE_VARIABLES, type.value());
    }

    @Override
    public int getMaxNumCompatibleSubroutines() {
        return GLX.glGetProgramInterfacei(program.intHandle(), GLX.GL_MAX_NUM_COMPATIBLE_SUBROUTINES, type.value());
    }
}
