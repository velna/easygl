package com.vanix.easygl.opengl.program;

import com.vanix.easygl.commons.util.BeanUtils;
import com.vanix.easygl.commons.util.LazyList;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.ProgramInterface;
import com.vanix.easygl.graphics.ProgramResource;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import com.vanix.easygl.opengl.Invalidatable;

import java.util.*;

public abstract class BaseInterface<T extends ProgramResource<T>> implements Invalidatable,
        ProgramInterface<T> {
    protected static final InterfaceCore<BaseInterface<?>> GL43 = new InterfaceCore<>() {};
    protected final Program program;
    protected final GlProgramInterfaceType type;
    protected final InterfaceCore<BaseInterface<?>> interfaceCore;
    private final List<T> resources = new ArrayList<>();
    private final Map<String, T> resourceMap = new HashMap<>();
    private final List<T> resourcesLazy;
    private int activeResources = -1;

    public BaseInterface(Program program, GlProgramInterfaceType type) {
        this(program, type, GL43);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public BaseInterface(Program program, GlProgramInterfaceType type, InterfaceCore interfaceCore) {
        this.program = program;
        this.type = type;
        this.interfaceCore = (InterfaceCore<BaseInterface<?>>) interfaceCore;
        resourcesLazy = LazyList.lazyList(resources, index -> newResource(type, index));
    }

    public Program program() {
        return program;
    }

    public GlProgramInterfaceType interfaceType() {
        return type;
    }

    protected abstract T newResource(GlProgramInterfaceType type, int index);

    @Override
    public void invalidate() {
        for (var resource : resources) {
            if (resource instanceof Invalidatable invalidatable) {
                invalidatable.invalidate();
            }
        }
        resources.clear();
        activeResources = -1;
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

    public T getResource(String name) {
        return resourceMap.computeIfAbsent(name, key -> {
            int index = interfaceCore.getIndexByName(this, name);
            return index == GLX.GL_INVALID_INDEX ? null : resourcesLazy.get(index);
        });
    }

    public boolean containsResource(String name) {
        return getResource(name) != null;
    }

    public Map<String, T> getResourcesMap() {
        if (resourceMap.size() != getActiveResources()) {
            for (int i = 0; i < getActiveResources(); i++) {
                var resource = getResource(i);
                resourceMap.put(((ProgramResource.Named<?>) resource).getName(), resource);
            }
        }
        return Collections.unmodifiableMap(resourceMap);
    }

    public List<String> getResourceNames() {
        int n = getActiveResources();
        if (n == 0) {
            return Collections.emptyList();
        }
        var names = new ArrayList<String>(n);
        for (int i = 0; i < n; i++) {
            names.add(((ProgramResource.Named<?>) getResource(i)).getName());
        }
        return names;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <B> B bindResources(B bean) {
        BeanUtils.forEachSettersOfType(bean, (Class<T>) type.resourceType(), (name, setter) -> {
            var resource = getResource(name);
            if (resource != null) {
                setter.accept(resource);
            }
        });
        return bean;
    }

    @Override
    public int getActiveResources() {
        if (activeResources < 0) {
            activeResources = interfaceCore.getActiveResources(this);
        }
        return activeResources;
    }

    public int getMaxNumActiveVariables() {
        return interfaceCore.getMaxNumActiveVariables(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BaseInterface<?> that = (BaseInterface<?>) object;
        return Objects.equals(program, that.program) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(program, type);
    }
}
