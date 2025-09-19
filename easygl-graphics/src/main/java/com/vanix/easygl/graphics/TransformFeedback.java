package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.graphics.draw.TransformFeedbackDrawing;

@Support(since = Version.GL40)
public interface TransformFeedback extends Bindable<BindTarget.Default<TransformFeedback>, TransformFeedback> {
    BindTarget.Default<TransformFeedback> Target = new BindTarget.Default<>(
            MetaSystem.Graphics.queryInt("TRANSFORM_FEEDBACK"), "TransformFeedback", MetaHolder.TransformFeedback);

    TransformFeedbackDrawing drawing(DrawMode mode);

    TransformFeedback begin(PrimitiveMode primitiveMode);

    TransformFeedback end();

    TransformFeedback pause();

    TransformFeedback resume();

    @Support(since = Version.GL45)
    boolean isActive();

    @Support(since = Version.GL45)
    boolean isPaused();

    @Support(since = Version.GL45)
    TransformFeedback bindBuffer(int bindingPoint, Buffer buffer);

    @Support(since = Version.GL45)
    TransformFeedback bindBufferRange(int bindingPoint, Buffer buffer, long offset, long size);

    @Support(since = Version.GL45)
    Buffer bindingBuffer(int bindingPoint);

    @Support(since = Version.GL45)
    long bidingOffset(int bindingPoint);

    @Support(since = Version.GL45)
    long bindingSize(int bindingPoint);

    enum PrimitiveMode implements IntEnum {
        Points("POINTS"),
        Lines("LINES"),
        Triangles("TRIANGLES");
        private final int value;

        PrimitiveMode(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

}
