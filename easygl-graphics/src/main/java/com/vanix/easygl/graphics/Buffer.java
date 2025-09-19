package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.commons.bufferio.BufferIO;
import com.vanix.easygl.commons.util.SerializableFunction;
import com.vanix.easygl.commons.util.TypeReferenceBean;
import com.vanix.easygl.core.*;
import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.*;

public interface Buffer extends Handle, MultiTargetBindable<Buffer.Target, Buffer> {

    DataType dataType();

    default int count() {
        return (int) (size() / dataType().bytes());
    }

    //region Set buffer data
    //region Set buffer data
    Buffer realloc(DataUsage usage, long size);

    Buffer realloc(DataUsage usage, DoubleBuffer data);

    Buffer realloc(DataUsage usage, FloatBuffer data);

    Buffer realloc(DataUsage usage, IntBuffer data);

    Buffer realloc(DataUsage usage, ShortBuffer data);

    Buffer realloc(DataUsage usage, ByteBuffer data);

    Buffer realloc(DataUsage usage, double[] data);

    Buffer realloc(DataUsage usage, float[] data);

    Buffer realloc(DataUsage usage, int[] data);

    Buffer realloc(DataUsage usage, short[] data);

    <T> Buffer realloc(DataUsage usage, T bean, BufferIO<T> bufferIO);

    default <T> Buffer realloc(DataUsage usage, T bean) {
        return realloc(usage, bean, BufferIO.ofBean(bean));
    }

    default <T> Buffer realloc(DataUsage usage, TypeReferenceBean<T> typeReferenceBean) {
        return realloc(usage, typeReferenceBean.getBean(), BufferIO.ofType(typeReferenceBean));
    }
    //endregion

