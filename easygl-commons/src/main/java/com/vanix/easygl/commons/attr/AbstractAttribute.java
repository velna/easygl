package com.vanix.easygl.commons.attr;

public class AbstractAttribute implements AttributeType {
    protected final int key;

    public AbstractAttribute(int key) {
        this.key = key;
    }

    @Override
    public int key() {
        return key;
    }
}
