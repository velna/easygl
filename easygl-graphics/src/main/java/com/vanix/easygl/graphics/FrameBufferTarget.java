package com.vanix.easygl.graphics;

import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.meta.MetaSystem;
import lombok.ToString;

@ToString
final class FrameBufferTarget<T extends BaseFrameBuffer<T>> implements BaseFrameBuffer.Target<T> {
    @SuppressWarnings("rawtypes")
    static final FrameBufferTarget Read = new FrameBufferTarget("READ_FRAMEBUFFER");
    @SuppressWarnings("rawtypes")
    static final FrameBufferTarget Draw = new FrameBufferTarget("DRAW_FRAMEBUFFER");
    @SuppressWarnings("rawtypes")
    static final FrameBufferTarget Frame = new FrameBufferTarget("FRAMEBUFFER");
    private final int value;
    private final BindingState<BaseFrameBuffer.Target<T>, T> state;

    @SuppressWarnings("unchecked")
    private FrameBufferTarget(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
        this.state = MetaHolder.BaseFrameBuffer.newBindingState(id);
    }

    @Override
    public int value() {
        return value;
    }

    @Override
    public BindingState<BaseFrameBuffer.Target<T>, T> state() {
        return state;
    }

}
