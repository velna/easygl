package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum MinFilter implements IntEnum {
    Nearest("NEAREST"),
    Linear("LINEAR"),
    NearestMipmapNearest("NEAREST_MIPMAP_NEAREST"),
    LinearMipmapNearest("LINEAR_MIPMAP_NEAREST"),
    NearestMipmapLinear("NEAREST_MIPMAP_LINEAR"),
    LinearMipmapLinear("LINEAR_MIPMAP_LINEAR");

    private final int value;

    MinFilter(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
