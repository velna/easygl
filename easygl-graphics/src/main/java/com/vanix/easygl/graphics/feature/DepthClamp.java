package com.vanix.easygl.graphics.feature;

import com.vanix.easygl.core.Support;
import com.vanix.easygl.graphics.Version;

public interface DepthClamp extends GraphicsFeature<DepthClamp> {

    DepthClamp setDepthRange(double near, double far);

    @Support(since = Version.GL41)
    DepthClamp setDepthRange(float near, float far);

    @Support(since = Version.GL41)
    float[] getDepthRangeFloat();

    double[] getDepthRangeDouble();

    int[] getDepthRangeIntMapped();

}
