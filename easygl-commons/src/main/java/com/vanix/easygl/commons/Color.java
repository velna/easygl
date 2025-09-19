package com.vanix.easygl.commons;

import org.joml.Vector4f;

public class Color extends Vector4f {
    public Color() {
    }

    public Color(float red) {
        super(red, 0, 0, 1.0f);
    }

    public Color(float red, float green) {
        super(red, green, 0, 1.0f);
    }

    public Color(float red, float green, float blue) {
        super(red, green, blue, 1.0f);
    }

    public Color(float red, float green, float blue, float alpha) {
        super(red, green, blue, alpha);
    }

    public float red() {
        return x;
    }

    public float green() {
        return y;
    }

    public float blue() {
        return z;
    }

    public float alpha() {
        return w;
    }

    @Override
    public Color clone() {
        try {
            return (Color) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