    //region Data storage
    @Support(since = Version.GL44)
    Buffer storage(DoubleBuffer data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(FloatBuffer data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(IntBuffer data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(ShortBuffer data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(ByteBuffer data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(double[] data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(float[] data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(int[] data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(short[] data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(DoubleBuffer data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(FloatBuffer data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(IntBuffer data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(ShortBuffer data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(ByteBuffer data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(double[] data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(float[] data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(int[] data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(short[] data, BitSet<StorageBits> flags);
    //endregion

    //region Set sub data
    Buffer setSubData(long offset, DoubleBuffer data);

    Buffer setSubData(long offset, FloatBuffer data);

    Buffer setSubData(long offset, IntBuffer data);

    Buffer setSubData(long offset, ShortBuffer data);

    Buffer setSubData(long offset, ByteBuffer data);

    Buffer setSubData(long offset, double[] data);

    Buffer setSubData(long offset, float[] data);

    Buffer setSubData(long offset, int[] data);

    Buffer setSubData(long offset, short[] data);

    <T> Buffer setSubData(long offset, T bean, BufferIO<T> bufferIO);
    //endregion

    //region Clear data
    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, ByteBuffer data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, short[] data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, ShortBuffer data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, int[] data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, IntBuffer data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, float[] data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, FloatBuffer data);
    //endregion

    //region Clear sub data
    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, ByteBuffer data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, short[] data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, ShortBuffer data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, int[] data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, IntBuffer data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, float[] data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, FloatBuffer data);
    //endregion

    //region Data mapping
    @Support(since = Version.GL30)
    ByteBuffer mapRange(long offset, long size, MapAccessBits access);

    ByteBuffer mapRange(long offset, long size, BitSet<MapAccessBits> access);

    @Support(since = Version.GL30)
    Buffer flushMappedRange(long offset, long size);

    ByteBuffer map(Access access);

    boolean unmap();
    //endregion

    @Support(since = Version.GL40)
    Buffer invalidateSubData(long offset, long size);

    @Support(since = Version.GL40)
    Buffer invalidateData();

    //region Get buffer data
    Buffer getSubData(long offset, short[] data);

    Buffer getSubData(long offset, ShortBuffer data);

    Buffer getSubData(long offset, int[] data);

    Buffer getSubData(long offset, IntBuffer data);

    Buffer getSubData(long offset, float[] data);

    Buffer getSubData(long offset, FloatBuffer data);

    Buffer getSubData(long offset, long[] data);

    Buffer getSubData(long offset, LongBuffer data);

    Buffer getSubData(long offset, double[] data);

    Buffer getSubData(long offset, DoubleBuffer data);

    Buffer getSubData(long offset, ByteBuffer data);

    <T> T getSubData(long offset, T bean, BufferIO<T> bufferIO);

    default <T> T getSubData(long offset, T bean) {
        return getSubData(offset, bean, BufferIO.ofBean(bean));
    }

    default <T> T getSubData(long offset, TypeReferenceBean<T> typeReferenceBean) {
        return getSubData(offset, typeReferenceBean.getBean(), BufferIO.ofType(typeReferenceBean));
    }
    //endregion

    Buffer copySubData(long readOffset, Buffer dstBuffer, long writeOffset, long size);

    long size();

    BindingPoint bindAt(int bindingPoint, long offset, long size);

    BindingPoint bindAt(int bindingPoint);

    <T> Mapping<T> createMapping(T bean, long offset);

    <T> Mapping<T> createMapping(TypeReferenceBean<T> typeReferenceBean, long offset);

    //region Parameters
    Access getMapAccess();

    BitSet<MapAccessBits> getMapAccessBits();

    boolean isImmutable();

    boolean isMapped();

    long getMappedSize();

    long getMappedOffset();

    BitSet<StorageBits> getStorageBits();

    DataUsage getDataUsage();

    ByteBuffer getMappedBuffer();

    //endregion
    static Buffer of(DataType dataType) {
        return MetaHolder.Buffer.create(dataType);
    }

    static BufferArray of(int n, DataType dataType) {
        return (BufferArray) MetaHolder.Buffer.createArray(n, dataType);
    }

    enum Target implements BindTarget<Target, Buffer>, IntEnum {
        Array("ARRAY_BUFFER"),
        AtomicCounter("ATOMIC_COUNTER_BUFFER"),
        CopyRead("COPY_READ_BUFFER"),
        CopyWrite("COPY_WRITE_BUFFER"),
        DispatchIndirect("DISPATCH_INDIRECT_BUFFER"),
        DrawIndirect("DRAW_INDIRECT_BUFFER"),
        ElementArray("ELEMENT_ARRAY_BUFFER"),
        PixelPack("PIXEL_PACK_BUFFER"),
        PixelUnpack("PIXEL_UNPACK_BUFFER"),
        Query("QUERY_BUFFER"),
        ShaderStorage("SHADER_STORAGE_BUFFER"),
        Texture("TEXTURE_BUFFER"),
        TransformFeedback("TRANSFORM_FEEDBACK_BUFFER"),
        Uniform("UNIFORM_BUFFER");

        final int value;

        private final BindingState<Target, Buffer> state;

        Target(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
            state = MetaHolder.Buffer.newBindingState(name());
        }

        @Override
        public int value() {
            return value;
        }

        @Override
        public BindingState<Target, Buffer> state() {
            return state;
        }

    }

    enum DataUsage implements IntEnum {
        StreamDraw("STREAM_DRAW"),
        StreamRead("STREAM_READ"),
        StreamCopy("STREAM_COPY"),
        StaticDraw("STATIC_DRAW"),
        StaticRead("STATIC_READ"),
        StaticCopy("STATIC_COPY"),
        DynamicDraw("DYNAMIC_DRAW"),
        DynamicRead("DYNAMIC_READ"),
        DynamicCopy("DYNAMIC_COPY");

        final int value;

        DataUsage(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum StorageBits implements IntEnum {
        Dynamic("DYNAMIC_STORAGE_BIT"),
        Client("CLIENT_STORAGE_BIT"),
        Read("MAP_READ_BIT"),
        Write("MAP_WRITE_BIT"),
        @Support(since = Version.GL44)
        PersistentRead("MAP_PERSISTENT_BIT", Read),
        @Support(since = Version.GL44)
        PersistentWrite("MAP_PERSISTENT_BIT", Write),
        @Support(since = Version.GL44)
        Coherent("MAP_COHERENT_BIT", "MAP_PERSISTENT_BIT");
        private final int value;

        StorageBits(String id, StorageBits ma2) {
            this.value = MetaSystem.Graphics.queryInt(id) | ma2.value;
        }

        StorageBits(String id1, String id2) {
            this.value = MetaSystem.Graphics.queryInt(id1) | MetaSystem.Graphics.queryInt(id2);
        }

        StorageBits(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    interface BindingPoint extends IntEnum {
        Target target();

        Buffer buffer();

        long offset();

        long size();

        <T, B extends ProgramResource.BufferBinding<B> & ProgramResource.BufferDataSize<B>>
        Mapping<T> createMapping(T bean, B bufferBinding);
    }

    interface Mapping<T> extends Closeable {
        T getBean();

        int size();

        Buffer.BindingPoint getBindingPoint();

        void flush();

        <F> void flush(SerializableFunction<T, F> fieldGetter);

        void load();

        <F> void load(SerializableFunction<T, F> fieldGetter);

        ByteBuffer storage();
    }
}
