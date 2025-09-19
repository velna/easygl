package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.Support;
import org.joml.*;

import javax.annotation.Nullable;
import java.nio.*;

public interface VertexAttribute extends Feature<VertexAttribute, VertexArray>, IntEnum {

    VertexAttribute prevAttribute();

    VertexAttribute nextAttribute();

    VertexAttribute setPointer(int size, DataType dataType, boolean normalized, int stride, long offset);

    long getPointer();

    /**
     * 目前支持Byte, UnsignedByte, Short, UnsignedShort, Int, UnsignedInt, Float, HalfFloat, Double。<br>
     * 不同的类型传入对应的Java类型Byte、Short、Integer、Float、Double。<br>
     * layouts值大于0的，属性会被启动，小于0的不会被启用。<br>
     * layouts绝对值小于10的，为Unsigned类型，大于10的为Signed类型。 <br>
     * layouts绝对值为5时代表BGRA。<br>
     * normalized默认为false，BGRA为true。
     *
     * @param layouts layouts
     * @return last attribute object, not this!
     */
    VertexAttribute enablePointers(Number... layouts);

    default int countOfStride() {
        return (int) (getBindingBuffer().size() / getStride());
    }

    @Support(since = Version.GL43)
    VertexAttribute setFormat(int size, DataType dataType, boolean normalized, int offset);

    @Support(since = Version.GL43)
    VertexAttribute enableFormats(VertexArray.BindingPoint bindingPoint, Number... layouts);

    @Support(since = Version.GL33)
    VertexAttribute setDivisor(int divisor);

    @Support(since = Version.GL43)
    VertexAttribute bind(VertexArray.BindingPoint bindingPoint);

    // region Set Values
    VertexAttribute setShort(short v0);

    VertexAttribute setShort(short v0, short v1);

    VertexAttribute setShort(short v0, short v1, short v2);

    VertexAttribute setShort(short v0, short v1, short v2, short v3);

    VertexAttribute setShort(short[] array);

    VertexAttribute setShort(ShortBuffer buffer);

    VertexAttribute setUnsignedShort(short[] array);

    VertexAttribute setUnsignedShort(ShortBuffer buffer);

    VertexAttribute setInt(int[] array);

    VertexAttribute setInt(IntBuffer buffer);

    VertexAttribute setUnsignedInt(int[] array);

    VertexAttribute setUnsignedInt(IntBuffer buffer);

    VertexAttribute setFloat(float v0);

    VertexAttribute setFloat(float v0, float v1);

    VertexAttribute setFloat(float v0, float v1, float v2);

    VertexAttribute setFloat(float v0, float v1, float v2, float v3);

    VertexAttribute setFloat(float[] array);

    VertexAttribute setFloat(FloatBuffer buffer);

    default VertexAttribute setFloat(Vector2f vec) {
        return setFloat(vec.x, vec.y);
    }

    default VertexAttribute setFloat(Vector3f vec) {
        return setFloat(vec.x, vec.y, vec.z);
    }

    default VertexAttribute setFloat(Vector4f vec) {
        return setFloat(vec.x, vec.y, vec.z, vec.w);
    }

    VertexAttribute setDouble(double v0);

    VertexAttribute setDouble(double v0, double v1);

    VertexAttribute setDouble(double v0, double v1, double v2);

    VertexAttribute setDouble(double v0, double v1, double v2, double v3);

    VertexAttribute setDouble(double[] array);

    VertexAttribute setDouble(DoubleBuffer buffer);

    default VertexAttribute setDouble(Vector2d vec) {
        return setDouble(vec.x, vec.y);
    }

    default VertexAttribute setDouble(Vector3d vec) {
        return setDouble(vec.x, vec.y, vec.z);
    }

    default VertexAttribute setDouble(Vector4d vec) {
        return setDouble(vec.x, vec.y, vec.z, vec.w);
    }

