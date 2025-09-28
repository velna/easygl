package com.vanix.easygl.graphics.feature;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.graphics.CompareFunction;
import com.vanix.easygl.graphics.Face;
import com.vanix.easygl.graphics.Version;

public interface StencilTest extends GraphicsFeature<StencilTest> {

    StencilTest setMask(int mask);

    int getMask();

    StencilTest setFunction(CompareFunction function, int ref, int mask);

    @Support(since = Version.GL20)
    StencilTest setFunctionSeparate(Face face, CompareFunction function, int ref, int mask);

    CompareFunction getCompareFunction(Face face);

    int getRef(Face face);

    int getMask(Face face);

    StencilTest setOps(Op stencilTestFailOp, Op depthTestFailOp, Op passOp);

    @Support(since = Version.GL20)
    StencilTest setOpsSeparate(Face face, Op stencilTestFailOp, Op depthTestFailOp, Op passOp);

    Op getOpAt(ActionPoint actionPoint);

    enum ActionPoint implements IntEnum {
        StencilFail("STENCIL_FAIL"),
        StencilPassDepthPass("STENCIL_PASS_DEPTH_PASS"),
        StencilPassDepthFail("STENCIL_PASS_DEPTH_FAIL"),
        StencilBackFail("STENCIL_BACK_FAIL"),
        StencilBackPassDepthPass("STENCIL_BACK_PASS_DEPTH_PASS"),
        StencilBackPassDepthFail("STENCIL_BACK_PASS_DEPTH_FAIL");

        private final int value;

        ActionPoint(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum Op implements IntEnum {
        Zero("ZERO"),
        Keep("KEEP"),
        Replace("REPLACE"),
        Incr("INCR"),
        Decr("DECR"),
        IncrWrap("INCR_WRAP"),
        DecrWrap("DECR_WRAP"),
        Invert("INVERT");
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
