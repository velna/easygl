package com.vanix.easygl.application;

import com.vanix.easygl.commons.Identified;
import com.vanix.easygl.core.Closeable;

public interface Renderer<A extends ClientApp, C extends RenderContext> extends Closeable, Identified<String> {

    @Override
    default String id() {
        return this.getClass().getSimpleName();
    }

    default void init(A app) {

    }

    default void close() {

    }

    default boolean isActive() {
        return true;
    }

    void render(C context);
}
