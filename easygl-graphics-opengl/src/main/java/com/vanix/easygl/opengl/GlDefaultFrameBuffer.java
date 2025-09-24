package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.BaseFrameBuffer;
import com.vanix.easygl.graphics.DefaultFrameBuffer;

public class GlDefaultFrameBuffer extends GlBaseFrameBuffer<DefaultFrameBuffer> implements DefaultFrameBuffer {
    public GlDefaultFrameBuffer() {
        super(0, h -> {
        });
        target = BaseFrameBuffer.Target.frame();
    }

    @Override
    public DefaultFrameBuffer selectDrawBuffer(DrawBuffer drawBuffer) {
        GLX.glDrawBuffer(drawBuffer.value());
        return this;
    }

    @Override
    public DefaultFrameBuffer invalidate(Invalidatable attachment) {
        assertBinding();
        GLX.glInvalidateFramebuffer(target.value(), attachment.value());
        GLX.checkError();
        return this;
    }

    @Override
    public DefaultFrameBuffer invalidate(int x, int y, int width, int height, Invalidatable attachment) {
        assertBinding();
        GLX.glInvalidateSubFramebuffer(target.value(), attachment.value(), x, y, width, height);
        GLX.checkError();
        return this;
    }
}
