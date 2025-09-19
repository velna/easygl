package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum PolygonMode implements IntEnum {
    Point("POINT"),
    Line("LINE"),
    Fill("FILL");
    private final int value;

    PolygonMode(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
