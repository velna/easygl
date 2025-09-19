package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.graphics.Buffer;
import com.vanix.easygl.graphics.DrawMode;
import com.vanix.easygl.graphics.TransformFeedback;
import com.vanix.easygl.graphics.draw.TransformFeedbackDrawing;

import java.util.function.IntConsumer;

public class GlTransformFeedback extends AbstractBindable<BindTarget.Default<TransformFeedback>, TransformFeedback> implements TransformFeedback {

    public GlTransformFeedback(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteTransformFeedbacks);
    }

    @Override
    public TransformFeedbackDrawing drawing(DrawMode mode) {
        return new GlTransformFeedbackDrawing(this, mode);
    }

    @Override
    public TransformFeedback begin(PrimitiveMode primitiveMode) {
        assertBinding();
        GLX.glBeginTransformFeedback(primitiveMode.value());
        GLX.checkError();
        return this;
    }

    @Override
    public TransformFeedback end() {
        assertBinding();
        GLX.glEndTransformFeedback();
        GLX.checkError();
        return this;
    }

    @Override
    public TransformFeedback pause() {
        assertBinding();
        GLX.glPauseTransformFeedback();
        GLX.checkError();
        return this;
    }

    @Override
    public TransformFeedback resume() {
        assertBinding();
        GLX.glResumeTransformFeedback();
        GLX.checkError();
        return this;
    }

    @Override
    public boolean isActive() {
        return GLX.glGetTransformFeedbacki(intHandle(), GLX.GL_TRANSFORM_FEEDBACK_ACTIVE) == GLX.GL_TRUE;
    }

    @Override
    public boolean isPaused() {
        return GLX.glGetTransformFeedbacki(intHandle(), GLX.GL_TRANSFORM_FEEDBACK_PAUSED) == GLX.GL_TRUE;
    }

    @Override
    public TransformFeedback bindBuffer(int bindingPoint, Buffer buffer) {
        GLX.glTransformFeedbackBufferBase(intHandle(), bindingPoint, buffer.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public TransformFeedback bindBufferRange(int bindingPoint, Buffer buffer, long offset, long size) {
        GLX.glTransformFeedbackBufferRange(intHandle(), bindingPoint, buffer.intHandle(), offset, size);
        GLX.checkError();
        return this;
    }

    @Override
    public Buffer bindingBuffer(int bindingPoint) {
        var handle = GLX.glGetTransformFeedbacki(intHandle(), GLX.GL_TRANSFORM_FEEDBACK_BUFFER_BINDING, bindingPoint);
        return handle > 0 ? GlBuffer.get(handle) : null;
    }

    @Override
    public long bidingOffset(int bindingPoint) {
        return GLX.glGetTransformFeedbacki64(intHandle(), GLX.GL_TRANSFORM_FEEDBACK_BUFFER_START, bindingPoint);
    }

    @Override
    public long bindingSize(int bindingPoint) {
        return GLX.glGetTransformFeedbacki64(intHandle(), GLX.GL_TRANSFORM_FEEDBACK_BUFFER_SIZE, bindingPoint);
    }
}
