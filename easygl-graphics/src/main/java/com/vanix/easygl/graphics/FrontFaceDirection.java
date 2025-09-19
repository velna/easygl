package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum FrontFaceDirection implements IntEnum {
    Clockwise("CW"),
    Counterclockwise("CCW");
    private final int value;

    FrontFaceDirection(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