    VertexAttribute setNormalizedByte(ByteBuffer buffer);

    VertexAttribute setNormalizedUnsignedByte(byte v0, byte v1, byte v2, byte v3);

    VertexAttribute setNormalizedUnsignedByte(ByteBuffer buffer);

    default VertexAttribute setNormalizedShort4(short v0, short v1, short v2, short v3) {
        return setNormalizedShort4(new short[]{v0, v1, v2, v3});
    }

    VertexAttribute setNormalizedShort4(short[] array);

    VertexAttribute setNormalizedShort4(ShortBuffer buffer);

    default VertexAttribute setNormalizedUnsignedShort4(short v0, short v1, short v2, short v3) {
        return setNormalizedUnsignedShort4(new short[]{v0, v1, v2, v3});
    }

    VertexAttribute setNormalizedUnsignedShort4(short[] array);

    VertexAttribute setNormalizedUnsignedShort4(ShortBuffer buffer);

    default VertexAttribute setNormalizedInt4(int v0, int v1, int v2, int v3) {
        return setNormalizedInt4(new int[]{v0, v1, v2, v3});
    }

    VertexAttribute setNormalizedInt4(int[] array);

    default VertexAttribute setNormalizedInt4(Vector4i vec) {
        return setNormalizedInt4(vec.x, vec.y, vec.z, vec.w);
    }

    VertexAttribute setNormalizedInt4(IntBuffer buffer);

    default VertexAttribute setNormalizedUnsignedInt4(int v0, int v1, int v2, int v3) {
        return setNormalizedUnsignedInt4(new int[]{v0, v1, v2, v3});
    }

    VertexAttribute setNormalizedUnsignedInt4(int[] array);

    default VertexAttribute setNormalizedUnsignedInt4(Vector4i vec) {
        return setNormalizedUnsignedInt4(vec.x, vec.y, vec.z, vec.w);
    }

    VertexAttribute setNormalizedUnsignedInt4(IntBuffer buffer);

    VertexAttribute setExtendedInt(int v0);

    VertexAttribute setExtendedInt(int v0, int v1);

    VertexAttribute setExtendedInt(int v0, int v1, int v2);

    VertexAttribute setExtendedInt(int v0, int v1, int v2, int v3);

    VertexAttribute setExtendedInt(int[] array);

    VertexAttribute setExtendedInt(IntBuffer buffer);

    default VertexAttribute setExtendedInt(Vector2i vec) {
        return setExtendedInt(vec.x, vec.y);
    }

    default VertexAttribute setExtendedInt(Vector3i vec) {
        return setExtendedInt(vec.x, vec.y, vec.z);
    }

    default VertexAttribute setExtendedInt(Vector4i vec) {
        return setExtendedInt(vec.x, vec.y, vec.z, vec.w);
    }

    VertexAttribute setExtendedUnsignedInt(int v0);

    VertexAttribute setExtendedUnsignedInt(int v0, int v1);

    VertexAttribute setExtendedUnsignedInt(int v0, int v1, int v2);

    VertexAttribute setExtendedUnsignedInt(int v0, int v1, int v2, int v3);

    VertexAttribute setExtendedUnsignedInt(int[] array);

    VertexAttribute setExtendedUnsignedInt(IntBuffer buffer);

    default VertexAttribute setExtendedUnsignedInt(Vector2i vec) {
        return setExtendedUnsignedInt(vec.x, vec.y);
    }

    default VertexAttribute setExtendedUnsignedInt(Vector3i vec) {
        return setExtendedUnsignedInt(vec.x, vec.y, vec.z);
    }

    default VertexAttribute setExtendedUnsignedInt(Vector4i vec) {
        return setExtendedUnsignedInt(vec.x, vec.y, vec.z, vec.w);
    }

    VertexAttribute setExtendedByte4(ByteBuffer buffer);

    VertexAttribute setExtendedUnsignedByte4(ByteBuffer buffer);

