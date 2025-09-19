package com.vanix.easygl.commons.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

public class ListenerSupport<T extends EventListener> {

    private final List<T>[] array;
    private int size;
    private final Runnable initFunction;
    private final Runnable closeFunction;

    @SuppressWarnings("unchecked")
    public ListenerSupport(int maxKey, Runnable initFunction, Runnable closeFunction) {
        this.array = new List[maxKey + 1];
        this.initFunction = initFunction;
        this.closeFunction = closeFunction;
    }

    public ListenerSupport(int maxKey) {
        this(maxKey, null, null);
    }

    @SafeVarargs
    public final <K> ListenerOperation<T> listen(ToIntFunction<K> mapper, K... keys) {
        int[] ks = new int[keys.length];
        for (int i = 0; i < keys.length; i++) {
            ks[i] = mapper.applyAsInt(keys[i]);
        }
        return listen(ks);
    }

    public ListenerOperation<T> listen(int... keys) {
        return new ListenerOperation<>() {
            @Override
            public void subscribe(T listener) {
                if (keys.length == 0) {
                    add(0, listener);
                } else {
                    for (var k : keys) {
                        add(k, listener);
                    }
                }
            }

            @Override
            public void unsubscribe(T listener) {
                if (keys.length == 0) {
                    remove(0, listener);
                } else {
                    for (var k : keys) {
                        remove(k, listener);
                    }
                }
            }
        };
    }

    private void add(int key, T listener) {
        var listeners = array[key];
        if (listeners == null) {
            array[key] = listeners = new ArrayList<>();
        }
        if (listeners.contains(listener)) {
            return;
        }
        listeners.add(listener);
        if (size++ == 0 && initFunction != null) {
            initFunction.run();
        }
    }

    private void remove(int key, T listener) {
        var listeners = array[key];
        if (listeners != null && listeners.remove(listener) && --size == 0 && closeFunction != null) {
            closeFunction.run();
        }
    }

    public void forEach(int key, Consumer<T> consumer) {
        var listeners = array[key];
        if (listeners != null && !listeners.isEmpty()) {
            listeners.forEach(consumer);
        }
    }

}
