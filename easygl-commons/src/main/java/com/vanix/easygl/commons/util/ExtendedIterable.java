package com.vanix.easygl.commons.util;

import org.apache.commons.lang3.stream.Streams;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public interface ExtendedIterable<T> extends Iterable<T> {
    default void forEach(EachAction<T> action) {
        AtomicInteger index = new AtomicInteger();
        forEach(e -> action.accept(e, index.getAndIncrement()));
    }

    interface EachAction<T> {
        void accept(T e, int i);
    }

    default Stream<T> stream() {
        return Streams.of(this);
    }
}
