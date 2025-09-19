package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.Buffer;
import com.vanix.easygl.graphics.DrawMode;
import com.vanix.easygl.graphics.VertexArray;
import com.vanix.easygl.graphics.draw.*;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.collections.api.factory.primitive.LongObjectMaps;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;

import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;

public class GlVertexArrayDrawing implements
        ArraysDrawing,
        ArraysDrawing.Instanced,
        ArraysDrawing.BaseInstanced,
        ArraysDrawing.MultiStrideIndirect,
        ArraysDrawing.MultiFirsts,
        ArraysDrawing.Builder<GlVertexArrayDrawing>,
        ElementsDrawing,
        ElementsDrawing.Instanced,
        ElementsDrawing.BaseInstanced,
        ElementsDrawing.Indirect,
        ElementsDrawing.MultiStrideIndirect,
        ElementsDrawing.MultiCounts,
        ElementsDrawing.Range,
        ElementsDrawing.RangeBaseVertex,
        ElementsDrawing.BaseVertex,
        ElementsDrawing.InstancedBaseVertex,
        ElementsDrawing.BaseInstancedBaseVertex,
        ElementsDrawing.MultiBaseVertex,
        ElementsDrawing.Builder<GlVertexArrayDrawing>,
        InstancedDrawing.Builder<VertexArray, GlVertexArrayDrawing, GlVertexArrayDrawing>,
        IndirectDrawing.Builder<VertexArray, GlVertexArrayDrawing, GlVertexArrayDrawing>,
        BaseVertexDrawing.Builder<VertexArray, GlVertexArrayDrawing, GlVertexArrayDrawing, GlVertexArrayDrawing, GlVertexArrayDrawing> {

    private static final int F_ARRAYS = 1;
    private static final int F_ELEMENTS = 1 << 1;
    private static final int F_INSTANCED = 1 << 2;
    private static final int F_BASE_INSTANCE = 1 << 3;
    private static final int F_INDIRECT = 1 << 4;
    private static final int F_MULTI_COUNTS = 1 << 5;
    private static final int F_MULTI_STRIDE = 1 << 6;
    private static final int F_MULTI_FIRSTS_BASE_VERTEX = 1 << 7;
    private static final int F_BASE_VERTEX = 1 << 8;
    private static final int F_RANGE = 1 << 9;
    private static final int T_NULL = 0;
    private static final int T_INT_ARRAY = 1;
    private static final int T_INT_BUFFER = 2;
    private static final int T_INT_BUFFER_ARRAY = 3;
    private static final int T_BYTE_BUFFER = 4;
    private static final int T_BUFFER = 5;
    private static final int T_START = T_NULL;
    private static final int T_END = T_BUFFER;
    private static final int SHIFT_INDICES = 32;
    private static final int SHIFT_COUNTS = 40;
    private static final int SHIFT_FIRSTS_OR_BASE_VERTEXES = 48;

    private static final MutableLongObjectMap<Consumer<GlVertexArrayDrawing>> MAPPING = LongObjectMaps.mutable.of();

    static {
        MAPPING.put(arraysMask(0, 0, 0, F_ARRAYS), d ->
                GLX.glDrawArrays(d.mode.value(), d.first, d.count));
        MAPPING.put(arraysMask(0, 0, 0, F_ARRAYS, F_INSTANCED), d ->
                GLX.glDrawArraysInstanced(d.mode.value(), d.first, d.count, d.instanceCount));
        MAPPING.put(arraysMask(0, 0, 0, F_ARRAYS, F_INSTANCED, F_BASE_INSTANCE), d ->
                GLX.glDrawArraysInstancedBaseInstance(d.mode.value(), d.first, d.count, d.instanceCount, d.baseInstance));
        forEachType(type -> arraysMask(type, 0, 0, F_ARRAYS, F_INDIRECT), type ->
                switch (type) {
                    case T_INT_ARRAY -> d -> GLX.glDrawArraysIndirect(d.mode.value(), (int[]) d.indices);
                    case T_INT_BUFFER -> d -> GLX.glDrawArraysIndirect(d.mode.value(), (IntBuffer) d.indices);
                    case T_BYTE_BUFFER -> d -> GLX.glDrawArraysIndirect(d.mode.value(), (ByteBuffer) d.indices);
                    default -> null;
                });
        forEachType(type -> arraysMask(type, 0, 0, F_ARRAYS, F_INDIRECT), type ->
                switch (type) {
                    case T_INT_ARRAY -> d ->
                            GLX.glDrawArraysIndirect(d.mode.value(), (int[]) d.indices);
                    case T_INT_BUFFER -> d ->
                            GLX.glDrawArraysIndirect(d.mode.value(), (IntBuffer) d.indices);
                    case T_BYTE_BUFFER -> d ->
                            GLX.glDrawArraysIndirect(d.mode.value(), (ByteBuffer) d.indices);
                    default -> null;
                });
        forEachType(type -> arraysMask(0, type, type, F_MULTI_FIRSTS_BASE_VERTEX, F_ARRAYS), type ->
                switch (type) {
                    case T_INT_ARRAY ->
                            d -> GLX.glMultiDrawArrays(d.mode.value(), (int[]) d.firstsOrBaseVertexes, (int[]) d.counts);
                    case T_INT_BUFFER ->
                            d -> GLX.glMultiDrawArrays(d.mode.value(), (IntBuffer) d.firstsOrBaseVertexes, (IntBuffer) d.counts);
                    default -> null;
                });
        forEachType(type -> arraysMask(type, 0, 0, F_MULTI_STRIDE, F_ARRAYS, F_INDIRECT), type ->
                switch (type) {
                    case T_INT_ARRAY ->
                            d -> GLX.glMultiDrawArraysIndirect(d.mode.value(), (int[]) d.indices, d.drawCount, d.stride);
                    case T_INT_BUFFER ->
                            d -> GLX.glMultiDrawArraysIndirect(d.mode.value(), (IntBuffer) d.indices, d.drawCount, d.stride);
                    case T_BYTE_BUFFER ->
                            d -> GLX.glMultiDrawArraysIndirect(d.mode.value(), (ByteBuffer) d.indices, d.drawCount, d.stride);
                    default -> null;
                });
        forEachType(type -> elementsMask(type, 0, 0, F_ELEMENTS), type ->
                switch (type) {
                    case T_BUFFER ->
                            d -> GLX.glDrawElements(d.mode.value(), d.count, d.dataType.value(), MemoryUtil.NULL);
                    case T_INT_BUFFER -> d -> GLX.glDrawElements(d.mode.value(), (IntBuffer) d.indices);
                    case T_BYTE_BUFFER ->
                            d -> GLX.glDrawElements(d.mode.value(), d.dataType.value(), (ByteBuffer) d.indices);
                    default -> null;
                });
        forEachType(type -> elementsMask(type, 0, 0, F_ELEMENTS, F_INSTANCED), type ->
                switch (type) {
                    case T_BUFFER ->
                            d -> GLX.glDrawElementsInstanced(d.mode.value(), d.count, d.dataType.value(), MemoryUtil.NULL, d.instanceCount);
                    case T_INT_BUFFER ->
                            d -> GLX.glDrawElementsInstanced(d.mode.value(), (IntBuffer) d.indices, d.instanceCount);
                    case T_BYTE_BUFFER ->
                            d -> GLX.glDrawElementsInstanced(d.mode.value(), d.dataType.value(), (ByteBuffer) d.indices, d.instanceCount);
                    default -> null;
                });
        forEachType(type -> elementsMask(type, 0, 0, F_ELEMENTS, F_INSTANCED, F_BASE_INSTANCE), type ->
                switch (type) {
                    case T_BUFFER ->
                            d -> GLX.glDrawElementsInstancedBaseInstance(d.mode.value(), d.count, d.dataType.value(), MemoryUtil.NULL, d.instanceCount, d.baseInstance);
                    case T_INT_BUFFER ->
                            d -> GLX.glDrawElementsInstancedBaseInstance(d.mode.value(), (IntBuffer) d.indices, d.instanceCount, d.baseInstance);
                    case T_BYTE_BUFFER ->
                            d -> GLX.glDrawElementsInstancedBaseInstance(d.mode.value(), d.dataType.value(), (ByteBuffer) d.indices, d.instanceCount, d.baseInstance);
                    default -> null;
                });
        forEachType(type -> elementsMask(T_INT_BUFFER_ARRAY, type, 0, F_MULTI_COUNTS, F_ELEMENTS), type ->
                switch (type) {
                    case T_INT_ARRAY ->
                            d -> GLX.glMultiDrawElements(d.mode.value(), (int[]) d.counts, d.dataType.value(), (PointerBuffer) d.indices);
                    case T_INT_BUFFER ->
                            d -> GLX.glMultiDrawElements(d.mode.value(), (IntBuffer) d.counts, d.dataType.value(), (PointerBuffer) d.indices);
                    default -> null;
                });
        forEachType(type -> elementsMask(type, 0, 0, F_RANGE, F_ELEMENTS), type ->
                switch (type) {
                    case T_BUFFER ->
                            d -> GLX.glDrawRangeElements(d.mode.value(), d.min, d.max, d.count, d.dataType.value(), MemoryUtil.NULL);
                    case T_INT_BUFFER ->
                            d -> GLX.glDrawRangeElements(d.mode.value(), d.min, d.max, (IntBuffer) d.indices);
                    case T_BYTE_BUFFER ->
                            d -> GLX.glDrawRangeElements(d.mode.value(), d.min, d.max, d.dataType.value(), (ByteBuffer) d.indices);
                    default -> null;
                });
        forEachType(type -> elementsMask(type, 0, 0, F_ELEMENTS, F_BASE_VERTEX), type ->
                switch (type) {
                    case T_BUFFER ->
                            d -> GLX.glDrawElementsBaseVertex(d.mode.value(), d.count, d.dataType.value(), MemoryUtil.NULL, d.baseVertex);
                    case T_INT_BUFFER ->
                            d -> GLX.glDrawElementsBaseVertex(d.mode.value(), (IntBuffer) d.indices, d.baseVertex);
                    case T_BYTE_BUFFER ->
                            d -> GLX.glDrawElementsBaseVertex(d.mode.value(), d.dataType.value(), (ByteBuffer) d.indices, d.baseVertex);
                    default -> null;
                });
        forEachType(type -> elementsMask(type, 0, 0, F_RANGE, F_ELEMENTS, F_BASE_VERTEX), type ->
                switch (type) {
                    case T_BUFFER ->
                            d -> GLX.glDrawRangeElementsBaseVertex(d.mode.value(), d.min, d.max, d.count, d.dataType.value(), MemoryUtil.NULL, d.baseVertex);
                    case T_INT_BUFFER ->
                            d -> GLX.glDrawRangeElementsBaseVertex(d.mode.value(), d.min, d.max, (IntBuffer) d.indices, d.baseVertex);
                    case T_BYTE_BUFFER ->
                            d -> GLX.glDrawRangeElementsBaseVertex(d.mode.value(), d.min, d.max, d.dataType.value(), (ByteBuffer) d.indices, d.baseVertex);
                    default -> null;
                });
        forEachType(type -> elementsMask(type, 0, 0, F_ELEMENTS, F_INSTANCED, F_BASE_VERTEX), type ->
                switch (type) {
                    case T_BUFFER ->
                            d -> GLX.glDrawElementsInstancedBaseVertex(d.mode.value(), d.count, d.dataType.value(), MemoryUtil.NULL, d.instanceCount, d.baseVertex);
                    case T_INT_BUFFER ->
                            d -> GLX.glDrawElementsInstancedBaseVertex(d.mode.value(), (IntBuffer) d.indices, d.instanceCount, d.baseVertex);
                    case T_BYTE_BUFFER ->
                            d -> GLX.glDrawElementsInstancedBaseVertex(d.mode.value(), d.dataType.value(), (ByteBuffer) d.indices, d.instanceCount, d.baseVertex);
                    default -> null;
                });
        forEachType(type -> elementsMask(type, 0, 0, F_ELEMENTS, F_INSTANCED, F_BASE_VERTEX, F_BASE_INSTANCE), type ->
                switch (type) {
                    case T_BUFFER ->
                            d -> GLX.glDrawElementsInstancedBaseVertexBaseInstance(d.mode.value(), d.count, d.dataType.value(), MemoryUtil.NULL, d.instanceCount, d.baseVertex, d.baseInstance);
                    case T_INT_BUFFER ->
                            d -> GLX.glDrawElementsInstancedBaseVertexBaseInstance(d.mode.value(), (IntBuffer) d.indices, d.instanceCount, d.baseVertex, d.baseInstance);
                    case T_BYTE_BUFFER ->
                            d -> GLX.glDrawElementsInstancedBaseVertexBaseInstance(d.mode.value(), d.dataType.value(), (ByteBuffer) d.indices, d.instanceCount, d.baseVertex, d.baseInstance);
                    default -> null;
                });
        forEachType(type -> elementsMask(type, 0, 0, F_ELEMENTS, F_INDIRECT), type ->
                switch (type) {
                    case T_BUFFER ->
                            d -> GLX.glDrawElementsIndirect(d.mode.value(), d.dataType.value(), MemoryUtil.NULL);
                    case T_INT_ARRAY ->
                            d -> GLX.glDrawElementsIndirect(d.mode.value(), d.dataType.value(), (int[]) d.indices);
                    case T_INT_BUFFER ->
                            d -> GLX.glDrawElementsIndirect(d.mode.value(), d.dataType.value(), (IntBuffer) d.indices);
                    case T_BYTE_BUFFER ->
                            d -> GLX.glDrawElementsIndirect(d.mode.value(), d.dataType.value(), (ByteBuffer) d.indices);
                    default -> null;
                });
        forEachType(type -> elementsMask(type, 0, 0, F_MULTI_STRIDE, F_ELEMENTS, F_INDIRECT), type ->
                switch (type) {
                    case T_BUFFER ->
                            d -> GLX.glMultiDrawElementsIndirect(d.mode.value(), d.dataType.value(), MemoryUtil.NULL, d.drawCount, d.stride);
                    case T_INT_ARRAY ->
                            d -> GLX.glMultiDrawElementsIndirect(d.mode.value(), d.dataType.value(), (int[]) d.indices, d.drawCount, d.stride);
                    case T_INT_BUFFER ->
                            d -> GLX.glMultiDrawElementsIndirect(d.mode.value(), d.dataType.value(), (IntBuffer) d.indices, d.drawCount, d.stride);
                    case T_BYTE_BUFFER ->
                            d -> GLX.glMultiDrawElementsIndirect(d.mode.value(), d.dataType.value(), (ByteBuffer) d.indices, d.drawCount, d.stride);
                    default -> null;
                });
        forEachType(type -> elementsMask(type, 0, type, F_MULTI_FIRSTS_BASE_VERTEX, F_ELEMENTS), type ->
                switch (type) {
                    case T_INT_ARRAY ->
                            d -> GLX.glMultiDrawElementsBaseVertex(d.mode.value(), (int[]) d.counts, d.dataType.value(), (PointerBuffer) d.indices, (int[]) d.firstsOrBaseVertexes);
                    case T_INT_BUFFER ->
                            d -> GLX.glMultiDrawElementsBaseVertex(d.mode.value(), (IntBuffer) d.counts, d.dataType.value(), (PointerBuffer) d.indices, (IntBuffer) d.firstsOrBaseVertexes);
                    default -> null;
                });
    }

    private final VertexArray vertexArray;
    private final DrawMode mode;
    private final DataType dataType;
    private final int first;
    private final int count;
    private Object firstsOrBaseVertexes;
    private Object indices;
    private int min;
    private int max;
    private int instanceCount;
    private int baseInstance;
    private int baseVertex;
    private Object counts;
    private int drawCount;
    private int stride;
    private long mask;
    private Consumer<GlVertexArrayDrawing> drawFunction;

    public GlVertexArrayDrawing(VertexArray vertexArray, DrawMode mode) {
        this(vertexArray, mode, -1, -1);
    }

    public GlVertexArrayDrawing(VertexArray vertexArray, DrawMode mode, int first, int count) {
        this.vertexArray = vertexArray;
        this.mode = mode;
        this.dataType = null;
        this.indices = null;
        this.first = first;
        this.count = count;
        mask = F_ARRAYS;
    }

    public GlVertexArrayDrawing(VertexArray vertexArray, DrawMode mode, DataType dataType, int[] indices) {
        this(vertexArray, mode, dataType, indices, indices == null ? 0 : indices.length, T_INT_ARRAY);
    }

    public GlVertexArrayDrawing(VertexArray vertexArray, DrawMode mode, DataType dataType, IntBuffer indices) {
        this(vertexArray, mode, dataType, indices, indices == null ? 0 : indices.remaining(), T_INT_BUFFER);
    }

    public GlVertexArrayDrawing(VertexArray vertexArray, DrawMode mode, DataType dataType, ByteBuffer indices) {
        this(vertexArray, mode, dataType, indices, indices == null ? 0 : indices.remaining(), T_BYTE_BUFFER);
    }

    public GlVertexArrayDrawing(VertexArray vertexArray, DrawMode mode, DataType dataType, IntBuffer[] indices) {
        this(vertexArray, mode, dataType, indices, indices == null ? 0 : indices.length, T_INT_BUFFER_ARRAY);
    }

    public GlVertexArrayDrawing(VertexArray vertexArray, DrawMode mode, Buffer ebo) {
        this(vertexArray, mode, ebo.dataType(), ebo, ebo.count(), T_BUFFER);
    }

    private GlVertexArrayDrawing(VertexArray vertexArray, DrawMode mode, DataType dataType, Object indices, int count, int indicesType) {
        this.vertexArray = vertexArray;
        this.mode = mode;
        this.dataType = dataType;
        this.first = -1;
        this.count = count;
        mask = F_ELEMENTS | indicesType(indices == null ? T_NULL : indicesType);
        if (indices instanceof IntBuffer[] buffers) {
            var pointerBuffer = toPointerBuffer(buffers);
            Cache.CLEANER.register(this, () -> MemoryUtil.memFree(pointerBuffer));
            this.indices = pointerBuffer;
        } else {
            this.indices = indices;
        }
    }

    @Override
    public GlVertexArrayDrawing build() {
        drawFunction = MAPPING.get(mask);
        if (drawFunction == null) {
            String function = "Draw";
            List<String> params = new ArrayList<>();
            if ((mask & F_ARRAYS) > 0) {
                function += "Arrays";
            }
            if ((mask & F_ELEMENTS) > 0) {
                function += "Elements";
            }
            if ((mask & F_INDIRECT) > 0) {
                function += "Indirect";
                params.add("indices:" + typeString(SHIFT_INDICES));
            }
            if ((mask & F_INSTANCED) > 0) {
                function += "Instanced";
            }
            if ((mask & F_BASE_VERTEX) > 0) {
                function += "BaseVertex";
            }
            if ((mask & F_BASE_INSTANCE) > 0) {
                function += "BaseInstance";
            }
            if ((mask & F_RANGE) > 0) {
                function = "Range" + function;
            }
            if ((mask & F_MULTI_COUNTS) > 0) {
                function = "Multi" + function + "_Counts";
                params.add("indices:" + typeString(SHIFT_INDICES));
                params.add("counts:" + typeString(SHIFT_COUNTS));
            }
            if ((mask & F_MULTI_STRIDE) > 0) {
                function = "Multi" + function + "_Stride";
                params.add("indices:" + typeString(SHIFT_INDICES));
            }
            if ((mask & (F_MULTI_FIRSTS_BASE_VERTEX | F_ARRAYS)) > 0) {
                function = "Multi" + function + "_Firsts";
                params.add("firsts:" + typeString(SHIFT_FIRSTS_OR_BASE_VERTEXES));
                params.add("indices:" + typeString(SHIFT_INDICES));
            }
            if ((mask & (F_MULTI_FIRSTS_BASE_VERTEX | F_ELEMENTS)) > 0) {
                function = "Multi" + function + "BaseVertex";
                params.add("indices:" + typeString(SHIFT_INDICES));
                params.add("baseVertexes:" + typeString(SHIFT_FIRSTS_OR_BASE_VERTEXES));
            }
            function += "(" + StringUtils.join(params, ", ") + ")";

            throw new UnsupportedOperationException(function);
        }
        return this;
    }

    @Override
    public void draw() {
        vertexArray.bind();
        if (indices instanceof Buffer buffer) {
            buffer.bind();
        }
        drawFunction.accept(this);
        vertexArray.unbind();
        GLX.checkError();
    }

    private String typeString(int shift) {
        int type = (int) ((mask >> shift) & 0xff);
        return switch (type) {
            case T_NULL -> "null";
            case T_BUFFER -> "Buffer";
            case T_BYTE_BUFFER -> "ByteBuffer";
            case T_INT_BUFFER -> "IntBuffer";
            case T_INT_ARRAY -> "int[]";
            case T_INT_BUFFER_ARRAY -> "IntBuffer[]";
            default -> "Unknown";
        };
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public InstancedDrawing.Builder instanced(int instanceCount) {
        this.instanceCount = instanceCount;
        mask |= F_INSTANCED;
        return this;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Drawing.Builder baseInstance(int baseInstance) {
        this.baseInstance = baseInstance;
        mask |= F_BASE_INSTANCE;
        return this;
    }

    @SuppressWarnings("rawtypes")
    private IndirectDrawing.Builder indirect(Object indirect, int type) {
        this.indices = indirect;
        mask |= F_INDIRECT | indicesType(indirect == null ? T_NULL : type);
        return this;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public IndirectDrawing.Builder indirect(int[] indirect) {
        return indirect(indirect, T_INT_ARRAY);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public IndirectDrawing.Builder indirect(IntBuffer indirect) {
        return indirect(indirect, T_INT_BUFFER);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public IndirectDrawing.Builder indirect(ByteBuffer indirect) {
        return indirect(indirect, T_BYTE_BUFFER);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public IndirectDrawing.Builder indirect() {
        mask |= F_INDIRECT;
        return this;
    }

    @Override
    public Drawing.Builder<VertexArray, GlVertexArrayDrawing> multi(int drawCount, int stride) {
        this.stride = stride;
        this.drawCount = drawCount;
        mask |= F_MULTI_STRIDE;
        return this;
    }

    @SuppressWarnings("rawtypes")
    private Drawing.Builder multi(Object counts, int drawCount, Object firstsOrBaseVertexes, int type) {
        this.counts = counts;
        this.drawCount = drawCount;
        this.firstsOrBaseVertexes = firstsOrBaseVertexes;
        mask |= F_MULTI_FIRSTS_BASE_VERTEX | countsType(counts == null ? T_NULL : type) | firstsOrBaseVertexesType(firstsOrBaseVertexes == null ? T_NULL : type);
        return this;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Drawing.Builder multi(int[] counts, int drawCount, int[] firstsOrBaseVertexes) {
        return multi(counts, drawCount, firstsOrBaseVertexes, T_INT_ARRAY);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Drawing.Builder multi(IntBuffer counts, int drawCount, IntBuffer firstsOrBaseVertexes) {
        return multi(counts, drawCount, firstsOrBaseVertexes, T_INT_BUFFER);
    }

    @SuppressWarnings("rawtypes")
    private Drawing.Builder multi(Object counts, int drawCount, int type) {
        this.counts = counts;
        this.drawCount = drawCount;
        mask |= F_MULTI_COUNTS | countsType(counts == null ? T_NULL : type);
        return this;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Drawing.Builder multi(int[] counts, int drawCount) {
        return multi(counts, drawCount, T_INT_ARRAY);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Drawing.Builder multi(IntBuffer counts, int drawCount) {
        return multi(counts, drawCount, T_INT_BUFFER);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public BaseVertexDrawing.Builder baseVertex(int baseVertex) {
        this.baseVertex = baseVertex;
        mask |= F_BASE_VERTEX;
        return this;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Drawing.Builder range(int min, int max) {
        this.min = min;
        this.max = max;
        mask |= F_RANGE;
        return this;
    }

    @Override
    public VertexArray source() {
        return vertexArray;
    }

    @Override
    public int baseInstance() {
        return baseInstance;
    }

    @Override
    public int baseVertex() {
        return baseVertex;
    }

    @Override
    public Object baseVertexes() {
        return firstsOrBaseVertexes;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public Object counts() {
        return counts;
    }

    @Nullable
    @Override
    public DataType dataType() {
        return dataType;
    }

    @Override
    public int drawCount() {
        return drawCount;
    }

    @Override
    public int first() {
        return first;
    }

    @Override
    public Object firsts() {
        return firstsOrBaseVertexes;
    }

    @Nullable
    @Override
    public Object indices() {
        return indices;
    }

    @Override
    public int instanceCount() {
        return instanceCount;
    }

    @Override
    public int max() {
        return max;
    }

    @Override
    public int min() {
        return min;
    }

    @Override
    public DrawMode mode() {
        return mode;
    }

    @Override
    public int stride() {
        return stride;
    }

    private static long arraysMask(int indicesType, int countsType, int firstsType, int... functions) {
        long masks = indicesType(indicesType) | countsType(countsType) | firstsOrBaseVertexesType(firstsType);
        for (var function : functions) {
            masks |= function;
        }
        return masks;
    }

    private static long firstsOrBaseVertexesType(long type) {
        return type << SHIFT_FIRSTS_OR_BASE_VERTEXES;
    }

    private static long countsType(long type) {
        return type << SHIFT_COUNTS;
    }

    private static long elementsMask(int indicesType, int countsType, int baseVertexesType, int... functions) {
        long masks = indicesType(indicesType) | countsType(countsType) | firstsOrBaseVertexesType(baseVertexesType);
        for (var function : functions) {
            masks |= function;
        }
        return masks;
    }

    private static void forEachType(IntToLongFunction keyMapper, IntFunction<Consumer<GlVertexArrayDrawing>> drawingFactory) {
        for (int type = T_START; type <= T_END; type++) {
            var drawing = drawingFactory.apply(type);
            if (drawing != null) {
                MAPPING.put(keyMapper.applyAsLong(type), drawing);
            }
        }
    }

    private static long indicesType(long type) {
        return type << SHIFT_INDICES;
    }

    private static PointerBuffer toPointerBuffer(IntBuffer[] buffers) {
        var pointerBuffer = MemoryUtil.memAllocPointer(buffers.length);
        for (var buffer : buffers) {
            pointerBuffer.put(MemoryUtil.memAddress(buffer));
        }
        return pointerBuffer;
    }

}
