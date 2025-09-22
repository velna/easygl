package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.graphics.program.Subroutine;

import java.util.List;

@Support(since = Version.GL43)
public interface ProgramResource<T extends ProgramResource<T>> {

    Program program();

    int index();

    T preLoad(PropertyKey... keys);

    enum PropertyKey implements IntEnum {
        Named("NAME_LENGTH"),
        Type("TYPE"),
        ArraySize("ARRAY_SIZE"),
        Offset("OFFSET"),
        BlockIndex("BLOCK_INDEX"),
        ArrayStride("ARRAY_STRIDE"),
        MatrixStride("MATRIX_STRIDE"),
        IsRowMajor("IS_ROW_MAJOR"),
        AtomicCounterBufferIndex("ATOMIC_COUNTER_BUFFER_INDEX"),
        TextureBuffer("TEXTURE_BUFFER"),
        BufferBinding("BUFFER_BINDING", false),
        BufferDataSize("BUFFER_DATA_SIZE"),
        NumActiveVariables("NUM_ACTIVE_VARIABLES"),
        ActiveVariables("ACTIVE_VARIABLES"),//array/GL_NUM_ACTIVE_VARIABLES
        ReferencedByVertexShader("REFERENCED_BY_VERTEX_SHADER"),
        ReferencedByTessControlShader("REFERENCED_BY_TESS_CONTROL_SHADER"),
        ReferencedByTessEvaluationShader("REFERENCED_BY_TESS_EVALUATION_SHADER"),
        ReferencedByGeometryShader("REFERENCED_BY_GEOMETRY_SHADER"),
        ReferencedByFragmentShader("REFERENCED_BY_FRAGMENT_SHADER"),
        ReferencedByComputeShader("REFERENCED_BY_COMPUTE_SHADER"),
        NumCompatibleSubroutines("NUM_COMPATIBLE_SUBROUTINES"),
        CompatibleSubroutines("COMPATIBLE_SUBROUTINES"),
        TopLevelArraySize("TOP_LEVEL_ARRAY_SIZE"),
        TopLevelArrayStride("TOP_LEVEL_ARRAY_STRIDE"),
        Location("LOCATION"),
        LocationIndex("LOCATION_INDEX"),
        IsPerPatch("IS_PER_PATCH"),
        LocationComponent("LOCATION_COMPONENT"),
        TransformFeedbackBufferIndex("TRANSFORM_FEEDBACK_BUFFER_INDEX"),
        TransformFeedbackBufferStride("TRANSFORM_FEEDBACK_BUFFER_STRIDE");
        private final int value;
        private final int mask;
        private final boolean cacheable;

        PropertyKey(String id) {
            this(id, true);
        }

        PropertyKey(String id, boolean cacheable) {
            this.value = MetaSystem.Graphics.queryInt(id);
            this.mask = 1 << ordinal();
            this.cacheable = cacheable;
        }

        public boolean isCacheable() {
            return cacheable;
        }

        @Override
        public int value() {
            return value;
        }

        public int mask() {
            return mask;
        }
    }


    //region Resource functions
    interface Named<T extends ProgramResource<T>> extends ProgramResource<T> {
        String getName();
    }

    interface Type<T extends ProgramResource<T>> extends ProgramResource<T> {
        DataType getType();
    }

    interface ArraySize<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getArraySize();
    }

    interface Offset<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getOffset();
    }

    interface BlockIndex<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getBlockIndex();
    }

    interface ArrayStride<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getArrayStride();
    }

    interface MatrixStride<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getMatrixStride();
    }

    interface IsRowMajor<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isRowMajor();
    }

    interface AtomicCounterBufferIndex<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getAtomicCounterBufferIndex();
    }

    interface TextureBuffer<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getTextureBuffer();
    }

    interface BufferBinding<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getBufferBinding();
    }

    interface BufferDataSize<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getBufferDataSize();
    }

    interface NumActiveVariables<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getNumActiveVariables();
    }

    interface ActiveVariables<T extends ProgramResource<T>> extends ProgramResource<T> {
        int[] getActiveVariables();
    }

    interface ReferencedByVertexShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByVertexShader();
    }

    interface ReferencedByTessControlShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByTessControlShader();
    }

    interface ReferencedByTessEvaluationShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByTessEvaluationShader();
    }

    interface ReferencedByGeometryShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByGeometryShader();
    }

    interface ReferencedByFragmentShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByFragmentShader();
    }

    interface ReferencedByComputeShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByComputeShader();
    }

    interface NumCompatibleSubroutines<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getNumCompatibleSubroutines();
    }

    interface CompatibleSubroutines<T extends ProgramResource<T>> extends ProgramResource<T> {
        List<Subroutine> getCompatibleSubroutines();
    }

    interface TopLevelArraySize<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isTopLevelArraySize();
    }

    interface TopLevelArrayStride<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getTopLevelArrayStride();
    }

    interface Location<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getLocation();
    }

    interface LocationIndex<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getLocationIndex();
    }

    interface IsPerPatch<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isPerPatch();
    }

    interface LocationComponent<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getLocationComponent();
    }

    interface TransformFeedbackBufferIndex<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getTransformFeedbackBufferIndex();
    }

    interface TransformFeedbackBufferStride<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getTransformFeedbackBufferStride();
    }
    //endregion

}
