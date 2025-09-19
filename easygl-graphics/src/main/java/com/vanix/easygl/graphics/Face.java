package com.vanix.easygl.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum Face {
    Front("FRONT"),
    Back("BACK"),
    FrontAndBack("FRONT_AND_BACK");
    private final int value;

    Face(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
