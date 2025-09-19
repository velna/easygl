package com.vanix.easygl.commons;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class SimpleIntEnum implements IntEnum {

    protected final int value;

    public SimpleIntEnum(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return value;
    }
}
