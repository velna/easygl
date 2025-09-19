package com.vanix.easygl.core;

import com.vanix.easygl.commons.Chained;

public interface Feature<T extends Feature<T, C>, C> extends Chained<C> {
    boolean isEnabled();

    T enable();

    T disable();
}
