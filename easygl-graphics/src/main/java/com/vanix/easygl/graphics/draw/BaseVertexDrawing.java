package com.vanix.easygl.graphics.draw;

public interface BaseVertexDrawing<S> extends Drawing<S> {
    int baseVertex();

    interface Builder<S, D extends BaseVertexDrawing<S>,
            ID extends InstancedBaseVertexDrawing<S>,
            BD extends BaseInstancedBaseVertexDrawing<S>,
            RD extends RangeBaseVertexDrawing<S>> extends Drawing.Builder<S, D> {
        InstancedDrawing.Builder<S, ID, BD> instanced(int instanceCount);

        Drawing.Builder<S, RD> range(int min, int max);
    }
}