    default VertexAttribute setExtendedShort4(short v0, short v1, short v2, short v3) {
        return setExtendedShort4(new short[]{v0, v1, v2, v3});
    }

    VertexAttribute setExtendedShort4(short[] array);

    VertexAttribute setExtendedShort4(ShortBuffer buffer);

    default VertexAttribute setExtendedUnsignedShort4(short v0, short v1, short v2, short v3) {
        return setExtendedUnsignedShort4(new short[]{v0, v1, v2, v3});
    }

    VertexAttribute setExtendedUnsignedShort4(short[] array);

    VertexAttribute setExtendedUnsignedShort4(ShortBuffer buffer);

    VertexAttribute setDirectDouble(double v0);

    @Support(since = Version.GL41)
    VertexAttribute setDirectDouble(double v0, double v1);

    @Support(since = Version.GL41)
    VertexAttribute setDirectDouble(double v0, double v1, double v2);

    @Support(since = Version.GL41)
    VertexAttribute setDirectDouble(double v0, double v1, double v2, double v3);

    @Support(since = Version.GL41)
    VertexAttribute setDirectDouble(double[] array);

    @Support(since = Version.GL41)
    VertexAttribute setDirectDouble(DoubleBuffer buffer);

    @Support(since = Version.GL41)
    default VertexAttribute setDirectDouble(Vector2d vec) {
        return setDirectDouble(vec.x, vec.y);
    }

    @Support(since = Version.GL41)
    default VertexAttribute setDirectDouble(Vector3d vec) {
        return setDirectDouble(vec.x, vec.y, vec.z);
    }

    @Support(since = Version.GL41)
    default VertexAttribute setDirectDouble(Vector4d vec) {
        return setDirectDouble(vec.x, vec.y, vec.z, vec.w);
    }

    @Support(since = Version.GL33)
    VertexAttribute setPackedInt1(boolean normalized, int v);

    @Support(since = Version.GL33)
    VertexAttribute setPackedInt2(boolean normalized, int v);

    @Support(since = Version.GL33)
    VertexAttribute setPackedInt3(boolean normalized, int v);

    @Support(since = Version.GL33)
    VertexAttribute setPackedInt4(boolean normalized, int v);

    @Support(since = Version.GL33)
    VertexAttribute setPackedUnsignedInt1(boolean normalized, int v);

    @Support(since = Version.GL33)
    VertexAttribute setPackedUnsignedInt2(boolean normalized, int v);

    @Support(since = Version.GL33)
    VertexAttribute setPackedUnsignedInt3(boolean normalized, int v);

    @Support(since = Version.GL33)
    VertexAttribute setPackedUnsignedInt4(boolean normalized, int v);

    @Support(since = Version.GL33)
    VertexAttribute setPackedFloat1(int v);

    @Support(since = Version.GL33)
    VertexAttribute setPackedFloat2(int v);

    @Support(since = Version.GL33)
    VertexAttribute setPackedFloat3(int v);

    @Support(since = Version.GL33)
    VertexAttribute setPackedFloat4(int v);
    // endregion

    // region Get parameters
    @Nullable
    Buffer getBindingBuffer();

    int getSize();

    int getStride();

    DataType getDataType();

    boolean isNormalized();

    @Support(since = Version.GL30)
    boolean isInteger();

    @Support(since = Version.GL43)
    boolean isLong();

    @Support(since = Version.GL33)
    int getDivisor();

    @Support(since = Version.GL43)
    VertexArray.BindingPoint getBindingPoint();

    @Support(since = Version.GL43)
    int getOffset();

    Vector4f getFloat();

    Vector4d getFloatAsDouble();

    Vector4i getFloatAsInt();

    @Support(since = Version.GL30)
    Vector4i getInt();

    @Support(since = Version.GL30)
    Vector4i getUnsignedInt();

    @Support(since = Version.GL41)
    Vector4d getDouble();
    // endregion

}
