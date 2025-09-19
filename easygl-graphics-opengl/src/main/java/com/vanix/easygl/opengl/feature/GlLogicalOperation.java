package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.opengl.Cache;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.feature.LogicalOperation;

public class GlLogicalOperation extends GlFeature<LogicalOperation> implements LogicalOperation {


    public GlLogicalOperation(Graphics graphics) {
        super(GLX.GL_COLOR_LOGIC_OP, graphics);
    }

    @Override
    public LogicalOperation setOp(Op op) {
        GLX.glLogicOp(op.value());
        return this;
    }

    @Override
    public Op getOp() {
        return Cache.LogicalOp.valueOf(GLX.glGetInteger(GLX.GL_LOGIC_OP_MODE));
    }
}
