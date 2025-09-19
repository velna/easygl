package com.vanix.easygl.opengl.program;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.*;
import com.vanix.easygl.opengl.Cache;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

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
        ProgramResource.NumCompatibleSubroutines<T>,
        ProgramResource.CompatibleSubroutines<T>,
        ProgramResource.TopLevelArraySize<T>,
        ProgramResource.TopLevelArrayStride<T>,
        ProgramResource.Location<T>,
        ProgramResource.LocationIndex<T>,
        ProgramResource.IsPerPatch<T>,
        ProgramResource.LocationComponent<T>,
        ProgramResource.TransformFeedbackBufferIndex<T>,
        ProgramResource.TransformFeedbackBufferStride<T> {
    protected final Program program;
    protected final GlProgramInterfaceType interfaceType;
    protected final int index;
    private String name;
    private BitSet<PropertyKey> properties;
    private int[] preloadValues;

    public BaseResource(Program program, GlProgramInterfaceType interfaceType, int index) {
        this.program = program;
        this.interfaceType = interfaceType;
        this.index = index;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T preLoad(PropertyKey... keys) {
        if (preloadValues == null) {
            preloadValues = new int[ProgramResource.PropertyKey.values().length];
        }
        if (this.properties == null) {
            this.properties = BitSet.of((ToIntFunction<PropertyKey>) ProgramResource.PropertyKey::mask);
        } else {
            this.properties.clear();
        }
        List<PropertyKey> propertyKeys = new ArrayList<>(keys.length);
        for (var key : keys) {
            if (interfaceType.properties().contains(key)) {
                properties.add(key);
                propertyKeys.add(key);
            }
        }
        int[][] data = new int[2][propertyKeys.size()];
        for (int i = 0; i < propertyKeys.size(); i++) {
            data[0][i] = propertyKeys.get(i).value();
        }
        GLX.glGetProgramResourceiv(program.intHandle(), interfaceType.value(), index, data[0], null, data[1]);
        for (int i = 0; i < data[1].length; i++) {
            preloadValues[propertyKeys.get(i).ordinal()] = data[1][i];
        }
        return (T) this;
    }

    private int queryInt(PropertyKey key) {
        if (preloadValues != null && properties.contains(key)) {
            return preloadValues[key.ordinal()];
        } else {
            int[][] data = new int[2][1];
            data[0][0] = key.value();
            GLX.glGetProgramResourceiv(program.intHandle(), interfaceType.value(), index, data[0], null, data[1]);
            return data[1][0];
        }
    }

    private boolean queryBoolean(PropertyKey key) {
        if (preloadValues != null && properties.contains(key)) {
            return preloadValues[key.ordinal()] != 0;
        } else {
            int[][] data = new int[2][1];
            data[0][0] = key.value();
            GLX.glGetProgramResourceiv(program.intHandle(), interfaceType.value(), index, data[0], null, data[1]);
            return data[1][0] == GLX.GL_TRUE;
        }
    }

    @Override
    public Program program() {
        return program;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public String getName() {
        if (name == null) {
            name = GLX.glGetProgramResourceName(program.intHandle(), interfaceType.value(), index);
        }
        return name;
    }

    @Override
    public int[] getActiveVariables() {
        int activeResources = queryInt(ProgramResource.PropertyKey.ActiveVariables);
        int[] props = new int[]{ProgramResource.PropertyKey.ActiveVariables.value()};
        int[] data = new int[activeResources];
        GLX.glGetProgramResourceiv(program.intHandle(), interfaceType.value(), index, props, null, data);
        return data;
    }

    @Override
    public int getTextureBuffer() {
        return 0;
    }

    @Override
    public DataType getType() {
        return Cache.DataType.get(queryInt(ProgramResource.PropertyKey.Type));
    }

    @Override
    public int getArraySize() {
        return queryInt(ProgramResource.PropertyKey.ArraySize);
    }

    @Override
    public int getOffset() {
        return queryInt(ProgramResource.PropertyKey.Offset);
    }

    @Override
    public int getBlockIndex() {
        return queryInt(ProgramResource.PropertyKey.BlockIndex);
    }

    @Override
    public int getArrayStride() {
        return queryInt(ProgramResource.PropertyKey.ArrayStride);
    }

    @Override
    public int getMatrixStride() {
        return queryInt(ProgramResource.PropertyKey.MatrixStride);
    }

    @Override
    public boolean isRowMajor() {
        return queryBoolean(ProgramResource.PropertyKey.IsRowMajor);
    }

    @Override
    public int getAtomicCounterBufferIndex() {
        return queryInt(ProgramResource.PropertyKey.AtomicCounterBufferIndex);
    }

    @Override
    public int getBufferBinding() {
        return queryInt(ProgramResource.PropertyKey.BufferBinding);
    }

    @Override
    public int getBufferDataSize() {
        return queryInt(ProgramResource.PropertyKey.BufferDataSize);
    }

    @Override
    public int getNumActiveVariables() {
        return queryInt(ProgramResource.PropertyKey.NumActiveVariables);
    }

    @Override
    public boolean isReferencedByVertexShader() {
        return queryBoolean(ProgramResource.PropertyKey.ReferencedByVertexShader);
    }

    @Override
    public boolean isReferencedByTessControlShader() {
        return queryBoolean(ProgramResource.PropertyKey.ReferencedByTessControlShader);
    }

    @Override
    public boolean isReferencedByTessEvaluationShader() {
        return queryBoolean(ProgramResource.PropertyKey.ReferencedByTessEvaluationShader);
    }

    @Override
    public boolean isReferencedByGeometryShader() {
        return queryBoolean(ProgramResource.PropertyKey.ReferencedByGeometryShader);
    }

    @Override
    public boolean isReferencedByFragmentShader() {
        return queryBoolean(ProgramResource.PropertyKey.ReferencedByFragmentShader);
    }

    @Override
    public boolean isReferencedByComputeShader() {
        return queryBoolean(ProgramResource.PropertyKey.ReferencedByComputeShader);
    }

    @Override
    public int getNumCompatibleSubroutines() {
        return queryInt(ProgramResource.PropertyKey.NumCompatibleSubroutines);
    }

    @Override
    public int getCompatibleSubroutines() {
        return queryInt(ProgramResource.PropertyKey.CompatibleSubroutines);
    }

    @Override
    public boolean isTopLevelArraySize() {
        return queryBoolean(ProgramResource.PropertyKey.TopLevelArraySize);
    }

    @Override
    public int getTopLevelArrayStride() {
        return queryInt(ProgramResource.PropertyKey.TopLevelArrayStride);
    }

    @Override
    public int getLocation() {
        return queryInt(ProgramResource.PropertyKey.Location);
    }

    @Override
    public int getLocationIndex() {
        return queryInt(ProgramResource.PropertyKey.LocationIndex);
    }

    @Override
    public boolean isPerPatch() {
        return queryBoolean(ProgramResource.PropertyKey.IsPerPatch);
    }

    @Override
    public int getLocationComponent() {
        return queryInt(ProgramResource.PropertyKey.LocationComponent);
    }

    @Override
    public int getTransformFeedbackBufferIndex() {
        return queryInt(ProgramResource.PropertyKey.TransformFeedbackBufferIndex);
    }

    @Override
    public int getTransformFeedbackBufferStride() {
        return queryInt(ProgramResource.PropertyKey.TransformFeedbackBufferStride);
    }

}
