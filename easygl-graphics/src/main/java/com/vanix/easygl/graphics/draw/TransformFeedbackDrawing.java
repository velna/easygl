package com.vanix.easygl.graphics.draw;

import com.vanix.easygl.graphics.TransformFeedback;

public interface TransformFeedbackDrawing extends Drawing<TransformFeedback> {

    interface Builder<D extends TransformFeedbackDrawing, I extends Instanced, SI extends StreamInstanced> extends Drawing.Builder<TransformFeedback, D> {
        Drawing.Builder<TransformFeedback, I> instanced(int instanceCount);

        Stream.Builder<SI> stream(int stream);
    }

    interface Instanced extends TransformFeedbackDrawing, InstancedDrawing<TransformFeedback> {}

    interface Stream extends TransformFeedbackDrawing, Drawing<TransformFeedback> {
        int stream();

        interface Builder<SI extends StreamInstanced> extends Drawing.Builder<TransformFeedback, SI> {
            Drawing.Builder<TransformFeedback, SI> instanced(int instanceCount);
        }
    }

    interface StreamInstanced extends Stream, Instanced {}

}
