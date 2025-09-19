package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.BaseFrameBuffer;
import com.vanix.easygl.graphics.DefaultFrameBuffer;
import com.vanix.easygl.graphics.FrameBuffer;

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
    public DefaultFrameBuffer invalidate(Target<FrameBuffer> target, Invalidatable attachment) {
        assertBinding();
        GLX.glInvalidateFramebuffer(target.value(), attachment.value());
        return this;
    }

    @Override
    public DefaultFrameBuffer invalidate(Target<FrameBuffer> target, int x, int y, int width, int height, Invalidatable attachment) {
        assertBinding();
        GLX.glInvalidateSubFramebuffer(target.value(), attachment.value(), x, y, width, height);
        return this;
    }
}
