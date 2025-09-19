package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.*;

public enum DataType implements IntEnum {
    Byte("BYTE", java.lang.Byte.BYTES),
    UnsignedByte("UNSIGNED_BYTE", java.lang.Byte.BYTES),
    UnsignedByte332("UNSIGNED_BYTE_3_3_2", java.lang.Byte.BYTES),
    UnsignedByte233Rev("UNSIGNED_BYTE_2_3_3_REV", java.lang.Byte.BYTES),
    Short("SHORT", java.lang.Short.BYTES),
    UnsignedShort("UNSIGNED_SHORT", java.lang.Short.BYTES),
    UnsignedShort565("UNSIGNED_SHORT_5_6_5", java.lang.Short.BYTES),
    UnsignedShort565Rev("UNSIGNED_SHORT_5_6_5_REV", java.lang.Short.BYTES),
    UnsignedShort4444("UNSIGNED_SHORT_4_4_4_4", java.lang.Short.BYTES),
    UnsignedShort4444Rev("UNSIGNED_SHORT_4_4_4_4_REV", java.lang.Short.BYTES),
    UnsignedShort5551("UNSIGNED_SHORT_5_5_5_1", java.lang.Short.BYTES),
    UnsignedShort1555Rev("UNSIGNED_SHORT_1_5_5_5_REV", java.lang.Short.BYTES),
    Int("INT", Integer.BYTES),
    UnsignedInt("UNSIGNED_INT", Integer.BYTES),
    UnsignedInt8888("UNSIGNED_INT_8_8_8_8", Integer.BYTES),
    UnsignedInt8888Rev("UNSIGNED_INT_8_8_8_8_REV", Integer.BYTES),
    UnsignedInt10_10_10_2("UNSIGNED_INT_10_10_10_2", Integer.BYTES),
    UnsignedInt2_10_10_10Rev("UNSIGNED_INT_2_10_10_10_REV", Integer.BYTES),
    UnsignedInt24_8("UNSIGNED_INT_24_8", Integer.BYTES),
    UnsignedInt10F11F11FRev("UNSIGNED_INT_10F_11F_11F_REV", Integer.BYTES),
    UnsignedInt5999Rev("UNSIGNED_INT_5_9_9_9_REV", Integer.BYTES),
    Float("FLOAT", java.lang.Float.BYTES),
    HalfFloat("HALF_FLOAT", java.lang.Float.BYTES >> 1),
    Double("DOUBLE", java.lang.Double.BYTES),
    Float32UnsignedInt24_8Rev("FLOAT_32_UNSIGNED_INT_24_8_REV", Long.BYTES);
    private final int value;
    private final int bytes;

    DataType(String id, int bytes) {
        this.value = MetaSystem.Graphics.queryInt(id);
        this.bytes = bytes;
    }

    @Override
    public int value() {
        return value;
    }

    public int bytes() {
        return bytes;
    }

    public int bytesOfCount(int count) {
        return bytes * count;
    }

    public static int bytesOf(byte[] data) {
        return data.length;
    }

    public static int bytesOf(short[] data) {
        return Short.bytesOfCount(data.length);
    }

    public static int bytesOf(int[] data) {
        return Int.bytesOfCount(data.length);
    }

    public static int bytesOf(float[] data) {
        return Float.bytesOfCount(data.length);
    }

    public static int bytesOf(double[] data) {
        return Double.bytesOfCount(data.length);
    }

    public static int bytesOf(ByteBuffer data) {
        return data.remaining();
    }

    public static int bytesOf(ShortBuffer data) {
        return Short.bytesOfCount(data.remaining());
    }

    public static int bytesOf(IntBuffer data) {
        return Int.bytesOfCount(data.remaining());
    }

    public static int bytesOf(FloatBuffer data) {
        return Float.bytesOfCount(data.remaining());
    }

    public static int bytesOf(DoubleBuffer data) {
        return Double.bytesOfCount(data.remaining());
    }

}
