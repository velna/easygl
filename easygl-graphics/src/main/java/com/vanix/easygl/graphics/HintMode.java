package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum HintMode implements IntEnum {
    Fastest("FASTEST"),
    Nicest("NICEST"),
    DontCare("DONT_CARE");
    private final int value;

    HintMode(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
