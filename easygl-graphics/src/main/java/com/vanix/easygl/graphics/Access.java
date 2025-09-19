package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum Access implements IntEnum {
    ReadOnly("READ_ONLY"),
    WriteOnly("WRITE_ONLY"),
    ReadWrite("READ_WRITE");

    private final int value;

    Access(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }

}
