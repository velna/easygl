package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.*;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.graphics.draw.ArraysDrawing;
import com.vanix.easygl.graphics.draw.ElementsDrawing;

import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

@Support(since = Version.GL30)
public interface VertexArray extends Bindable<BindTarget.Default<VertexArray>, VertexArray>, Handle {
    int MAX_BINDING_POINTS = MetaSystem.Graphics.queryInt("GET.MAX_VERTEX_ATTRIB_BINDINGS");
    int MAX_ATTRIBUTES = MetaSystem.Graphics.queryInt("GET.MAX_VERTEX_ATTRIBS");

    BindTarget.Default<VertexArray> Target = new BindTarget.Default<>("VertexArray", MetaHolder.VertexArray);

    default VertexAttribute enableAttributePointers(Number... layouts) {
        return attribute(0).enablePointers(layouts);
    }

    default VertexAttribute enableAttributeFormats(VertexArray.BindingPoint bindingPoint, Number... layouts) {
        return attribute(0).enableFormats(bindingPoint, layouts);
    }

    VertexAttribute attribute(int index);

    BindingPoint bidingPoint(int bindingIndex);

    VertexArray bindBuffers(int firstBindingIndex, Iterable<Buffer> bufferIterable, long[] offsets, int[] strides);

    VertexArray bindBuffers(int firstBindingIndex, Buffer[] buffers, long[] offsets, int[] strides);

    ArraysDrawing.Builder<ArraysDrawing> drawingArrays(DrawMode mode);

    default ArraysDrawing.Builder<ArraysDrawing> drawingArrays(DrawMode mode, int count) {
        return drawingArrays(mode, 0, count);
    }

    ArraysDrawing.Builder<ArraysDrawing> drawingArrays(DrawMode mode, int first, int count);

    ElementsDrawing.Builder<ElementsDrawing> drawingElements(DrawMode mode, Buffer ebo);

    ElementsDrawing.Builder<ElementsDrawing> drawingElements(DrawMode mode, DataType dataType, int[] indices);

    ElementsDrawing.Builder<ElementsDrawing> drawingElements(DrawMode mode, DataType dataType, IntBuffer indices);

    ElementsDrawing.Builder<ElementsDrawing> drawingElements(DrawMode mode, DataType dataType, ByteBuffer indices);

    ElementsDrawing.Builder<ElementsDrawing> drawingElements(DrawMode mode, DataType dataType, IntBuffer[] indices);

    static VertexArray of() {
        return MetaHolder.VertexArray.create();
    }

    static HandleArray<VertexArray> of(int n) {
        return MetaHolder.VertexArray.createArray(n);
    }

    @Support(since = Version.GL43)
    interface BindingPoint extends IntEnum {

        BindingPoint bind(Buffer buffer, int offset, int stride);

        BindingPoint setDivisor(int divisor);

        default int countOfStride() {
            var buffer = getBuffer();
            return buffer == null ? 0 : (int) (getBuffer().size() / getStride());
        }

        int getDivisor();

        int getOffset();

        int getStride();

        @Nullable
        Buffer getBuffer();
    }

}
