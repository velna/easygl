package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum CompareFunction implements IntEnum {
    Equal("EQUAL"),
    LessThan("LESS"),
    GreaterThan("GREATER"),
    LessEqual("LEQUAL"),
    GreaterEqual("GEQUAL"),
    NotEqual("NOTEQUAL"),
    Always("ALWAYS"),
    Never("NEVER");

    private final int value;

    CompareFunction(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
