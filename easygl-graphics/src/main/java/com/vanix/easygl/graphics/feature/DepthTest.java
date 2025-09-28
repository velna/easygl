package com.vanix.easygl.graphics.feature;

import com.vanix.easygl.graphics.CompareFunction;

public interface DepthTest extends GraphicsFeature<DepthTest> {
    DepthTest setWriteDepthBuffer(boolean enable);

    boolean isWriteDepthBuffer();

    DepthTest setFunction(CompareFunction function);

    CompareFunction getFunction();
}
