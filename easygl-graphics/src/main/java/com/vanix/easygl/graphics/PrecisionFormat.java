package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public record PrecisionFormat(Type type, int range, int precision) {
    public enum Type implements IntEnum {
        LOW_FLOAT("LOW_FLOAT"),
        MEDIUM_FLOAT("MEDIUM_FLOAT"),
        HIGH_FLOAT("HIGH_FLOAT"),
        LOW_INT("LOW_INT"),
        MEDIUM_INT("MEDIUM_INT"),
        HIGH_INT("HIGH_INT");

        private final int value;

        Type(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }
}
