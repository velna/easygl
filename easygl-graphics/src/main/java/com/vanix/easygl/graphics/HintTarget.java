package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum HintTarget implements IntEnum {
    FragmentShaderDerivative("FRAGMENT_SHADER_DERIVATIVE_HINT"),
    LineSmooth("LINE_SMOOTH_HINT"),
    PolygonSmooth("POLYGON_SMOOTH_HINT"),
    TextureCompression("TEXTURE_COMPRESSION_HINT");
    private final int value;

    HintTarget(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
