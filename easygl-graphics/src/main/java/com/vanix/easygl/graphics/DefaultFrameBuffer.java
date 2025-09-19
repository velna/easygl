package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.commons.primitives.Rectanglei;
import com.vanix.easygl.core.Support;

public interface DefaultFrameBuffer extends BaseFrameBuffer<DefaultFrameBuffer> {

    DefaultFrameBuffer selectDrawBuffer(DefaultFrameBuffer.DrawBuffer drawBuffer);

    @Support(since = Version.GL43)
    DefaultFrameBuffer invalidate(Target<FrameBuffer> target, Invalidatable attachment);

    @Support(since = Version.GL43)
    DefaultFrameBuffer invalidate(Target<FrameBuffer> target, int x, int y, int width, int height, Invalidatable attachment);

    default DefaultFrameBuffer invalidate(Target<FrameBuffer> target, Rectanglei<?> rectangle, Invalidatable attachment) {
        return invalidate(target, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), attachment);
    }

    sealed interface DrawBuffer extends IntEnum permits FrameInnerBuffer.Constants {
        DrawBuffer None = FrameInnerBuffer.Constants.None;
        DrawBuffer FrontLeft = FrameInnerBuffer.Constants.FrontLeft;
        DrawBuffer FrontRight = FrameInnerBuffer.Constants.FrontRight;
        DrawBuffer BackLeft = FrameInnerBuffer.Constants.BackLeft;
        DrawBuffer BackRight = FrameInnerBuffer.Constants.BackRight;
        DrawBuffer Front = FrameInnerBuffer.Constants.Front;
        DrawBuffer Back = FrameInnerBuffer.Constants.Back;
        DrawBuffer Left = FrameInnerBuffer.Constants.Left;
        DrawBuffer Right = FrameInnerBuffer.Constants.Right;
        DrawBuffer FrontAndBack = FrameInnerBuffer.Constants.FrontAndBack;
    }

    sealed interface Invalidatable extends IntEnum permits FrameInnerBuffer.Constants {
        Invalidatable Color = FrameInnerBuffer.Constants.Color;
        Invalidatable Depth = FrameInnerBuffer.Constants.Depth;
        Invalidatable Stencil = FrameInnerBuffer.Constants.Stencil;
        Invalidatable FrontLeft = FrameInnerBuffer.Constants.FrontLeft;
        Invalidatable FrontRight = FrameInnerBuffer.Constants.FrontRight;
        Invalidatable BackLeft = FrameInnerBuffer.Constants.BackLeft;
        Invalidatable BackRight = FrameInnerBuffer.Constants.BackRight;
    }
}
