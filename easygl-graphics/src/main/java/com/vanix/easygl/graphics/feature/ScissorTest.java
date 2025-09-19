package com.vanix.easygl.graphics.feature;

import com.vanix.easygl.commons.primitives.Rectanglei;
import com.vanix.easygl.core.IndexedFeature;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.graphics.Version;
import com.vanix.easygl.graphics.Graphics;

import java.nio.IntBuffer;

public interface ScissorTest extends IndexedFeature<ScissorTest, Graphics>, GraphicsFeature<ScissorTest> {

    default ScissorTest setBox(int[] box) {
        return setBox(box[0], box[1], box[2], box[3]);
    }

    default ScissorTest setBox(IntBuffer box) {
        return setBox(box.get(), box.get(), box.get(), box.get());
    }

    default ScissorTest setBox(Rectanglei<?> box) {
        return setBox(box.getX(), box.getY(), box.getWidth(), box.getHeight());
    }

    ScissorTest setBox(int x, int y, int width, int height);

    @Support(since = Version.GL41)
    ScissorTest setBoxes(int first, int[] boxes);

    @Support(since = Version.GL41)
    ScissorTest setBoxes(int first, IntBuffer boxes);

    @Support(since = Version.GL41)
    ScissorTest setBoxes(int first, Rectanglei<?>... boxes);

    @Support(since = Version.GL41)
    ScissorTest setBoxAt(int index, int[] box);

    @Support(since = Version.GL41)
    ScissorTest setBoxAt(int index, IntBuffer box);

    @Support(since = Version.GL41)
    ScissorTest setBoxAt(int index, Rectanglei<?> box);

    @Support(since = Version.GL41)
    ScissorTest setBoxAt(int index, int x, int y, int width, int height);

    @Support(since = Version.GL41)
    Rectanglei<?> getBoxAt(int index);

    Rectanglei<?> getBox();
}
