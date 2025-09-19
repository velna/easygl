package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.commons.util.IndexedEnumCache;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

@Support(since = Version.GL42)
public abstract class ImageUnit extends SimpleIntEnum {
    public static final int MAX_IMAGE_UNITS = MetaSystem.Graphics.queryInt("GET.MAX_IMAGE_UNITS");
    private static final IndexedEnumCache<ImageUnit> cache = new IndexedEnumCache<>(MAX_IMAGE_UNITS, MetaHolder.ImageUnit::create);
    public static final ImageUnit U0 = of(0);
    public static final ImageUnit U1 = of(1);
    public static final ImageUnit U2 = of(2);
    public static final ImageUnit U3 = of(3);
    public static final ImageUnit U4 = of(4);
    public static final ImageUnit U5 = of(5);
    public static final ImageUnit U6 = of(6);
    public static final ImageUnit U7 = of(7);
    public static final ImageUnit U8 = of(8);
    public static final ImageUnit U9 = of(9);
    public static final ImageUnit U10 = of(10);
    public static final ImageUnit U11 = of(11);
    public static final ImageUnit U12 = of(12);
    public static final ImageUnit U13 = of(13);
    public static final ImageUnit U14 = of(14);
    public static final ImageUnit U15 = of(15);

    protected ImageUnit(int index) {
        super(index);
    }

    public static ImageUnit of(int i) {
        return cache.valueOf(i);
    }

    public abstract ImageUnit bindTexture(Texture<?> texture, int level, boolean layered, int layer, Access access, InternalPixelFormat.Sized format);

    @Support(since = Version.GL44)
    public abstract ImageUnit bindTextures(Iterable<Texture<?>> textures);

    public abstract Texture<?> bindingTexture();

    public abstract int bindingLevel();

    public abstract boolean isBindingLayered();

    public abstract Access bindingAccess();

    public abstract InternalPixelFormat.Sized bindingFormat();
}
