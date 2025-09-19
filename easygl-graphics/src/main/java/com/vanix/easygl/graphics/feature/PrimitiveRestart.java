package com.vanix.easygl.graphics.feature;

import com.vanix.easygl.core.Support;
import com.vanix.easygl.graphics.Version;

@Support(since = Version.GL31)
public interface PrimitiveRestart extends GraphicsFeature<PrimitiveRestart> {
    PrimitiveRestart setRestartIndex(int index);
}
