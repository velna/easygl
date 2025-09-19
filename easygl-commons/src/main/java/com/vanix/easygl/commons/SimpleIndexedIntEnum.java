package com.vanix.easygl.commons;

public class SimpleIndexedIntEnum extends SimpleIntEnum implements IndexedIntEnum {
    protected final int index;

    public SimpleIndexedIntEnum(int value, int index) {
        super(value);
        this.index = index;
    }

    @Override
    public int index() {
        return index;
    }
}
