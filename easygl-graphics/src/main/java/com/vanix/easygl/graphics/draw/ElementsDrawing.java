package com.vanix.easygl.graphics.draw;

import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.VertexArray;

import java.nio.IntBuffer;

public interface ElementsDrawing extends Drawing<VertexArray> {
    DataType dataType();

    Object indices();

    interface Builder<D extends ElementsDrawing> extends Drawing.Builder<VertexArray, D> {
        InstancedDrawing.Builder<VertexArray, Instanced, BaseInstanced> instanced(int instanceCount);

        IndirectDrawing.Builder<VertexArray, Indirect, MultiStrideIndirect> indirect();

        Drawing.Builder<VertexArray, MultiCounts> multi(int[] counts, int drawCount);

        Drawing.Builder<VertexArray, MultiCounts> multi(IntBuffer counts, int drawCount);

        BaseVertexDrawing.Builder<VertexArray, BaseVertex, InstancedBaseVertex, BaseInstancedBaseVertex, RangeBaseVertex> baseVertex(int baseVertex);

        Drawing.Builder<VertexArray, MultiBaseVertex> multi(int[] counts, int drawCount, int[] baseVertexes);

        Drawing.Builder<VertexArray, MultiBaseVertex> multi(IntBuffer counts, int drawCount, IntBuffer baseVertexes);

        Drawing.Builder<VertexArray, Range> range(int min, int max);
    }

    interface Instanced extends ElementsDrawing, InstancedDrawing<VertexArray> {}

    interface BaseInstanced extends Instanced, BaseInstancedDrawing<VertexArray> {}

    interface Range extends ElementsDrawing, RangeDrawing<VertexArray> {}

    interface Indirect extends ElementsDrawing, IndirectDrawing<VertexArray> {}

    interface MultiStrideIndirect extends Indirect, MultiStrideDrawing<VertexArray> {}

    interface BaseVertex extends ElementsDrawing, BaseVertexDrawing<VertexArray> {}

    interface InstancedBaseVertex extends BaseVertex, InstancedBaseVertexDrawing<VertexArray> {}

    interface BaseInstancedBaseVertex extends InstancedBaseVertex, BaseInstancedBaseVertexDrawing<VertexArray> {}

    interface RangeBaseVertex extends BaseVertex, RangeBaseVertexDrawing<VertexArray> {}

    interface MultiCounts extends ElementsDrawing, MultiCountsDrawing<VertexArray> {}

    interface MultiBaseVertex extends ElementsDrawing, MultiBaseVertexDrawing<VertexArray> {}

}
