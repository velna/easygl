package com.vanix.easygl.core.meta;

import com.vanix.easygl.commons.attr.*;

public class MetaSystemAttributesEnum<A, O> extends AttributesEnum<A> {
    private final MetaSystem metaSystem;
    protected final O owner;

    public MetaSystemAttributesEnum(MetaSystem metaSystem, O owner) {
        this.metaSystem = metaSystem;
        this.owner = owner;
    }

    public O then() {
        return owner;
    }

    protected BooleanAttribute ofBoolean(String id) {
        return ofBoolean(metaSystem.queryInt(id));
    }

    protected UpdatableBooleanAttribute<A> ofUpdatableBoolean(String id) {
        return ofUpdatableBoolean(metaSystem.queryInt(id));
    }

    protected IntAttribute ofInt(String id) {
        return ofInt(metaSystem.queryInt(id));
    }

    protected UpdatableIntAttribute<A> ofUpdatableInt(String id) {
        return ofUpdatableInt(metaSystem.queryInt(id));
    }

    protected Attribute<A> of(String id) {
        return of(metaSystem.queryInt(id));
    }

    protected <T> UpdatableAttribute<A, T> ofUpdatable(String id) {
        return ofUpdatable(metaSystem.queryInt(id));
    }

}
