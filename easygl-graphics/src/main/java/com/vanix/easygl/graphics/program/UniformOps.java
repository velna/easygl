package com.vanix.easygl.graphics.program;

import com.vanix.easygl.core.Support;
import com.vanix.easygl.graphics.Version;
import com.vanix.easygl.graphics.TextureUnit;
import org.joml.*;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public interface UniformOps<T> {

    default T setBoolean(boolean value) {
        return setInt(value ? 1 : 0);
    }

    default T setTextureUnit(TextureUnit unit) {
        return setInt(unit.index());
    }

    T setInt(int value);

    T setVec1(int[] value);

    T setVec1(IntBuffer buffer);

    T setVec2(int v1, int v2);

    T setVec2(int[] value);

    T setVec2(IntBuffer buffer);

    T setVec3(int v1, int v2, int v3);

    T setVec3(int[] value);

    T setVec3(IntBuffer buffer);

    T setVec4(int v1, int v2, int v3, int v4);

    T setVec4(int[] value);

    T setVec4(IntBuffer buffer);

    T setUnsigned(int value);

    T setUnsigned(int v1, int v2);

    T setUnsigned(int v1, int v2, int v3);

    T setUnsigned(int v1, int v2, int v3, int v4);

    T setUnsignedVec1(int[] value);

    T setUnsignedVec1(IntBuffer buffer);

    T setUnsignedVec2(int[] value);

    T setUnsignedVec2(IntBuffer buffer);

    T setUnsignedVec3(int[] value);

    T setUnsignedVec3(IntBuffer buffer);

    T setUnsignedVec4(int[] value);

    T setUnsignedVec4(IntBuffer buffer);

    T setFloat(float value);

    T setVec2(float v1, float v2);

    T setVec3(float v1, float v2, float v3);

    T setVec4(float v1, float v2, float v3, float v4);

    T setVec1(float[] value);

    T setVec1(FloatBuffer buffer);

    T setVec2(float[] value);

    T setVec2(FloatBuffer buffer);

    T setVec3(float[] value);

    T setVec3(FloatBuffer buffer);

    T setVec4(float[] value);

    T setVec4(FloatBuffer buffer);

    T setMatrix2(float[] value);

    T setMatrix2(FloatBuffer buffer);

    T setMatrix3(float[] value);

    T setMatrix3(FloatBuffer buffer);

    T setMatrix4(float[] value);

    T setMatrix4(FloatBuffer buffer);

    T setMatrix2x3(float[] value);

    T setMatrix2x3(FloatBuffer buffer);

    T setMatrix3x2(float[] value);

    T setMatrix3x2(FloatBuffer buffer);

    T setMatrix2x4(float[] value);

    T setMatrix2x4(FloatBuffer buffer);

    T setMatrix4x2(float[] value);

    T setMatrix4x2(FloatBuffer buffer);

    T setMatrix3x4(float[] value);

    T setMatrix3x4(FloatBuffer buffer);

    T setMatrix4x3(float[] value);

    T setMatrix4x3(FloatBuffer buffer);

    default T setVec2(Vector2fc value) {
        return setVec2(value.x(), value.y());
    }

    default T setVec3(Vector3fc value) {
        return setVec3(value.x(), value.y(), value.z());
    }

    default T setVec4(Vector4fc value) {
        return setVec4(value.x(), value.y(), value.z(), value.w());
    }

    T setMatrix2(Matrix2fc value);

    T setMatrix3(Matrix3fc value);

    T setMatrix4(Matrix4fc value);

    T setMatrix3x2(Matrix3x2fc value);

    T setMatrix4x3(Matrix4x3fc value);

    int getInt();

    int[] getInt(int[] value);

    IntBuffer getInt(IntBuffer buffer);

    Vector2i getVec2(Vector2i vec);

    Vector3i getVec3(Vector3i vec);

    Vector4i getVec4(Vector4i vec);

    int getUnsigned();

    int[] getUnsigned(int[] value);

    IntBuffer getUnsigned(IntBuffer buffer);

    float getFloat();

    float[] getFloat(float[] value);

    FloatBuffer getFloat(FloatBuffer buffer);

    Vector2f getVec2(Vector2f vec);

    Vector3f getVec3(Vector3f vec);

    Vector4f getVec4(Vector4f vec);

    Matrix2f getMatrix2(Matrix2f matrix);

    Matrix3f getMatrix3(Matrix3f matrix);

    Matrix3x2f getMatrix3X2(Matrix3x2f matrix);

    Matrix4f getMatrix4(Matrix4f matrix);

    Matrix4x3f getMatrix4X3(Matrix4x3f matrix);

    @Support(since = Version.GL40)
    double getDouble();

    @Support(since = Version.GL40)
    double[] getDouble(double[] value);

    @Support(since = Version.GL40)
    DoubleBuffer getDouble(DoubleBuffer buffer);

    @Support(since = Version.GL40)
    Vector2d getVec2(Vector2d vec);

    @Support(since = Version.GL40)
    Vector3d getVec3(Vector3d vec);

    @Support(since = Version.GL40)
    Vector4d getVec4(Vector4d vec);

    @Support(since = Version.GL40)
    Matrix2d getMatrix2(Matrix2d matrix);

    @Support(since = Version.GL40)
    Matrix3d getMatrix3(Matrix3d matrix);

    @Support(since = Version.GL40)
    Matrix3x2d getMatrix3X2(Matrix3x2d matrix);

    @Support(since = Version.GL40)
    Matrix4d getMatrix4(Matrix4d matrix);

    @Support(since = Version.GL40)
    Matrix4x3d getMatrix4X3(Matrix4x3d matrix);
}
