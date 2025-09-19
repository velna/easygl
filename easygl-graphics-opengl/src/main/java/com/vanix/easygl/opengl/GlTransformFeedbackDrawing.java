package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.DrawMode;
import com.vanix.easygl.graphics.TransformFeedback;
import com.vanix.easygl.graphics.draw.Drawing;
import com.vanix.easygl.graphics.draw.TransformFeedbackDrawing;

import java.util.function.Consumer;

public class GlTransformFeedbackDrawing implements
        TransformFeedbackDrawing,
        TransformFeedbackDrawing.Instanced,
        TransformFeedbackDrawing.Stream,
        TransformFeedbackDrawing.StreamInstanced,
        TransformFeedbackDrawing.Builder<GlTransformFeedbackDrawing, GlTransformFeedbackDrawing, GlTransformFeedbackDrawing>,
        TransformFeedbackDrawing.Stream.Builder<GlTransformFeedbackDrawing> {
    @SuppressWarnings("unchecked")
    private static final Consumer<GlTransformFeedbackDrawing>[] FUNCTIONS = new Consumer[4];

    static {
        FUNCTIONS[0] = d -> GLX.glDrawTransformFeedback(d.mode.value(), d.transformFeedback.intHandle());
        FUNCTIONS[1] = d -> GLX.glDrawTransformFeedbackInstanced(d.mode.value(), d.transformFeedback.intHandle(), d.instanceCount);
        FUNCTIONS[2] = d -> GLX.glDrawTransformFeedbackStream(d.mode.value(), d.transformFeedback.intHandle(), d.stream);
        FUNCTIONS[3] = d -> GLX.glDrawTransformFeedbackStreamInstanced(d.mode.value(), d.transformFeedback.intHandle(), d.stream, d.instanceCount);
    }

    private final TransformFeedback transformFeedback;
    private final DrawMode mode;
    private int stream;
    private int instanceCount;
    private int functionIndex;

    public GlTransformFeedbackDrawing(TransformFeedback transformFeedback, DrawMode mode) {
        this.transformFeedback = transformFeedback;
        this.mode = mode;
    }

    @Override
    public void draw() {
        transformFeedback.bind();
        FUNCTIONS[functionIndex].accept(this);
        GLX.checkError();
    }

    @Override
    public TransformFeedback source() {
        return transformFeedback;
    }

    @Override
    public DrawMode mode() {
        return mode;
    }

    @Override
    public GlTransformFeedbackDrawing build() {
        return this;
    }

    @Override
    public Drawing.Builder<TransformFeedback, GlTransformFeedbackDrawing> instanced(int instanceCount) {
        this.instanceCount = instanceCount;
        if (functionIndex == 0 || functionIndex == 2) {
            functionIndex += 1;
        }
        return this;
    }

    @Override
    public Stream.Builder<GlTransformFeedbackDrawing> stream(int stream) {
        this.stream = stream;
        if (functionIndex == 0 || functionIndex == 1) {
            functionIndex += 2;
        }
        return this;
    }

    @Override
    public int instanceCount() {
        return instanceCount;
    }

    @Override
    public int stream() {
        return stream;
    }
}
