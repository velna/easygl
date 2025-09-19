package com.vanix.easygl.core;

import com.vanix.easygl.commons.Identified;
import com.vanix.easygl.commons.util.IntBiConsumer;
import com.vanix.easygl.commons.util.IntLongConsumer;
import lombok.ToString;

import java.util.Objects;

@ToString(of = {"id", "handle"})
public class BindingState<E extends BindTarget<E, T>, T extends Handle> implements Identified<String> {

    private final String id;
    private final IntLongConsumer unbindFunction;
    private final IntLongConsumer bindFunction;
    final long unbindValue;
    private T handle;

    private BindingState(String id, IntLongConsumer bindFunction, IntLongConsumer unbindFunction, long unbindValue) {
        this.id = String.format("Binding#%s", id);
        this.unbindValue = unbindValue;
        this.bindFunction = bindFunction;
        this.unbindFunction = unbindFunction;
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofInt(String id, IntBiConsumer bindFunction) {
        return ofInt(id, bindFunction, 0);
    }

    public static <E extends BindTarget<E, T>, T extends Handle> BindingState<E, T> ofInt(String id, IntBiConsumer bindFunction, int unbindValue) {
        return ofInt(id, bindFunction, bindFunction, unbindValue);
    }

    public static <E extends BindTarget<E, T>, T extends Handle> BindingState<E, T> ofInt(String id, IntBiConsumer bindFunction, IntBiConsumer unbindFunction, int unbindValue) {
        return new BindingState<>(id, (t, h) -> bindFunction.accept(t, (int) h), (t, h) -> unbindFunction.accept(t, (int) h), unbindValue);
    }

    public static <E extends BindTarget<E, T>, T extends Handle> BindingState<E, T> ofLong(String id, IntLongConsumer bindFunction) {
        return ofLong(id, bindFunction, 0);
    }

    public static <E extends BindTarget<E, T>, T extends Handle> BindingState<E, T> ofLong(String id, IntLongConsumer bindFunction, long unbindValue) {
        return ofLong(id, bindFunction, bindFunction, unbindValue);
    }

    public static <E extends BindTarget<E, T>, T extends Handle> BindingState<E, T> ofLong(String id, IntLongConsumer bindFunction, IntLongConsumer unbindFunction, long unbindValue) {
        return new BindingState<>(id, bindFunction, unbindFunction, unbindValue);
    }

    @Override
    public String id() {
        return id;
    }

    public T bind(T handle, E target) {
        long h = handle.handle();
        bindFunction.accept(target.value(), h);
        this.handle = handle;
        return handle;
    }

    public void unbind(E target) {
        unbindFunction.accept(target.value(), unbindValue);
        this.handle = null;
    }

    public boolean isBound() {
        return handle != null;
    }

    public boolean isBoundTo(T handle) {
        return Objects.equals(this.handle, handle);
    }

    public long unbindValue() {
        return unbindValue;
    }

}
