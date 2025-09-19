package com.vanix.easygl.graphics.draw;

public interface InstancedDrawing<S> extends Drawing<S> {
    int instanceCount();

    interface Builder<S, D extends InstancedDrawing<S>, BD extends BaseInstancedDrawing<S>> extends Drawing.Builder<S, D> {
        Drawing.Builder<S, BD> baseInstance(int baseInstance);
    }
}
