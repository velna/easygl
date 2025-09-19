package com.vanix.easygl.graphics.draw;

import com.vanix.easygl.graphics.VertexArray;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface ArraysDrawing extends Drawing<VertexArray> {
    int first();

    int count();

    interface Builder<D extends ArraysDrawing> extends Drawing.Builder<VertexArray, D> {
        Drawing.Builder<VertexArray, MultiFirsts> multi(int[] counts, int drawCount, int[] firsts);

        Drawing.Builder<VertexArray, MultiFirsts> multi(IntBuffer counts, int drawCount, IntBuffer firsts);

        InstancedDrawing.Builder<VertexArray, Instanced, BaseInstanced> instanced(int instanceCount);

        IndirectDrawing.Builder<VertexArray, Indirect, MultiStrideIndirect> indirect(int[] indirect);

        IndirectDrawing.Builder<VertexArray, Indirect, MultiStrideIndirect> indirect(IntBuffer indirect);

        IndirectDrawing.Builder<VertexArray, Indirect, MultiStrideIndirect> indirect(ByteBuffer indirect);
    }

    interface Instanced extends ArraysDrawing, InstancedDrawing<VertexArray> {}

    interface BaseInstanced extends Instanced, BaseInstancedDrawing<VertexArray> {}

    interface Indirect extends ArraysDrawing, IndirectDrawing<VertexArray> {}

    interface MultiStrideIndirect extends Indirect, MultiStrideDrawing<VertexArray> {}

    interface MultiFirsts extends ArraysDrawing, MultiFirstsDrawing<VertexArray> {}
}
