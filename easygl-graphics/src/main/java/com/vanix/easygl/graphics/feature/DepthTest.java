package com.vanix.easygl.graphics.feature;

import com.vanix.easygl.graphics.CompareFunction;

public interface DepthTest extends GraphicsFeature<DepthTest> {

    DepthTest setFunction(CompareFunction function);

    CompareFunction getFunction();
}
