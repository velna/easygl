package com.vanix.easygl.commons.util;

import org.apache.commons.collections4.list.AbstractSerializableListDecorator;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.IntFunction;

public class LazyList<E> extends AbstractSerializableListDecorator<E> {

    /**
     * The factory to use to lazily instantiate the objects
     */
    private final IntFunction<? extends E> factory;

    /**
     * Factory method to create a lazily instantiating list.
     *
     * @param <E>     the type of the elements in the list
     * @param list    the list to decorate, must not be null
     * @param factory the factory to use for creation, must not be null
     * @return a new lazy list
     * @throws NullPointerException if list or factory is null
     * @since 4.0
     */
    public static <E> LazyList<E> lazyList(final List<E> list, final IntFunction<? extends E> factory) {
        return new LazyList<>(list, factory);
    }

    //-----------------------------------------------------------------------

    /**
     * Constructor that wraps (not copies).
     *
     * @param list    the list to decorate, must not be null
     * @param factory the factory to use for creation, must not be null
     * @throws NullPointerException if list or factory is null
     */
    protected LazyList(final List<E> list, final IntFunction<? extends E> factory) {
        super(list);
        if (factory == null) {
            throw new IllegalArgumentException("Factory must not be null");
        }
        this.factory = factory;
    }

    //-----------------------------------------------------------------------

    /**
     * Decorate the get method to perform the lazy behaviour.
     * <p>
     * If the requested index is greater than the current size, the list will
     * grow to the new size and a new object will be returned from the factory.
     * Indexes in-between the old size and the requested size are left with a
     * placeholder that is replaced with a factory object when requested.
     *
     * @param index the index to retrieve
     * @return the element at the given index
     */
    @Override
    public E get(final int index) {
        final int size = decorated().size();
        if (index < size) {
            // within bounds, get the object
            E object = decorated().get(index);
            if (object == null) {
                // item is a place holder, create new one, set and return
                object = factory.apply(index);
                decorated().set(index, object);
                return object;
            }
            // good and ready to go
            return object;
        }
        // we have to grow the list
        for (int i = size; i < index; i++) {
            decorated().add(null);
        }
        // create our last object, set and return
        final E object = factory.apply(index);
        decorated().add(object);
        return object;
    }

    @Nonnull
    @Override
    public List<E> subList(final int fromIndex, final int toIndex) {
        final List<E> sub = decorated().subList(fromIndex, toIndex);
        return new LazyList<>(sub, factory);
    }

}
