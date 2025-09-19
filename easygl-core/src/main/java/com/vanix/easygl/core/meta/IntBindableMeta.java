package com.vanix.easygl.core.meta;

import com.vanix.easygl.commons.util.IntBiConsumer;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.HandleArray;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;

public class IntBindableMeta<E extends BindTarget<E, T>, T extends Handle> extends IntHandleMeta<T> implements BindableMeta<E, T> {
    private final IntBiConsumer bind;
    private final IntBiConsumer unbind;
    private final int unbindValue;

    public IntBindableMeta(Function<Object[], T> factory,
                           IntBiConsumer bind,
                           IntBiConsumer unbind,
                           int unbindValue,
                           IntConsumer close,
                           BiFunction<Integer, Object[], T> init) {
        this(factory, bind, unbind, unbindValue, close, init, null, null);
    }

    public IntBindableMeta(Function<Object[], T> factory,
                           IntBiConsumer bind,
                           IntBiConsumer unbind,
                           int unbindValue,
                           IntConsumer close,
                           BiFunction<Integer, Object[], T> init,
                           Consumer<int[]> initArray,
                           Consumer<int[]> closeArray) {
        this(factory, bind, unbind, unbindValue, close, init, null, null, HandleArray::new);
    }

    public IntBindableMeta(Function<Object[], T> factory,
                           IntBiConsumer bind,
                           IntBiConsumer unbind,
                           int unbindValue,
                           IntConsumer close,
                           BiFunction<Integer, Object[], T> init,
                           Consumer<int[]> initArray,
                           Consumer<int[]> closeArray,
                           ArrayFactory<T> arrayFactory) {
        super(factory, close, init, initArray, closeArray, arrayFactory);
        this.bind = bind;
        this.unbind = unbind;
        this.unbindValue = unbindValue;
    }

    @Override
    public BindingState<E, T> newBindingState(String name) {
        if (bind == null || unbind == null) {
            throw new UnsupportedOperationException();
        }
        return BindingState.ofInt(name, bind, unbind, unbindValue);
    }

    @Override
    public int unbindValueInt() {
        return unbindValue;
    }
}
