package com.vanix.easygl.opengl.program;

import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.graphics.program.Uniform;
import org.joml.*;
import org.lwjgl.system.MemoryStack;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.function.BiFunction;

public interface GlUniform extends Uniform {

    @Override
    default Uniform setInt(int value) {
        program().assertBinding();
        GLX.glUniform1i(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec2(int v1, int v2) {
        program().assertBinding();
        GLX.glUniform2i(getLocation(), v1, v2);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec3(int v1, int v2, int v3) {
        program().assertBinding();
        GLX.glUniform3i(getLocation(), v1, v2, v3);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec4(int v1, int v2, int v3, int v4) {
        program().assertBinding();
        GLX.glUniform4i(getLocation(), v1, v2, v3, v4);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec1(int[] value) {
        program().assertBinding();
        GLX.glUniform1iv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec1(IntBuffer buffer) {
        program().assertBinding();
        GLX.glUniform1iv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec2(int[] value) {
        program().assertBinding();
        GLX.glUniform2iv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec2(IntBuffer buffer) {
        program().assertBinding();
        GLX.glUniform2iv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec3(int[] value) {
        program().assertBinding();
        GLX.glUniform3iv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec3(IntBuffer buffer) {
        program().assertBinding();
        GLX.glUniform3iv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec4(int[] value) {
        program().assertBinding();
        GLX.glUniform4iv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec4(IntBuffer buffer) {
        program().assertBinding();
        GLX.glUniform4iv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsigned(int value) {
        program().assertBinding();
        GLX.glUniform1ui(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsigned(int v1, int v2) {
        program().assertBinding();
        GLX.glUniform2ui(getLocation(), v1, v2);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsigned(int v1, int v2, int v3) {
        program().assertBinding();
        GLX.glUniform3ui(getLocation(), v1, v2, v3);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsigned(int v1, int v2, int v3, int v4) {
        program().assertBinding();
        GLX.glUniform4ui(getLocation(), v1, v2, v3, v4);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsignedVec1(int[] value) {
        program().assertBinding();
        GLX.glUniform1uiv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsignedVec1(IntBuffer buffer) {
        program().assertBinding();
        GLX.glUniform1uiv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsignedVec2(int[] value) {
        program().assertBinding();
        GLX.glUniform2uiv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsignedVec2(IntBuffer buffer) {
        program().assertBinding();
        GLX.glUniform2uiv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsignedVec3(int[] value) {
        program().assertBinding();
        GLX.glUniform3uiv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsignedVec3(IntBuffer buffer) {
        program().assertBinding();
        GLX.glUniform3uiv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsignedVec4(int[] value) {
        program().assertBinding();
        GLX.glUniform4uiv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setUnsignedVec4(IntBuffer buffer) {
        program().assertBinding();
        GLX.glUniform4uiv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setFloat(float value) {
        program().assertBinding();
        GLX.glUniform1f(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec2(float v1, float v2) {
        program().assertBinding();
        GLX.glUniform2f(getLocation(), v1, v2);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec3(float v1, float v2, float v3) {
        program().assertBinding();
        GLX.glUniform3f(getLocation(), v1, v2, v3);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec4(float v1, float v2, float v3, float v4) {
        program().assertBinding();
        GLX.glUniform4f(getLocation(), v1, v2, v3, v4);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec1(float[] value) {
        program().assertBinding();
        GLX.glUniform1fv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec1(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniform1fv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec2(float[] value) {
        program().assertBinding();
        GLX.glUniform2fv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec2(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniform2fv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec3(float[] value) {
        program().assertBinding();
        GLX.glUniform3fv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec3(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniform3fv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec4(float[] value) {
        program().assertBinding();
        GLX.glUniform4fv(getLocation(), value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setVec4(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniform4fv(getLocation(), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix2(float[] value) {
        program().assertBinding();
        GLX.glUniformMatrix2fv(getLocation(), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix2(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniformMatrix2fv(getLocation(), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix3(float[] value) {
        program().assertBinding();
        GLX.glUniformMatrix3fv(getLocation(), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix3(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniformMatrix3fv(getLocation(), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix4(float[] value) {
        program().assertBinding();
        GLX.glUniformMatrix4fv(getLocation(), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix4(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniformMatrix4fv(getLocation(), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix2x3(float[] value) {
        program().assertBinding();
        GLX.glUniformMatrix2x3fv(getLocation(), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix2x3(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniformMatrix2x3fv(getLocation(), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix3x2(float[] value) {
        program().assertBinding();
        GLX.glUniformMatrix3x2fv(getLocation(), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix3x2(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniformMatrix3x2fv(getLocation(), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix2x4(float[] value) {
        program().assertBinding();
        GLX.glUniformMatrix2x4fv(getLocation(), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix2x4(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniformMatrix2x4fv(getLocation(), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix4x2(float[] value) {
        program().assertBinding();
        GLX.glUniformMatrix4x2fv(getLocation(), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix4x2(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniformMatrix4x2fv(getLocation(), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix3x4(float[] value) {
        program().assertBinding();
        GLX.glUniformMatrix3x4fv(getLocation(), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix3x4(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniformMatrix3x4fv(getLocation(), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix4x3(float[] value) {
        program().assertBinding();
        GLX.glUniformMatrix4x3fv(getLocation(), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix4x3(FloatBuffer buffer) {
        program().assertBinding();
        GLX.glUniformMatrix4x3fv(getLocation(), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    default Uniform setMatrix2(Matrix2fc value) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(4);
            return setMatrix2(value.get(buffer));
        }
    }

    @Override
    default Uniform setMatrix3(Matrix3fc value) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(9);
            return setMatrix3(value.get(buffer));
        }
    }

    @Override
    default Uniform setMatrix4(Matrix4fc value) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(16);
            return setMatrix4(value.get(buffer));
        }
    }

    @Override
    default Uniform setMatrix3x2(Matrix3x2fc value) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(6);
            return setMatrix3x2(value.get(buffer));
        }
    }

    @Override
    default Uniform setMatrix4x3(Matrix4x3fc value) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(12);
            return setMatrix4x3(value.get(buffer));
        }
    }

    @Override
    default int getInt() {
        return GLX.glGetUniformi(program().intHandle(), getLocation());
    }

    @Override
    default int[] getInt(int[] value) {
        GLX.glGetUniformiv(program().intHandle(), getLocation(), value);
        return value;
    }

    @Override
    default IntBuffer getInt(IntBuffer buffer) {
        GLX.glGetUniformiv(program().intHandle(), getLocation(), buffer);
        return buffer;
    }

    private <T> T getInt0(T value, int size, BiFunction<T, IntBuffer, T> setter) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocInt(size);
            GLX.glGetUniformiv(program().intHandle(), getLocation(), buffer);
            return setter.apply(value, buffer);
        }
    }

    @Override
    default Vector2i getVec2(Vector2i vec) {
        return getInt0(vec, 2, Vector2i::set);
    }

    @Override
    default Vector3i getVec3(Vector3i vec) {
        return getInt0(vec, 3, Vector3i::set);
    }

    @Override
    default Vector4i getVec4(Vector4i vec) {
        return getInt0(vec, 4, Vector4i::set);
    }

    private <T> T getFloat0(T value, int size, BiFunction<T, FloatBuffer, T> setter) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(size);
            GLX.glGetUniformfv(program().intHandle(), getLocation(), buffer);
            return setter.apply(value, buffer);
        }
    }

    @Override
    default float getFloat() {
        return GLX.glGetUniformf(program().intHandle(), getLocation());
    }

    @Override
    default float[] getFloat(float[] value) {
        GLX.glGetUniformfv(program().intHandle(), getLocation(), value);
        return value;
    }

    @Override
    default FloatBuffer getFloat(FloatBuffer buffer) {
        GLX.glGetUniformfv(program().intHandle(), getLocation(), buffer);
        return buffer;
    }

    @Override
    default Vector2f getVec2(Vector2f vec) {
        return getFloat0(vec, 2, Vector2f::set);
    }

    @Override
    default Vector3f getVec3(Vector3f vec) {
        return getFloat0(vec, 3, Vector3f::set);
    }

    @Override
    default Vector4f getVec4(Vector4f vec) {
        return getFloat0(vec, 4, Vector4f::set);
    }

    @Override
    default Matrix2f getMatrix2(Matrix2f matrix) {
        return getFloat0(matrix, 4, Matrix2f::set);
    }

    @Override
    default Matrix3f getMatrix3(Matrix3f matrix) {
        return getFloat0(matrix, 9, Matrix3f::set);
    }

    @Override
    default Matrix3x2f getMatrix3X2(Matrix3x2f matrix) {
        return getFloat0(matrix, 6, Matrix3x2f::set);
    }

    @Override
    default Matrix4f getMatrix4(Matrix4f matrix) {
        return getFloat0(matrix, 16, Matrix4f::set);
    }

    @Override
    default Matrix4x3f getMatrix4X3(Matrix4x3f matrix) {
        return getFloat0(matrix, 12, Matrix4x3f::set);
    }

    private <T> T getDouble0(T value, int size, BiFunction<T, DoubleBuffer, T> setter) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocDouble(size);
            GLX.glGetUniformdv(program().intHandle(), getLocation(), buffer);
            return setter.apply(value, buffer);
        }
    }

    @Override
    default double getDouble() {
        return GLX.glGetUniformf(program().intHandle(), getLocation());
    }

    @Override
    default double[] getDouble(double[] value) {
        GLX.glGetUniformdv(program().intHandle(), getLocation(), value);
        return value;
    }

    @Override
    default DoubleBuffer getDouble(DoubleBuffer buffer) {
        GLX.glGetUniformdv(program().intHandle(), getLocation(), buffer);
        return buffer;
    }

    @Override
    default Vector2d getVec2(Vector2d vec) {
        return getDouble0(vec, 2, Vector2d::set);
    }

    @Override
    default Vector3d getVec3(Vector3d vec) {
        return getDouble0(vec, 3, Vector3d::set);
    }

    @Override
    default Vector4d getVec4(Vector4d vec) {
        return getDouble0(vec, 4, Vector4d::set);
    }

    @Override
    default Matrix2d getMatrix2(Matrix2d matrix) {
        return getDouble0(matrix, 4, Matrix2d::set);
    }

    @Override
    default Matrix3d getMatrix3(Matrix3d matrix) {
        return getDouble0(matrix, 9, Matrix3d::set);
    }

    @Override
    default Matrix3x2d getMatrix3X2(Matrix3x2d matrix) {
        return getDouble0(matrix, 6, Matrix3x2d::set);
    }

    @Override
    default Matrix4d getMatrix4(Matrix4d matrix) {
        return getDouble0(matrix, 16, Matrix4d::set);
    }

    @Override
    default Matrix4x3d getMatrix4X3(Matrix4x3d matrix) {
        return getDouble0(matrix, 12, Matrix4x3d::set);
    }

    @Override
    default int getUnsigned() {
        return GLX.glGetUniformui(program().intHandle(), getLocation());
    }

    @Override
    default int[] getUnsigned(int[] value) {
        GLX.glGetUniformuiv(program().intHandle(), getLocation(), value);
        return value;
    }

    @Override
    default IntBuffer getUnsigned(IntBuffer buffer) {
        GLX.glGetUniformuiv(program().intHandle(), getLocation(), buffer);
        return buffer;
    }

}
