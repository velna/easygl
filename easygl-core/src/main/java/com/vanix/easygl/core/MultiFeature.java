package com.vanix.easygl.core;

import com.vanix.easygl.commons.Chained;

public interface MultiFeature<F extends Enum<F>, T extends MultiFeature<F, T, C>, C> extends Chained<C> {
    T enable(F feature);

    T disable(F feature);

    boolean isEnable(F feature);
}
