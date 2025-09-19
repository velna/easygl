package com.vanix.easygl.core.meta;

import com.vanix.easygl.commons.util.IntLongConsumer;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.BindingState;

import java.util.Objects;
import java.util.function.Function;

public class LongBindableMeta<E extends BindTarget<E, T>, T extends Bindable<E, T>> extends DefaultMeta<T> implements BindableMeta<E, T> {
    private final IntLongConsumer bind;
    private final IntLongConsumer unbind;
    private final long unbindValue;

    public LongBindableMeta(Function<Object[], T> factory,
                            IntLongConsumer bind,
                            IntLongConsumer unbind,
                            long unbindValue) {
        super(factory);
        this.bind = Objects.requireNonNull(bind);
        this.unbind = Objects.requireNonNull(unbind);
        this.unbindValue = unbindValue;
    }

    @Override
    public BindingState<E, T> newBindingState(String name) {
        return BindingState.ofLong(name, bind, unbind, unbindValue);
    }

    @Override
    public long unbindValueLong() {
        return unbindValue;
    }
}
