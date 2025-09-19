package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.Color;

public interface SamplerParameters<T> {

    T borderColor(float red, float green, float blue, float alpha);

    Color borderColor();

    default T borderColor(Color color) {
        return borderColor(color.red(), color.green(), color.blue(), color.alpha());
    }

    T compareFunc(CompareFunction func);

    CompareFunction compareFunc();

    T compareModeNone();

    T compareModeRefToTexture();

    boolean isCompareModeRefToTexture();

    T loadBias(float value);

    float loadBias();

    T magFilter(MagFilter mf);

    MagFilter magFilter();

    T maxLoad(float value);

    float maxLoad();

    T minFilter(MinFilter mf);

    MinFilter minFilter();

    T minLoad(float value);

    float minLoad();

    T wrapR(Texture.Wrap wrap);

    Texture.Wrap wrapR();

    T wrapS(Texture.Wrap wrap);

    Texture.Wrap wrapS();

    T wrapT(Texture.Wrap wrap);

    Texture.Wrap wrapT();

}
