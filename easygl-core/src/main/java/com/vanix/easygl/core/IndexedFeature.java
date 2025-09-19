package com.vanix.easygl.core;

public interface IndexedFeature<T extends IndexedFeature<T, C>, C> extends Feature<T, C> {
    T enableAt(int index);

    T disableAt(int index);

    boolean isEnabledAt(int index);
}
