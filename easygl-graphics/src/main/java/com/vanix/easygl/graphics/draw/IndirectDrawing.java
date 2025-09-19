package com.vanix.easygl.graphics.draw;

public interface IndirectDrawing<S> extends Drawing<S> {
    Object indices();

    interface Builder<S, D extends IndirectDrawing<S>, MD extends MultiStrideDrawing<S>> extends Drawing.Builder<S, D> {
        Drawing.Builder<S, MD> multi(int drawCount, int stride);
    }
}
