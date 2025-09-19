package com.vanix.easygl.core;

import com.vanix.easygl.commons.util.ThrowingConsumer;

import java.util.function.Consumer;

public interface Bindable<E extends BindTarget<E, T>, T extends Bindable<E, T>> extends Handle {
    E target();

    @SuppressWarnings("unchecked")
    default T bind() {
        E target = target();
        return target.state().bind((T) this, target);
    }

    default void unbind() {
        E target = target();
        target.state().unbind(target);
    }

    @SuppressWarnings("unchecked")
    default boolean isBound() {
        return target().state().isBoundTo((T) this);
    }

    default void assertBinding() throws IllegalStateException {
        if (!isBound()) {
            throw new IllegalStateException(this + " not bound.");
        }
    }

    default void use(Consumer<T> callback) {
        try {
            callback.accept(bind());
        } finally {
            unbind();
        }
    }

    default <R extends Throwable> void useOrThrow(ThrowingConsumer<T, R> callback) throws R {
        try {
            callback.accept(bind());
        } finally {
            unbind();
        }
    }

    @SuppressWarnings("unchecked")
    default T self() {
        return (T) this;
    }
}
