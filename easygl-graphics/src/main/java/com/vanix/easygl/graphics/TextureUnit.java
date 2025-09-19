package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.SimpleIndexedIntEnum;
import com.vanix.easygl.commons.util.IndexedEnumCache;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

public abstract class TextureUnit extends SimpleIndexedIntEnum implements Bindable<BindTarget.Default<TextureUnit>, TextureUnit> {
    public static final int MAX_TEXTURE_UNITS = MetaSystem.Graphics.queryInt("MAX_COMBINED_TEXTURE_IMAGE_UNITS");
    private static final IndexedEnumCache<TextureUnit> cache = new IndexedEnumCache<>(MAX_TEXTURE_UNITS, MetaHolder.TextureUnit::create);
    public static final BindTarget.Default<TextureUnit> Target = new BindTarget.Default<>(MetaHolder.TextureUnit.newBindingState("TextureUnit"));
    public static final TextureUnit U0 = of(0);
    public static final TextureUnit U1 = of(1);
    public static final TextureUnit U2 = of(2);
    public static final TextureUnit U3 = of(3);
    public static final TextureUnit U4 = of(4);
    public static final TextureUnit U5 = of(5);
    public static final TextureUnit U6 = of(6);
    public static final TextureUnit U7 = of(7);
    public static final TextureUnit U8 = of(8);
    public static final TextureUnit U9 = of(9);
    public static final TextureUnit U10 = of(10);
    public static final TextureUnit U11 = of(11);
    public static final TextureUnit U12 = of(12);
    public static final TextureUnit U13 = of(13);
    public static final TextureUnit U14 = of(14);
    public static final TextureUnit U15 = of(15);

    protected TextureUnit(int index) {
        super((int) Target.state().unbindValue() + index, index);
    }

    @Override
    public BindTarget.Default<TextureUnit> target() {
        return Target;
    }

    @Override
    public void close() {

    }

    @Override
    public long handle() {
        return value;
    }

    public static TextureUnit of(int i) {
        return cache.valueOf(i);
    }

    public TextureUnit bindTexture(Texture<?> texture) {
        this.bind();
        texture.bind();
        return this;
    }

    @Support(since = Version.GL44)
    public abstract TextureUnit binTextures(Texture<?>... textures);

    @Support(since = Version.GL33)
    public abstract TextureUnit bindSampler(Sampler sampler);

    @Support(since = Version.GL44)
    public abstract TextureUnit bindSamplers(Sampler... samplers);
}
