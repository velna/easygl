package com.vanix.easygl.commons.attr;

public interface UpdatableBooleanAttribute<O> extends BooleanAttribute {

    O set(boolean value);

    default O enable() {
        return set(true);
    }

    default O disable() {
        return set(false);
    }
}
