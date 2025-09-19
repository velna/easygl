package com.vanix.easygl.core;

import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public class AbstractMultiTargetBindable<E extends BindTarget<E, T>, T extends MultiTargetBindable<E, T>>
        extends AbstractHandle
        implements MultiTargetBindable<E, T> {

    protected E target;

    public AbstractMultiTargetBindable(int handle, IntConsumer closeFunction) {
        super(handle, closeFunction);
    }

    public AbstractMultiTargetBindable(long handle, LongConsumer closeFunction) {
        super(handle, closeFunction);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T bind(E target) {
        T self = (T) this;
        target.state().bind(self, target);
        this.target = target;
        return self;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T unbind(E target) {
        target.state().unbind(target);
        return (T) this;
    }

    @Override
    public E target() {
        return target;
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }
}
