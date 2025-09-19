package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.commons.primitives.Rectanglef;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;
import org.joml.Vector2d;
import org.joml.Vector2i;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

@Support(since = Version.GL41)
public interface Viewport extends IntEnum {
    int MaxViewports = MetaSystem.Graphics.queryInt("GET.MAX_VIEWPORTS");

    Viewport setDepthRange(double near, double far);

    Vector2d getDepthRange();

    Vector2i getDepthRangeMapped();

    Viewport setDepthRangeMulti(double[] values);

    Viewport setDepthRangeMulti(DoubleBuffer values);

    Viewport set(float x, float y, float width, float height);

    default Viewport set(Rectanglef<?> rectangle) {
        return set(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    Viewport setMulti(float[] values);

    Viewport setMulti(FloatBuffer values);

    Rectanglef<?> get();

}
