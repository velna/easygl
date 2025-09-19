package com.vanix.easygl.commons.attr;

import com.vanix.easygl.commons.EnumType;

public abstract class AttributesEnum<A> extends EnumType<AttributeType> {
    protected AttributesEnum() {
        super(AttributeType.class);
    }

    protected BooleanAttribute ofBoolean(int key) {
        throw new UnsupportedOperationException();
    }

    protected UpdatableBooleanAttribute<A> ofUpdatableBoolean(int key) {
        throw new UnsupportedOperationException();
    }

    protected IntAttribute ofInt(int key) {
        throw new UnsupportedOperationException();
    }

    protected UpdatableIntAttribute<A> ofUpdatableInt(int key) {
        throw new UnsupportedOperationException();
    }

    protected Attribute<A> of(int key) {
        throw new UnsupportedOperationException();
    }

    protected <T> UpdatableAttribute<A, T> ofUpdatable(int key) {
        throw new UnsupportedOperationException();
    }

}
