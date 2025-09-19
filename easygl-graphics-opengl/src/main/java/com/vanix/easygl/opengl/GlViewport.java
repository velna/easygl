package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.commons.primitives.Rectanglef;
import com.vanix.easygl.graphics.Viewport;
import org.joml.Vector2d;
import org.joml.Vector2i;
import org.lwjgl.system.MemoryStack;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GlViewport extends SimpleIntEnum implements Viewport {

    public GlViewport(int value) {
        super(value);
    }

    @Override
    public Viewport setDepthRange(double near, double far) {
        GLX.glDepthRangeIndexed(value, near, far);
        return this;
    }

    @Override
    public Vector2d getDepthRange() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            DoubleBuffer buffer = stack.mallocDouble(2);
            GLX.glGetDoublei_v(GLX.GL_DEPTH_RANGE, value, buffer);
            return new Vector2d().set(buffer);
        }
    }

    @Override
    public Vector2i getDepthRangeMapped() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer buffer = stack.mallocInt(2);
            GLX.glGetIntegeri_v(GLX.GL_DEPTH_RANGE, value, buffer);
            return new Vector2i().set(buffer);
        }
    }

    @Override
    public Viewport setDepthRangeMulti(double[] values) {
        GLX.glDepthRangeArrayv(value, values);
        return this;
    }

    @Override
    public Viewport setDepthRangeMulti(DoubleBuffer values) {
        GLX.glDepthRangeArrayv(value, values);
        return this;
    }

    @Override
    public Viewport set(float x, float y, float width, float height) {
        GLX.glViewportIndexedf(value, x, y, width, height);
        GLX.checkError();
        return this;
    }

    @Override
    public Viewport setMulti(float[] values) {
        GLX.glViewportArrayv(value, values);
        GLX.checkError();
        return this;
    }

    @Override
    public Viewport setMulti(FloatBuffer values) {
        GLX.glViewportArrayv(value, values);
        GLX.checkError();
        return this;
    }

    @Override
    public Rectanglef<?> get() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(4);
            GLX.glGetFloati_v(GLX.GL_VIEWPORT, value, buffer);
            return Rectanglef.of().set(buffer);
        }
    }

}
