package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.commons.primitives.Rectanglei;
import com.vanix.easygl.commons.primitives.RectangleiImpl;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.feature.ScissorTest;

import java.nio.IntBuffer;

public class GlScissorTest extends GlFeature<ScissorTest> implements ScissorTest {
    public GlScissorTest(Graphics graphics) {
        super(GLX.GL_SCISSOR_TEST, graphics);
    }

    @Override
    public ScissorTest enableAt(int index) {
        GLX.glEnablei(GLX.GL_SCISSOR_TEST, index);
        GLX.checkError();
        return this;
    }

    @Override
    public ScissorTest disableAt(int index) {
        GLX.glDisablei(GLX.GL_SCISSOR_TEST, index);
        GLX.checkError();
        return this;
    }

    @Override
    public boolean isEnabledAt(int index) {
        return GLX.glIsEnabledi(GLX.GL_SCISSOR_TEST, index);
    }

    @Override
    public ScissorTest setBox(int x, int y, int width, int height) {
        GLX.glScissor(x, y, width, height);
        GLX.checkError();
        return this;
    }

    @Override
    public ScissorTest setBoxes(int first, int[] boxes) {
        GLX.glScissorArrayv(first, boxes);
        GLX.checkError();
        return this;
    }

    @Override
    public ScissorTest setBoxes(int first, IntBuffer boxes) {
        GLX.glScissorArrayv(first, boxes);
        GLX.checkError();
        return this;
    }

    @Override
    public ScissorTest setBoxes(int first, Rectanglei<?>... boxes) {
        IntBuffer buffer = IntBuffer.allocate(boxes.length << 2);
        for (var box : boxes) {
            buffer.put(box.getX()).put(box.getY()).put(box.getWidth()).put(box.getHeight());
        }
        return setBoxes(first, buffer);
    }

    @Override
    public ScissorTest setBoxAt(int index, int[] box) {
        GLX.glScissorIndexedv(index, box);
        GLX.checkError();
        return this;
    }

    @Override
    public ScissorTest setBoxAt(int index, IntBuffer box) {
        GLX.glScissorIndexedv(index, box);
        GLX.checkError();
        return this;
    }

    @Override
    public ScissorTest setBoxAt(int index, Rectanglei<?> box) {
        return setBoxAt(index, box.getX(), box.getY(), box.getWidth(), box.getHeight());
    }

    @Override
    public ScissorTest setBoxAt(int index, int x, int y, int width, int height) {
        GLX.glScissorIndexed(index, x, y, width, height);
        GLX.checkError();
        return this;
    }

    @Override
    public Rectanglei<?> getBoxAt(int index) {
        int[] box = new int[4];
        GLX.glGetIntegeri_v(GLX.GL_SCISSOR_BOX, index, box);
        GLX.checkError();
        return new RectangleiImpl().set(box);
    }

    @Override
    public Rectanglei<?> getBox() {
        int[] box = new int[4];
        GLX.glGetIntegerv(GLX.GL_SCISSOR_BOX, box);
        GLX.checkError();
        return new RectangleiImpl().set(box);
    }
}
