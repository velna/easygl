package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.commons.bufferio.BufferIO;
import com.vanix.easygl.commons.bufferio.StructBufferIO;
import com.vanix.easygl.commons.util.SerializableFunction;
import com.vanix.easygl.commons.util.TypeReferenceBean;
import com.vanix.easygl.core.AbstractMultiTargetBindable;
import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.InternalPixelFormat;
import com.vanix.easygl.graphics.PixelFormat;
import com.vanix.easygl.graphics.Access;
import com.vanix.easygl.graphics.MapAccessBits;
import com.vanix.easygl.graphics.ProgramResource;
import com.vanix.easygl.graphics.program.UniformBlock;
import lombok.EqualsAndHashCode;
import org.eclipse.collections.api.factory.primitive.IntObjectMaps;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.*;
import java.util.function.IntConsumer;

public class GlBuffer extends AbstractMultiTargetBindable<com.vanix.easygl.graphics.Buffer.Target, com.vanix.easygl.graphics.Buffer> implements com.vanix.easygl.graphics.Buffer {
    private static final MutableIntObjectMap<com.vanix.easygl.graphics.Buffer> BUFFERS = IntObjectMaps.mutable.of();
    private final DataType dataType;
    private Long sizeInBytes;

    protected GlBuffer(int handle, DataType dataType) {
        super(handle, (IntConsumer) GLX::glDeleteBuffers);
        this.dataType = dataType;
        BUFFERS.put(handle, this);
    }

    protected GlBuffer(DataType dataType) {
        this(GLX.glGenBuffers(), dataType);
    }

    static com.vanix.easygl.graphics.Buffer get(int handle) {
        var buffer = BUFFERS.get(handle);
        if (buffer == null && GLX.glIsBuffer(handle)) {
            buffer = new GlBuffer(handle, DataType.UnsignedByte);
            BUFFERS.put(handle, buffer);
        }
        return buffer;
    }

    @Override
    public void close() {
        super.close();
        BUFFERS.remove(intHandle());
    }

    @Override
    public DataType dataType() {
        return dataType;
    }

    private interface ReallocFunction<T> {
        void accept(int target, T data, int usage);
    }

    private <T> com.vanix.easygl.graphics.Buffer realloc(ReallocFunction<T> reallocFn, DataUsage usage, T data, long dataBytes) {
        assertBinding();
        reallocFn.accept(target.value(), data, usage.value());
        GLX.checkError();
        sizeInBytes = dataBytes;
        return this;
    }

    @Override
    public com.vanix.easygl.graphics.Buffer realloc(DataUsage usage, long size) {
        GLX.glBufferData(target.value(), size, usage.value());
        sizeInBytes = size;
        return this;
    }

