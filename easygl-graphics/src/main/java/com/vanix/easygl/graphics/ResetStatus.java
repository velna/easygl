package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum ResetStatus implements IntEnum {
    NoRest("NO_ERROR"),
    GuiltyContextReset("GUILTY_CONTEXT_RESET"),
    InnocentContextReset("INNOCENT_CONTEXT_RESET"),
    UnknownContextReset("UNKNOWN_CONTEXT_RESET");
    private final int value;

    ResetStatus(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
