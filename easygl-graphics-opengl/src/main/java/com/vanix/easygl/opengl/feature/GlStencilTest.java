package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.graphics.*;
import com.vanix.easygl.graphics.feature.StencilTest;
import com.vanix.easygl.opengl.Cache;
import com.vanix.easygl.opengl.GLX;

public class GlStencilTest extends GlFeature<StencilTest> implements StencilTest {

    public GlStencilTest(Graphics graphics) {
        super(GLX.GL_STENCIL_TEST, graphics);
    }

    @Override
    public StencilTest setFunction(CompareFunction function, int ref, int mask) {
        GLX.glStencilFunc(function.value(), ref, mask);
        return this;
    }

    @Override
    public StencilTest setFunctionSeparate(Face face, CompareFunction function, int ref, int mask) {
        GLX.glStencilFuncSeparate(face.value(), function.value(), ref, mask);
        return this;
    }

    @Override
    public CompareFunction getCompareFunction(Face face) {
        return Cache.CompareFunction.valueOf(GLX.glGetInteger(face == Face.Back ? GLX.GL_STENCIL_BACK_FUNC : GLX.GL_STENCIL_FUNC));
    }

    @Override
    public int getRef(Face face) {
        return GLX.glGetInteger(face == Face.Back ? GLX.GL_STENCIL_BACK_REF : GLX.GL_STENCIL_REF);
    }

    @Override
    public int getMask(Face face) {
        return GLX.glGetInteger(face == Face.Back ? GLX.GL_STENCIL_BACK_VALUE_MASK : GLX.GL_STENCIL_VALUE_MASK);
    }

    @Override
    public StencilTest setOps(Op stencilTestFailOp, Op depthTestFailOp, Op passOp) {
        GLX.glStencilOp(stencilTestFailOp.value(), depthTestFailOp.value(), passOp.value());
        return this;
    }

    @Override
    public StencilTest setOpsSeparate(Face face, Op stencilTestFailOp, Op depthTestFailOp, Op passOp) {
        GLX.glStencilOpSeparate(face.value(), stencilTestFailOp.value(), depthTestFailOp.value(), passOp.value());
        return this;
    }

    @Override
    public Op getOpAt(ActionPoint actionPoint) {
        return Cache.StencilTestOp.valueOf(GLX.glGetInteger(actionPoint.value()));
    }
}
