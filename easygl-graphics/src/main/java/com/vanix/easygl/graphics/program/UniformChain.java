package com.vanix.easygl.graphics.program;

import com.vanix.easygl.commons.Chain;
import org.joml.*;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class UniformChain<T> extends Chain.Simple<T> implements UniformOps<T>, Chain<T, Uniform> {

    private Uniform uniform;

    public UniformChain(T owner) {
        super(owner);
    }

    @Override
    public void initChain(Uniform uniform) {
        this.uniform = uniform;
    }

    @Override
    public T setInt(int value) {
        uniform.setInt(value);
        return owner;
    }

    @Override
    public T setVec1(int[] value) {
        uniform.setVec1(value);
        return owner;
    }

    @Override
    public T setVec1(IntBuffer buffer) {
        uniform.setVec1(buffer);
        return owner;
    }

    @Override
    public T setVec2(int v1, int v2) {
        uniform.setVec2(v1, v2);
        return owner;
    }

    @Override
    public T setVec2(int[] value) {
        uniform.setVec2(value);
        return owner;
    }

    @Override
    public T setVec2(IntBuffer buffer) {
        uniform.setVec2(buffer);
        return owner;
    }

    @Override
    public T setVec3(int v1, int v2, int v3) {
        uniform.setVec3(v1, v2, v3);
        return owner;
    }

    @Override
    public T setVec3(int[] value) {
        uniform.setVec3(value);
        return owner;
    }

    @Override
    public T setVec3(IntBuffer buffer) {
        uniform.setVec3(buffer);
        return owner;
    }

    @Override
    public T setVec4(int v1, int v2, int v3, int v4) {
        uniform.setVec4(v1, v2, v3, v4);
        return owner;
    }

    @Override
    public T setVec4(int[] value) {
        uniform.setVec4(value);
        return owner;
    }

    @Override
    public T setVec4(IntBuffer buffer) {
        uniform.setVec4(buffer);
        return owner;
    }

    @Override
    public T setUnsigned(int value) {
        uniform.setUnsigned(value);
        return owner;
    }

    @Override
    public T setUnsigned(int v1, int v2) {
        uniform.setUnsigned(v1, v2);
        return owner;
    }

    @Override
    public T setUnsigned(int v1, int v2, int v3) {
        uniform.setUnsigned(v1, v2, v3);
        return owner;
    }

    @Override
    public T setUnsigned(int v1, int v2, int v3, int v4) {
        uniform.setUnsigned(v1, v2, v3, v4);
        return owner;
    }

    @Override
    public T setUnsignedVec1(int[] value) {
        uniform.setUnsignedVec1(value);
        return owner;
    }

    @Override
    public T setUnsignedVec1(IntBuffer buffer) {
        uniform.setUnsignedVec1(buffer);
        return owner;
    }

    @Override
    public T setUnsignedVec2(int[] value) {
        uniform.setUnsignedVec2(value);
        return owner;
    }

    @Override
    public T setUnsignedVec2(IntBuffer buffer) {
        uniform.setUnsignedVec2(buffer);
        return owner;
    }

    @Override
    public T setUnsignedVec3(int[] value) {
        uniform.setUnsignedVec3(value);
        return owner;
    }

    @Override
    public T setUnsignedVec3(IntBuffer buffer) {
        uniform.setUnsignedVec3(buffer);
        return owner;
    }

    @Override
    public T setUnsignedVec4(int[] value) {
        uniform.setUnsignedVec4(value);
        return owner;
    }

    @Override
    public T setUnsignedVec4(IntBuffer buffer) {
        uniform.setUnsignedVec4(buffer);
        return owner;
    }

    @Override
    public T setFloat(float value) {
        uniform.setFloat(value);
        return owner;
    }

    @Override
    public T setVec2(float v1, float v2) {
        uniform.setVec2(v1, v2);
        return owner;
    }

    @Override
    public T setVec3(float v1, float v2, float v3) {
        uniform.setVec3(v1, v2, v3);
        return owner;
    }

    @Override
    public T setVec4(float v1, float v2, float v3, float v4) {
        uniform.setVec4(v1, v2, v3, v4);
        return owner;
    }

    @Override
    public T setVec1(float[] value) {
        uniform.setVec1(value);
        return owner;
    }

    @Override
    public T setVec1(FloatBuffer buffer) {
        uniform.setVec1(buffer);
        return owner;
    }

    @Override
    public T setVec2(float[] value) {
        uniform.setVec2(value);
        return owner;
    }

    @Override
    public T setVec2(FloatBuffer buffer) {
        uniform.setVec2(buffer);
        return owner;
    }

    @Override
    public T setVec3(float[] value) {
        uniform.setVec3(value);
        return owner;
    }

    @Override
    public T setVec3(FloatBuffer buffer) {
        uniform.setVec3(buffer);
        return owner;
    }

    @Override
    public T setVec4(float[] value) {
        uniform.setVec4(value);
        return owner;
    }

    @Override
    public T setVec4(FloatBuffer buffer) {
        uniform.setVec4(buffer);
        return owner;
    }

    @Override
    public T setMatrix2(float[] value) {
        uniform.setMatrix2(value);
        return owner;
    }

    @Override
    public T setMatrix2(FloatBuffer buffer) {
        uniform.setMatrix2(buffer);
        return owner;
    }

    @Override
    public T setMatrix3(float[] value) {
        uniform.setMatrix3(value);
        return owner;
    }

    @Override
    public T setMatrix3(FloatBuffer buffer) {
        uniform.setMatrix3(buffer);
        return owner;
    }

    @Override
    public T setMatrix4(float[] value) {
        uniform.setMatrix4(value);
        return owner;
    }

    @Override
    public T setMatrix4(FloatBuffer buffer) {
        uniform.setMatrix4(buffer);
        return owner;
    }

    @Override
    public T setMatrix2x3(float[] value) {
        uniform.setMatrix2x3(value);
        return owner;
    }

    @Override
    public T setMatrix2x3(FloatBuffer buffer) {
        uniform.setMatrix2x3(buffer);
        return owner;
    }

    @Override
    public T setMatrix3x2(float[] value) {
        uniform.setMatrix3x2(value);
        return owner;
    }

    @Override
    public T setMatrix3x2(FloatBuffer buffer) {
        uniform.setMatrix3x2(buffer);
        return owner;
    }

    @Override
    public T setMatrix2x4(float[] value) {
        uniform.setMatrix2x4(value);
        return owner;
    }

    @Override
    public T setMatrix2x4(FloatBuffer buffer) {
        uniform.setMatrix2x4(buffer);
        return owner;
    }

    @Override
    public T setMatrix4x2(float[] value) {
        uniform.setMatrix4x2(value);
        return owner;
    }

    @Override
    public T setMatrix4x2(FloatBuffer buffer) {
        uniform.setMatrix4x2(buffer);
        return owner;
    }

    @Override
    public T setMatrix3x4(float[] value) {
        uniform.setMatrix3x4(value);
        return owner;
    }

    @Override
    public T setMatrix3x4(FloatBuffer buffer) {
        uniform.setMatrix3x4(buffer);
        return owner;
    }

    @Override
    public T setMatrix4x3(float[] value) {
        uniform.setMatrix4x3(value);
        return owner;
    }

    @Override
    public T setMatrix4x3(FloatBuffer buffer) {
        uniform.setMatrix4x3(buffer);
        return owner;
    }

    @Override
    public T setMatrix2(Matrix2fc value) {
        uniform.setMatrix2(value);
        return owner;
    }

    @Override
    public T setMatrix3(Matrix3fc value) {
        uniform.setMatrix3(value);
        return owner;
    }

    @Override
    public T setMatrix4(Matrix4fc value) {
        uniform.setMatrix4(value);
        return owner;
    }

    @Override
    public T setMatrix3x2(Matrix3x2fc value) {
        uniform.setMatrix3x2(value);
        return owner;
    }

    @Override
    public T setMatrix4x3(Matrix4x3fc value) {
        uniform.setMatrix4x3(value);
        return owner;
    }

    @Override
    public int getInt() {
        return uniform.getInt();
    }

    @Override
    public int[] getInt(int[] value) {
        return uniform.getInt(value);
    }

    @Override
    public IntBuffer getInt(IntBuffer buffer) {
        return uniform.getInt(buffer);
    }

    @Override
    public Vector2i getVec2(Vector2i vec) {
        return uniform.getVec2(vec);
    }

    @Override
    public Vector3i getVec3(Vector3i vec) {
        return uniform.getVec3(vec);
    }

    @Override
    public Vector4i getVec4(Vector4i vec) {
        return uniform.getVec4(vec);
    }

    @Override
    public int getUnsigned() {
        return uniform.getUnsigned();
    }

    @Override
    public int[] getUnsigned(int[] value) {
        return uniform.getUnsigned(value);
    }

    @Override
    public IntBuffer getUnsigned(IntBuffer buffer) {
        return uniform.getUnsigned(buffer);
    }

    @Override
    public float getFloat() {
        return uniform.getFloat();
    }

    @Override
    public float[] getFloat(float[] value) {
        return uniform.getFloat(value);
    }

    @Override
    public FloatBuffer getFloat(FloatBuffer buffer) {
        return uniform.getFloat(buffer);
    }

    @Override
    public Vector2f getVec2(Vector2f vec) {
        return uniform.getVec2(vec);
    }

    @Override
    public Vector3f getVec3(Vector3f vec) {
        return uniform.getVec3(vec);
    }

    @Override
    public Vector4f getVec4(Vector4f vec) {
        return uniform.getVec4(vec);
    }

    @Override
    public Matrix2f getMatrix2(Matrix2f matrix) {
        return uniform.getMatrix2(matrix);
    }

    @Override
    public Matrix3f getMatrix3(Matrix3f matrix) {
        return uniform.getMatrix3(matrix);
    }

    @Override
    public Matrix3x2f getMatrix3X2(Matrix3x2f matrix) {
        return uniform.getMatrix3X2(matrix);
    }

    @Override
    public Matrix4f getMatrix4(Matrix4f matrix) {
        return uniform.getMatrix4(matrix);
    }

    @Override
    public Matrix4x3f getMatrix4X3(Matrix4x3f matrix) {
        return uniform.getMatrix4X3(matrix);
    }

    @Override
    public double getDouble() {
        return uniform.getDouble();
    }

    @Override
    public double[] getDouble(double[] value) {
        return uniform.getDouble(value);
    }

    @Override
    public DoubleBuffer getDouble(DoubleBuffer buffer) {
        return uniform.getDouble(buffer);
    }

    @Override
    public Vector2d getVec2(Vector2d vec) {
        return uniform.getVec2(vec);
    }

    @Override
    public Vector3d getVec3(Vector3d vec) {
        return uniform.getVec3(vec);
    }

    @Override
    public Vector4d getVec4(Vector4d vec) {
        return uniform.getVec4(vec);
    }

    @Override
    public Matrix2d getMatrix2(Matrix2d matrix) {
        return uniform.getMatrix2(matrix);
    }

    @Override
    public Matrix3d getMatrix3(Matrix3d matrix) {
        return uniform.getMatrix3(matrix);
    }

    @Override
    public Matrix3x2d getMatrix3X2(Matrix3x2d matrix) {
        return uniform.getMatrix3X2(matrix);
    }

    @Override
    public Matrix4d getMatrix4(Matrix4d matrix) {
        return uniform.getMatrix4(matrix);
    }

    @Override
    public Matrix4x3d getMatrix4X3(Matrix4x3d matrix) {
        return uniform.getMatrix4X3(matrix);
    }
}
