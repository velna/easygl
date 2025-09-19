package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.opengl.Cache;
import com.vanix.easygl.graphics.FrameInnerBuffer;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.feature.Blending;

public class GlBlending extends GlFeature<Blending> implements Blending {

    public GlBlending(Graphics graphics) {
        super(GLX.GL_BLEND, graphics);
    }

    public Blending setFunction(Function src, Function dst) {
        GLX.glBlendFunc(src.value(), dst.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Blending setFunction(Function srcRgb, Function dstRgb, Function srcAlpha, Function dstAlpha) {
        GLX.glBlendFuncSeparate(srcRgb.value(), dstRgb.value(), srcAlpha.value(), dstAlpha.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Blending setFunction(FrameInnerBuffer.DrawBuffer drawBuffer, Function src, Function dst) {
        GLX.glBlendFunci(drawBuffer.index(), src.value(), dst.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Blending setFunction(FrameInnerBuffer.DrawBuffer drawBuffer, Function srcRgb, Function dstRgb, Function srcAlpha, Function dstAlpha) {
        GLX.glBlendFuncSeparatei(drawBuffer.index(), srcRgb.value(), dstRgb.value(), srcAlpha.value(), dstAlpha.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Function getSrcRGBFunction() {
        return Cache.BlendingFunction.valueOf(GLX.glGetInteger(GLX.GL_BLEND_SRC_RGB));
    }

    @Override
    public Function getDstRGBFunction() {
        return Cache.BlendingFunction.valueOf(GLX.glGetInteger(GLX.GL_BLEND_DST_RGB));
    }

    @Override
    public Function getSrcAlphaFunction() {
        return Cache.BlendingFunction.valueOf(GLX.glGetInteger(GLX.GL_BLEND_SRC_ALPHA));
    }

    @Override
    public Function getDstAlphaFunction() {
        return Cache.BlendingFunction.valueOf(GLX.glGetInteger(GLX.GL_BLEND_DST_ALPHA));
    }

    @Override
    public Blending setColor(float red, float green, float blue, float alpha) {
        GLX.glBlendColor(red, green, blue, alpha);
        GLX.checkError();
        return this;
    }

    @Override
    public Color setColor() {
        float[] data = new float[4];
        GLX.glGetFloatv(GLX.GL_BLEND_COLOR, data);
        return new Color(data[0], data[1], data[2], data[3]);
    }

    @Override
    public Blending enableAt(int index) {
        GLX.glEnablei(GLX.GL_BLEND, index);
        GLX.checkError();
        return this;
    }

    @Override
    public Blending disableAt(int index) {
        GLX.glDisablei(GLX.GL_BLEND, index);
        GLX.checkError();
        return this;
    }

    @Override
    public boolean isEnabledAt(int index) {
        return GLX.glIsEnabledi(GLX.GL_BLEND, index);
    }

    @Override
    public Blending setEquation(FrameInnerBuffer.DrawBuffer drawBuffer, Equation equation) {
        GLX.glBlendEquationi(drawBuffer.index(), equation.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Blending setEquation(FrameInnerBuffer.DrawBuffer drawBuffer, Equation equationRgb, Equation equationAlpha) {
        GLX.glBlendEquationSeparatei(drawBuffer.index(), equationRgb.value(), equationAlpha.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Blending setEquation(Equation equation) {
        GLX.glBlendEquation(equation.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Blending setEquation(Equation equationRgb, Equation equationAlpha) {
        GLX.glBlendEquationSeparate(equationRgb.value(), equationAlpha.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Equation getRGBEquation() {
        return Cache.BlendingEquation.valueOf(GLX.glGetInteger(GLX.GL_BLEND_EQUATION_RGB));
    }

    @Override
    public Equation getAlphaEquation() {
        return Cache.BlendingEquation.valueOf(GLX.glGetInteger(GLX.GL_BLEND_EQUATION_ALPHA));
    }
}