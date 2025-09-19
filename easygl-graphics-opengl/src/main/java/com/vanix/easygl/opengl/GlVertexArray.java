package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.graphics.Buffer;
import com.vanix.easygl.graphics.DrawMode;
import com.vanix.easygl.graphics.VertexArray;
import com.vanix.easygl.graphics.VertexAttribute;
import com.vanix.easygl.graphics.draw.ArraysDrawing;
import com.vanix.easygl.graphics.draw.ElementsDrawing;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.function.IntConsumer;

public class GlVertexArray extends AbstractBindable<BindTarget.Default<VertexArray>, VertexArray> implements VertexArray {

    private final VertexAttribute[] attributes = new VertexAttribute[MAX_ATTRIBUTES];
    private final BindingPoint[] bindingPoints = new BindingPoint[MAX_BINDING_POINTS];

    GlVertexArray() {
        this(GLX.glGenVertexArrays());
    }

    GlVertexArray(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteVertexArrays);
    }

    @Override
    public VertexAttribute attribute(int index) {
        if (index < 0 || index > attributes.length) {
            throw new IllegalArgumentException("Vertex attribute index must be range from 0 to " + attributes.length);
        }
        var ret = attributes[index];
        if (ret == null) {
            ret = new GlVertexAttribute(index, this);
            attributes[index] = ret;
        }
        return ret;
    }

    @Override
    public BindingPoint bidingPoint(int bindingIndex) {
        if (bindingIndex < 0 || bindingIndex > bindingPoints.length) {
            throw new IllegalArgumentException("Vertex binding index must be range from 0 to " + bindingPoints.length);
        }
        var ret = bindingPoints[bindingIndex];
        if (ret == null) {
            ret = new GlBindingPoint(bindingIndex);
            bindingPoints[bindingIndex] = ret;
        }
        return ret;
    }

    @Override
    public VertexArray bindBuffers(int firstBindingIndex, Iterable<Buffer> bufferIterable, long[] offsets, int[] strides) {
        assertBinding();
        int[] handles;
        if (bufferIterable instanceof HandleArray<Buffer> handleArray) {
            handles = handleArray.getHandles();
        } else {
            handles = new int[offsets.length];
            int i = 0;
            for (Buffer buffer : bufferIterable) {
                handles[i++] = buffer.intHandle();
            }
        }
        try (MemoryStack memoryStack = MemoryStack.stackPush()) {
            var pointerBuffer = memoryStack.mallocPointer(offsets.length);
            pointerBuffer.put(offsets);
            GLX.glBindVertexBuffers(firstBindingIndex, handles, pointerBuffer.clear(), strides);
            GLX.checkError();
        }
        return this;
    }

    @Override
    public VertexArray bindBuffers(int firstBindingIndex, Buffer[] buffers, long[] offsets, int[] strides) {
        assertBinding();
        int[] handles = new int[buffers.length];
        for (int i = 0; i < buffers.length; i++) {
            handles[i] = buffers[i].intHandle();
        }
        try (MemoryStack memoryStack = MemoryStack.stackPush()) {
            var pointerBuffer = memoryStack.mallocPointer(offsets.length);
            pointerBuffer.put(offsets);
            GLX.glBindVertexBuffers(firstBindingIndex, handles, pointerBuffer.clear(), strides);
            GLX.checkError();
        }
        return this;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public ArraysDrawing.Builder drawingArrays(DrawMode mode) {
        return new GlVertexArrayDrawing(this, mode);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public ArraysDrawing.Builder drawingArrays(DrawMode mode, int first, int count) {
        return new GlVertexArrayDrawing(this, mode, first, count);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public ElementsDrawing.Builder drawingElements(DrawMode mode, Buffer ebo) {
        return new GlVertexArrayDrawing(this, mode, ebo);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public ElementsDrawing.Builder drawingElements(DrawMode mode, DataType dataType, int[] indices) {
        return new GlVertexArrayDrawing(this, mode, dataType, indices);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public ElementsDrawing.Builder drawingElements(DrawMode mode, DataType dataType, IntBuffer indices) {
        return new GlVertexArrayDrawing(this, mode, dataType, indices);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public ElementsDrawing.Builder drawingElements(DrawMode mode, DataType dataType, ByteBuffer indices) {
        return new GlVertexArrayDrawing(this, mode, dataType, indices);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public ElementsDrawing.Builder drawingElements(DrawMode mode, DataType dataType, IntBuffer[] indices) {
        return new GlVertexArrayDrawing(this, mode, dataType, indices);
    }

    class GlBindingPoint extends SimpleIntEnum implements BindingPoint {
        public GlBindingPoint(int value) {
            super(value);
        }

        @Override
        public BindingPoint bind(Buffer buffer, int offset, int stride) {
            assertBinding();
            GLX.glBindVertexBuffer(value, buffer.intHandle(), offset, stride);
            GLX.checkError();
            return this;
        }

        @Override
        public BindingPoint setDivisor(int divisor) {
            assertBinding();
            GLX.glVertexBindingDivisor(value, divisor);
            GLX.checkError();
            return this;
        }

        @Override
        public int getDivisor() {
            assertBinding();
            return GLX.glGetIntegeri(GLX.GL_VERTEX_BINDING_DIVISOR, value);
        }

        @Override
        public int getOffset() {
            assertBinding();
            return GLX.glGetIntegeri(GLX.GL_VERTEX_BINDING_OFFSET, value);
        }

        @Override
        public int getStride() {
            assertBinding();
            return GLX.glGetIntegeri(GLX.GL_VERTEX_BINDING_STRIDE, value);
        }

        @Override
        public Buffer getBuffer() {
            assertBinding();
            int buffer = GLX.glGetIntegeri(GLX.GL_VERTEX_BINDING_BUFFER, value);
            return buffer > 0 ? GlBuffer.get(buffer) : null;
        }

    }
}
