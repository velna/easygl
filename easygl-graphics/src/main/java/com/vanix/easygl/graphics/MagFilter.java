package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum MagFilter implements IntEnum {
    Nearest("NEAREST"),
    Linear("LINEAR");

    private final int value;

    MagFilter(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
