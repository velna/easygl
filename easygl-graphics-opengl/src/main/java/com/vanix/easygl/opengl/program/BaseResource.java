package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.Program;
import com.vanix.easygl.graphics.ProgramResource;
import com.vanix.easygl.opengl.Cache;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlProgramInterfaceType;
import org.lwjgl.system.MemoryStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BaseResource<T extends ProgramResource<T>> implements
        ProgramResource.Named<T>,
        ProgramResource.Type<T>,
        ProgramResource.ArraySize<T>,
        ProgramResource.Offset<T>,
        ProgramResource.BlockIndex<T>,
        ProgramResource.ArrayStride<T>,
        ProgramResource.MatrixStride<T>,
        ProgramResource.IsRowMajor<T>,
        ProgramResource.AtomicCounterBufferIndex<T>,
        ProgramResource.TextureBuffer<T>,
        ProgramResource.BufferBinding<T>,
        ProgramResource.BufferDataSize<T>,
        ProgramResource.NumActiveVariables<T>,
        ProgramResource.ActiveVariables<T>,
        ProgramResource.ReferencedByVertexShader<T>,
        ProgramResource.ReferencedByTessControlShader<T>,
        ProgramResource.ReferencedByGeometryShader<T>,
        ProgramResource.ReferencedByFragmentShader<T>,
        ProgramResource.ReferencedByComputeShader<T>,
        ProgramResource.ReferencedByTessEvaluationShader<T>,
        ProgramResource.TopLevelArraySize<T>,
        ProgramResource.TopLevelArrayStride<T>,
        ProgramResource.Location<T>,
        ProgramResource.LocationIndex<T>,
        ProgramResource.IsPerPatch<T>,
        ProgramResource.LocationComponent<T>,
        ProgramResource.TransformFeedbackBufferIndex<T>,
        ProgramResource.TransformFeedbackBufferStride<T> {
    protected static final ResourceCore<BaseResource<?>> GL43 = new ResourceCore<>() {};
    protected final Program program;
    protected final GlProgramInterfaceType interfaceType;
    protected final int index;
    private final ResourceCore<BaseResource<?>> resourceCore;
    private String name;
    private int[] activeVariables;
    private DataType dataType;
    private Integer[] valuesCache;

    public BaseResource(Program program, GlProgramInterfaceType interfaceType, int index) {
        this(program, interfaceType, index, GL43);
    }

    public BaseResource(Program program, GlProgramInterfaceType interfaceType, int index, ResourceCore<BaseResource<?>> resourceCore) {
        this.program = program;
        this.interfaceType = interfaceType;
        this.index = index;
        this.resourceCore = resourceCore;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T preLoad(PropertyKey... keys) {
        List<PropertyKey> propertyKeys = new ArrayList<>(keys.length);
        for (var key : keys) {
            if (interfaceType.properties().contains(key)) {
                propertyKeys.add(key);
            }
        }
        try (var stack = MemoryStack.stackPush()) {
            var values = resourceCore.doPreLoad(this, propertyKeys, stack);
            for (int i = 0; i < values.limit(); i++) {
                setCacheValue(propertyKeys.get(i), values.get(i));
            }
            return (T) this;
        }
    }

    private int setCacheValue(PropertyKey key, int value) {
        if (key.isCacheable()) {
            if (valuesCache == null) {
                valuesCache = new Integer[PropertyKey.values().length];
            }
            valuesCache[key.ordinal()] = value;
        }
        return value;
    }

    private Integer getCacheValue(PropertyKey key) {
        return valuesCache == null ? null : valuesCache[key.ordinal()];
    }

    protected boolean queryBoolean(PropertyKey key) {
        return queryInt(key) == GLX.GL_TRUE;
    }

    protected int queryInt(PropertyKey key) {
        Integer value = getCacheValue(key);
        return value == null ? setCacheValue(key, resourceCore.doGet(this, key)) : value;
    }

    @Override
    public Program program() {
        return program;
    }

    @Override
    public String getName() {
        if (name == null) {
            name = resourceCore.doGetName(this);
        }
        return name;
    }

    @Override
    public int[] getActiveVariables() {
        if (activeVariables == null) {
            activeVariables = resourceCore.doGetActiveVariables(this);
        }
        return activeVariables;
    }

    @Override
    public DataType getType() {
        if (dataType == null) {
            dataType = Cache.DataType.get(queryInt(PropertyKey.Type));
        }
        return dataType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BaseResource<?> that = (BaseResource<?>) object;
        return index == that.index && Objects.equals(program, that.program) && interfaceType == that.interfaceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(program, interfaceType, index);
    }

    @Override
    public int getArraySize() {
        return queryInt(PropertyKey.ArraySize);
    }

    @Override
    public int getArrayStride() {
        return queryInt(PropertyKey.ArrayStride);
    }

    @Override
    public int getAtomicCounterBufferIndex() {
        return queryInt(PropertyKey.AtomicCounterBufferIndex);
    }

    @Override
    public int getBlockIndex() {
        return queryInt(PropertyKey.BlockIndex);
    }

    @Override
    public int getBufferBinding() {
        return queryInt(PropertyKey.BufferBinding);
    }

    @Override
    public int getBufferDataSize() {
        return queryInt(PropertyKey.BufferDataSize);
    }

    @Override
    public int getLocation() {
        return queryInt(PropertyKey.Location);
    }

    @Override
    public int getLocationComponent() {
        return queryInt(PropertyKey.LocationComponent);
    }

    @Override
    public int getLocationIndex() {
        return queryInt(PropertyKey.LocationIndex);
    }

    @Override
    public int getMatrixStride() {
        return queryInt(PropertyKey.MatrixStride);
    }

    @Override
    public int getNumActiveVariables() {
        return queryInt(PropertyKey.NumActiveVariables);
    }

    @Override
    public int getOffset() {
        return queryInt(PropertyKey.Offset);
    }

    @Override
    public int getTextureBuffer() {
        return 0;
    }

    @Override
    public int getTopLevelArrayStride() {
        return queryInt(PropertyKey.TopLevelArrayStride);
    }

    @Override
    public int getTransformFeedbackBufferIndex() {
        return queryInt(PropertyKey.TransformFeedbackBufferIndex);
    }

    @Override
    public int getTransformFeedbackBufferStride() {
        return queryInt(PropertyKey.TransformFeedbackBufferStride);
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public boolean isPerPatch() {
        return queryBoolean(PropertyKey.IsPerPatch);
    }

    @Override
    public boolean isReferencedByComputeShader() {
        return queryBoolean(PropertyKey.ReferencedByComputeShader);
    }

    @Override
    public boolean isReferencedByFragmentShader() {
        return queryBoolean(PropertyKey.ReferencedByFragmentShader);
    }

    @Override
    public boolean isReferencedByGeometryShader() {
        return queryBoolean(PropertyKey.ReferencedByGeometryShader);
    }

    @Override
    public boolean isReferencedByTessControlShader() {
        return queryBoolean(PropertyKey.ReferencedByTessControlShader);
    }

    @Override
    public boolean isReferencedByTessEvaluationShader() {
        return queryBoolean(PropertyKey.ReferencedByTessEvaluationShader);
    }

    @Override
    public boolean isReferencedByVertexShader() {
        return queryBoolean(PropertyKey.ReferencedByVertexShader);
    }

    @Override
    public boolean isRowMajor() {
        return queryBoolean(PropertyKey.IsRowMajor);
    }

    @Override
    public boolean isTopLevelArraySize() {
        return queryBoolean(PropertyKey.TopLevelArraySize);
    }

}