    @Override
    public com.vanix.easygl.graphics.Buffer realloc(DataUsage usage, DoubleBuffer data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public com.vanix.easygl.graphics.Buffer realloc(DataUsage usage, FloatBuffer data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public com.vanix.easygl.graphics.Buffer realloc(DataUsage usage, IntBuffer data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public com.vanix.easygl.graphics.Buffer realloc(DataUsage usage, ShortBuffer data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public com.vanix.easygl.graphics.Buffer realloc(DataUsage usage, ByteBuffer data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public com.vanix.easygl.graphics.Buffer realloc(DataUsage usage, double[] data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public com.vanix.easygl.graphics.Buffer realloc(DataUsage usage, float[] data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public com.vanix.easygl.graphics.Buffer realloc(DataUsage usage, int[] data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public com.vanix.easygl.graphics.Buffer realloc(DataUsage usage, short[] data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public <T> com.vanix.easygl.graphics.Buffer realloc(DataUsage usage, T bean, BufferIO<T> bufferIO) {
        var buffer = MemoryUtil.memAlloc(bufferIO.size());
        bufferIO.write(bean, buffer);
        realloc(GLX::glBufferData, usage, buffer.clear(), bufferIO.size());
        MemoryUtil.memFree(buffer);
        return this;
    }

    private interface SubDataFunction<T> {
        void accept(int target, long offset, T data);
    }

    private <T> com.vanix.easygl.graphics.Buffer setSubData(SubDataFunction<T> subDataFunction, long offset, T data) {
        assertBinding();
        subDataFunction.accept(target().value(), offset, data);
        GLX.checkError();
        return this;
    }

    @Override
    public com.vanix.easygl.graphics.Buffer setSubData(long offset, DoubleBuffer data) {
        return setSubData(GLX::glBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer setSubData(long offset, FloatBuffer data) {
        return setSubData(GLX::glBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer setSubData(long offset, IntBuffer data) {
        return setSubData(GLX::glBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer setSubData(long offset, ShortBuffer data) {
        return setSubData(GLX::glBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer setSubData(long offset, ByteBuffer data) {
        return setSubData(GLX::glBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer setSubData(long offset, double[] data) {
        return setSubData(GLX::glBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer setSubData(long offset, float[] data) {
        return setSubData(GLX::glBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer setSubData(long offset, int[] data) {
        return setSubData(GLX::glBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer setSubData(long offset, short[] data) {
        return setSubData(GLX::glBufferSubData, offset, data);
    }

    @Override
    public <T> com.vanix.easygl.graphics.Buffer setSubData(long offset, T bean, BufferIO<T> bufferIO) {
        bufferIO.write(bean, mapRange(offset, bufferIO.size(), MapAccessBits.Write));
        unmap();
        return this;
    }

    private interface StorageFunction<T> {
        void accept(int target, T data, int flags);
    }

    private <T> com.vanix.easygl.graphics.Buffer storage(StorageFunction<T> storageFn, T data, long dataBytes, int flags) {
        assertBinding();
        storageFn.accept(target.value(), data, flags);
        GLX.checkError();
        sizeInBytes = dataBytes;
        return this;
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(DoubleBuffer data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(FloatBuffer data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(IntBuffer data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(ShortBuffer data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(ByteBuffer data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(double[] data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(float[] data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(int[] data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(short[] data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(DoubleBuffer data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(FloatBuffer data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(IntBuffer data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(ShortBuffer data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(ByteBuffer data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(double[] data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(float[] data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(int[] data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public com.vanix.easygl.graphics.Buffer storage(short[] data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    private interface ClearDataFunction<T> {
        void accept(int target, int internalFormat, int format, int type, T data);
    }

    private <T> com.vanix.easygl.graphics.Buffer clearData(ClearDataFunction<T> clearFunction, InternalPixelFormat internalFormat, PixelFormat format, DataType type, T data) {
        assertBinding();
        clearFunction.accept(target.value(), internalFormat.value(), format.value(), type.value(), data);
        GLX.checkError();
        return this;
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, (ByteBuffer) null);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, ByteBuffer data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, short[] data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, ShortBuffer data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, int[] data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, IntBuffer data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, float[] data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, FloatBuffer data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    private interface ClearSubDataFunction<T> {
        void accept(int target, int internalFormat, long offset, long size, int format, int type, T data);
    }

    private <T> com.vanix.easygl.graphics.Buffer clearSubData(ClearSubDataFunction<T> clearFunction, InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, T data) {
        assertBinding();
        clearFunction.accept(target.value(), internalFormat.value(), offset, size, format.value(), type.value(), data);
        GLX.checkError();
        return this;
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, (ByteBuffer) null);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, ByteBuffer data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, short[] data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, ShortBuffer data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, int[] data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, IntBuffer data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, float[] data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, FloatBuffer data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public ByteBuffer mapRange(long offset, long size, MapAccessBits access) {
        assertBinding();
        var ret = GLX.glMapBufferRange(target.value(), offset, size, access.value());
        GLX.checkError();
        return ret;
    }

    @Override
    public ByteBuffer mapRange(long offset, long size, BitSet<MapAccessBits> access) {
        assertBinding();
        var ret = GLX.glMapBufferRange(target.value(), offset, size, access.value());
        GLX.checkError();
        return ret;
    }

    @Override
    public ByteBuffer map(Access access) {
        assertBinding();
        var ret = GLX.glMapBuffer(target.value(), access.value());
        GLX.checkError();
        return ret;
    }

    @Override
    public com.vanix.easygl.graphics.Buffer flushMappedRange(long offset, long size) {
        assertBinding();
        GLX.glFlushMappedBufferRange(target.value(), offset, size);
        GLX.checkError();
        return this;
    }

    @Override
    public boolean unmap() {
        assertBinding();
        boolean ret = GLX.glUnmapBuffer(target.value());
        GLX.checkError();
        return ret;
    }

    @Override
    public com.vanix.easygl.graphics.Buffer invalidateSubData(long offset, long size) {
        GLX.glInvalidateBufferSubData(intHandle(), offset, size);
        GLX.checkError();
        return this;
    }

    @Override
    public com.vanix.easygl.graphics.Buffer invalidateData() {
        GLX.glInvalidateBufferData(intHandle());
        GLX.checkError();
        return this;
    }

    private interface GetSubDataFunction<T> {
        void accept(int target, long offset, T data);
    }

    private <T> com.vanix.easygl.graphics.Buffer getSubData(GetSubDataFunction<T> getSubDataFunction, long offset, T data) {
        assertBinding();
        getSubDataFunction.accept(target.value(), offset, data);
        GLX.checkError();
        return this;
    }

    @Override
    public com.vanix.easygl.graphics.Buffer getSubData(long offset, short[] data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer getSubData(long offset, ShortBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer getSubData(long offset, int[] data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer getSubData(long offset, IntBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer getSubData(long offset, float[] data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer getSubData(long offset, FloatBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer getSubData(long offset, long[] data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer getSubData(long offset, LongBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer getSubData(long offset, double[] data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer getSubData(long offset, DoubleBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public com.vanix.easygl.graphics.Buffer getSubData(long offset, ByteBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public <T> T getSubData(long offset, T bean, BufferIO<T> bufferIO) {
        T object = bufferIO.read(bean, mapRange(offset, bufferIO.size(), MapAccessBits.Read), null);
        unmap();
        return object;
    }

    @Override
    public com.vanix.easygl.graphics.Buffer copySubData(long readOffset, com.vanix.easygl.graphics.Buffer dstBuffer, long writeOffset, long size) {
        assertBinding();
        GLX.glCopyBufferSubData(target.value(), dstBuffer.target().value(), readOffset, writeOffset, size);
        GLX.checkError();
        return this;
    }

    @Override
    public long size() {
        if (sizeInBytes == null) {
            assertBinding();
            sizeInBytes = GLX.glGetBufferParameteri64(target.value(), GLX.GL_BUFFER_SIZE);
        }
        return sizeInBytes;
    }

    @Override
    public Access getMapAccess() {
        assertBinding();
        return Cache.Access.valueOf(GLX.glGetBufferParameteri(target.value(), GLX.GL_BUFFER_ACCESS));
    }

    @Override
    public BitSet<MapAccessBits> getMapAccessBits() {
        assertBinding();
        return BitSet.<MapAccessBits>of(MapAccessBits::value).set(GLX.glGetBufferParameteri(target.value(), GLX.GL_BUFFER_ACCESS_FLAGS));
    }

    @Override
    public boolean isImmutable() {
        return GLX.glGetBufferParameteri(target.value(), GLX.GL_BUFFER_IMMUTABLE_STORAGE) == GLX.GL_TRUE;
    }

    @Override
    public boolean isMapped() {
        return GLX.glGetBufferParameteri(target.value(), GLX.GL_BUFFER_MAPPED) == GLX.GL_TRUE;
    }

    @Override
    public long getMappedSize() {
        return GLX.glGetBufferParameteri64(target.value(), GLX.GL_BUFFER_MAP_LENGTH);
    }

    @Override
    public long getMappedOffset() {
        return GLX.glGetBufferParameteri64(target.value(), GLX.GL_BUFFER_MAP_OFFSET);
    }

    @Override
    public BitSet<StorageBits> getStorageBits() {
        return BitSet.<StorageBits>of(StorageBits::value).set(GLX.glGetBufferParameteri(target.value(), GLX.GL_BUFFER_STORAGE_FLAGS));
    }

    @Override
    public DataUsage getDataUsage() {
        return Cache.BufferDataUsage.valueOf(GLX.glGetBufferParameteri(target.value(), GLX.GL_BUFFER_USAGE));
    }

    @Override
    public ByteBuffer getMappedBuffer() {
        assertBinding();
        try (var stack = MemoryStack.stackPush()) {
            var pointerBuffer = stack.mallocPointer(1);
            GLX.glGetBufferPointerv(target.value(), GLX.GL_BUFFER_MAP_POINTER, pointerBuffer);
            long address = pointerBuffer.get(0);
            return address == MemoryUtil.NULL ? null : MemoryUtil.memByteBuffer(address, (int) size());
        }
    }

    @Override
    public BindingPoint bindAt(int bindingPoint, long offset, long size) {
        assertBinding();
        GLX.glBindBufferRange(target.value(), bindingPoint, intHandle(), offset, size);
        GLX.checkError();
        return new GlBindingPoint(bindingPoint, this, offset, size);
    }

    @Override
    public BindingPoint bindAt(int bindingPoint) {
        assertBinding();
        GLX.glBindBufferBase(target.value(), bindingPoint, intHandle());
        GLX.checkError();
        return new GlBindingPoint(bindingPoint, this, 0, size());
    }

    @Override
    public <T> Mapping<T> createMapping(TypeReferenceBean<T> typeReferenceBean, long offset) {
        return new GlMapping<>(this, typeReferenceBean, offset);
    }

    @Override
    public <T> Mapping<T> createMapping(T bean, long offset) {
        return new GlMapping<>(this, bean, offset);
    }

    @EqualsAndHashCode(callSuper = true)
    static class GlBindingPoint extends SimpleIntEnum implements BindingPoint {
        private final com.vanix.easygl.graphics.Buffer buffer;
        private final Target target;
        private final long offset;
        private final long size;

        public GlBindingPoint(int value, com.vanix.easygl.graphics.Buffer buffer, long offset, long size) {
            super(value);
            this.buffer = buffer;
            this.target = buffer.target();
            this.offset = offset;
            this.size = size;
        }

        @Override
        public <T, B extends ProgramResource.BufferBinding<B> & ProgramResource.BufferDataSize<B>> Mapping<T> createMapping(T bean, B bufferBinding) {
            return new GlMapping<>(this, bean, bufferBinding);
        }

        @Override
        public Target target() {
            return target;
        }

        @Override
        public com.vanix.easygl.graphics.Buffer buffer() {
            return buffer;
        }

        @Override
        public long offset() {
            return offset;
        }

        @Override
        public long size() {
            return size;
        }

        @Override
        public String toString() {
            return "BindingPoint{" +
                    "target=" + target + ", value=" + value() +
                    '}';
        }
    }

    static class GlMapping<T> implements Mapping<T> {
        private final BindingPoint bindingPoint;
        private final T bean;
        private final ByteBuffer storage;
        private final BufferIO<T> bufferIO;

        public <B extends ProgramResource.BufferBinding<B> & ProgramResource.BufferDataSize<B>> GlMapping(
                BindingPoint bindingPoint, T bean, B bufferBinding) {
            this.bindingPoint = bindingPoint;
            this.bean = bean;
            storage = MemoryUtil.memCalloc(bufferBinding.getBufferDataSize());
            if (bufferBinding instanceof UniformBlock uniformBlock) {
                this.bufferIO = uniformBlock.createBufferIO(bean, storage);
            } else {
                throw new UnsupportedOperationException();
            }
        }

        public GlMapping(com.vanix.easygl.graphics.Buffer buffer, TypeReferenceBean<T> typeReferenceBean, long offset) {
            this(buffer, typeReferenceBean.getBean(), BufferIO.ofType(typeReferenceBean), offset);
        }

        public GlMapping(com.vanix.easygl.graphics.Buffer buffer, T bean, long offset) {
            this(buffer, bean, BufferIO.ofBean(bean), offset);
        }

        private GlMapping(com.vanix.easygl.graphics.Buffer buffer, T bean, BufferIO<T> bufferIO, long offset) {
            this.bean = bean;
            this.bufferIO = bufferIO;
            storage = MemoryUtil.memCalloc(bufferIO.size());
            this.bindingPoint = new GlBindingPoint(-1, buffer, offset, storage.capacity());
        }

        @Override
        public T getBean() {
            return bean;
        }

        @Override
        public int size() {
            return storage.capacity();
        }

        @Override
        public ByteBuffer storage() {
            return storage;
        }

        @Override
        public BindingPoint getBindingPoint() {
            return bindingPoint.value() == -1 ? null : bindingPoint;
        }

        @Override
        public void flush() {
            bufferIO.write(bean, storage.clear());
            bindingPoint.buffer().setSubData(bindingPoint.offset(), storage.clear());
        }

        @Override
        public <F> void flush(SerializableFunction<T, F> fieldGetter) {
            if (bufferIO instanceof StructBufferIO<T> structBufferIO) {
                var fieldBufferIO = structBufferIO.getFieldBufferIO(fieldGetter);
                fieldBufferIO.write(fieldGetter.apply(bean), storage);
                int dataOffset = fieldBufferIO.getOffset();
                int dataSize = fieldBufferIO.size();
                bindingPoint.buffer().setSubData(bindingPoint.offset() + dataOffset,
                        storage.clear().position(dataOffset).limit(dataOffset + dataSize));
            } else {
                throw new UnsupportedOperationException("Not a plain pojo: " + bean.getClass());
            }
        }

        @Override
        public void load() {
            bindingPoint.buffer().getSubData(bindingPoint.offset(), storage.clear());
            bufferIO.read(bean, storage, null);
        }

        @Override
        public <F> void load(SerializableFunction<T, F> fieldGetter) {
            if (bufferIO instanceof StructBufferIO<T> structBufferIO) {
                var fieldBufferIO = structBufferIO.getFieldBufferIO(fieldGetter);
                int dataOffset = fieldBufferIO.getOffset();
                int dataSize = fieldBufferIO.size();
                bindingPoint.buffer().getSubData(bindingPoint.offset() + dataOffset,
                        storage.clear().position(dataOffset).limit(dataOffset + dataSize));
                fieldBufferIO.read(fieldGetter.apply(bean), storage, null);
            } else {
                throw new UnsupportedOperationException("Not a plain pojo: " + bean.getClass());
            }
        }

        @Override
        public void close() {
            MemoryUtil.memFree(storage);
        }

    }

}
