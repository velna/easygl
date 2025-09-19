package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum Origin implements IntEnum {
    LowerLeft("LOWER_LEFT"),
    UpperLeft("UPPER_LEFT");
    private final int value;

    Origin(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
