package com.vanix.easygl.graphics.feature;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public interface LogicalOperation extends GraphicsFeature<LogicalOperation> {
    LogicalOperation setOp(Op op);

    Op getOp();

    enum Op implements IntEnum {
        Clear("CLEAR"),
        Set("SET"),
        Copy("COPY"),
        CopyInverted("COPY_INVERTED"),
        Noop("NOOP"),
        Invert("INVERT"),
        And("AND"),
        Nand("NAND"),
        Or("OR"),
        Nor("NOR"),
        Xor("XOR"),
        Equiv("EQUIV"),
        AndReverse("AND_REVERSE"),
        AndInverted("AND_INVERTED"),
        OrReverse("OR_REVERSE"),
        OrInverted("OR_INVERTED");

        private final int value;

        Op(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }
}
