package com.vanix.easygl.graphics.feature;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.IndexedFeature;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.graphics.Version;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.graphics.FrameInnerBuffer;
import com.vanix.easygl.graphics.Graphics;

public interface Blending extends IndexedFeature<Blending, Graphics>, GraphicsFeature<Blending> {

    Blending setFunction(Function src, Function dst);

    Blending setFunction(Function srcRgb, Function dstRgb, Function srcAlpha, Function dstAlpha);

    @Support(since = Version.GL40)
    Blending setFunction(FrameInnerBuffer.DrawBuffer drawBuffer, Function src, Function dst);

    @Support(since = Version.GL40)
    Blending setFunction(FrameInnerBuffer.DrawBuffer drawBuffer, Function srcRgb, Function dstRgb, Function srcAlpha, Function dstAlpha);

    Function getSrcRGBFunction();

    Function getDstRGBFunction();

    Function getSrcAlphaFunction();

    Function getDstAlphaFunction();

    default Function[] getFunctions() {
        return new Function[]{getSrcRGBFunction(), getDstRGBFunction(), getSrcAlphaFunction(), getDstAlphaFunction()};
    }

    default Blending setColor(Color rgba) {
        return setColor(rgba.red(), rgba.green(), rgba.blue(), rgba.alpha());
    }

    Blending setColor(float red, float green, float blue, float alpha);

    Color getColor();

    Blending setEquation(Equation equation);

    @Support(since = Version.GL40)
    Blending setEquation(FrameInnerBuffer.DrawBuffer drawBuffer, Equation equation);

    @Support(since = Version.GL40)
    Blending setEquation(FrameInnerBuffer.DrawBuffer drawBuffer, Equation equationRgb, Equation equationAlpha);

    Blending setEquation(Equation equationRgb, Equation equationAlpha);

    Equation getRGBEquation();

    Equation getAlphaEquation();

    enum Function implements IntEnum {
        Zero("ZERO"),
        One("ONE"),
        SrcColor("SRC_COLOR"),
        OneMinusSrcColor("ONE_MINUS_SRC_COLOR"),
        DstColor("DST_COLOR"),
        OneMinusDstColor("ONE_MINUS_DST_COLOR"),
        SrcAlpha("SRC_ALPHA"),
        OneMinusSrcAlpha("ONE_MINUS_SRC_ALPHA"),
        DstAlpha("DST_ALPHA"),
        OneMinusDstAlpha("ONE_MINUS_DST_ALPHA"),
        ConstantColor("CONSTANT_COLOR"),
        OneMinusConstantColor("ONE_MINUS_CONSTANT_COLOR"),
        ConstantAlpha("CONSTANT_ALPHA"),
        OneMinusConstantAlpha("ONE_MINUS_CONSTANT_ALPHA");
        private final int value;

        Function(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum Equation implements IntEnum {
        FuncAdd("FUNC_ADD"),
        FuncSubtract("FUNC_SUBTRACT"),
        FuncReverseSubtract("FUNC_REVERSE_SUBTRACT"),
        Min("MIN"),
        Max("MAX");
        private final int value;

        Equation(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

}
