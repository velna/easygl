package com.vanix.easygl.graphics.draw;

public interface MultiStrideDrawing<S> extends IndirectDrawing<S> {
    int drawCount();

    int stride();
}
