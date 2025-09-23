package com.vanix.easygl.opengl.program;

import com.vanix.easygl.commons.util.LazyList;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.ProgramInterface;
import com.vanix.easygl.graphics.ProgramResource;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.opengl.Invalidatable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseInterface<T extends ProgramResource<T>> implements Invalidatable,
        ProgramInterface.Named<T>,
        ProgramInterface.Variable<T> {
    protected static final InterfaceCore<BaseInterface<?>> GL43 = new InterfaceCore<>() {};
    protected final Program program;
    protected final GlProgramInterfaceType type;
    protected final InterfaceCore<BaseInterface<?>> interfaceCore;
    private final List<T> resources = new ArrayList<>();
    private final List<T> resourcesLazy;

    public BaseInterface(Program program, GlProgramInterfaceType type) {
        this(program, type, GL43);
    }

    public BaseInterface(Program program, GlProgramInterfaceType type, InterfaceCore<BaseInterface<?>> interfaceCore) {
        this.program = program;
        this.type = type;
        this.interfaceCore = interfaceCore;
        resourcesLazy = LazyList.lazyList(resources, index -> newResource(type, index));
    }

    public Program program() {
        return program;
    }

    protected abstract T newResource(GlProgramInterfaceType type, int index);

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
        int index = interfaceCore.getIndexByName(this, name);
        return index == GLX.GL_INVALID_INDEX ? null : resourcesLazy.get(index);
    }

    @Override
    public int getActiveResources() {
        return interfaceCore.getActiveResources(this);
    }

    @Override
    public int getMaxNumActiveVariables() {
        return interfaceCore.getMaxNumActiveVariables(this);
    }

}
