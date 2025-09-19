package com.vanix.easygl.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public sealed interface InternalPixelFormat permits
        InternalPixelFormat.Base,
        InternalPixelFormat.Sized,
        InternalPixelFormat.Compressed {

    int value();

    enum Base implements InternalPixelFormat {
        DEPTH_COMPONENT("DEPTH_COMPONENT"),
        DEPTH_STENCIL("DEPTH_STENCIL"),
        DEPTH24_STENCIL8("DEPTH24_STENCIL8"),
        RED("RED"),
        RG("RG"),
        RGB("RGB"),
        RGBA("RGBA");
        private final int value;

        Base(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum Sized implements InternalPixelFormat {
        R8("R8", Base.RED),
        R8_SNORM("R8_SNORM", Base.RED),
        R16("R16", Base.RED),
        R16_SNORM("R16_SNORM", Base.RED),
        RG8("RG8", Base.RG),
        RG8_SNORM("RG8_SNORM", Base.RG),
        RG16("RG16", Base.RG),
        RG16_SNORM("RG16_SNORM", Base.RG),
        R3_G3_B2("R3_G3_B2", Base.RGB),
        RGB4("RGB4", Base.RGB),
        RGB5("RGB5", Base.RGB),
        RGB8("RGB8", Base.RGB),
        RGB8_SNORM("RGB8_SNORM", Base.RGB),
        RGB10("RGB10", Base.RGB),
        RGB12("RGB12", Base.RGB),
        RGB16_SNORM("RGB16_SNORM", Base.RGB),
        RGBA2("RGBA2", Base.RGB),
        RGBA4("RGBA4", Base.RGB),
        RGB5_A1("RGB5_A1", Base.RGBA),
        RGBA8("RGBA8", Base.RGBA),
        RGBA8_SNORM("RGBA8_SNORM", Base.RGBA),
        RGB10_A2("RGB10_A2", Base.RGBA),
        RGB10_A2UI("RGB10_A2UI", Base.RGBA),
        RGBA12("RGBA12", Base.RGBA),
        RGBA16("RGBA16", Base.RGBA),
        SRGB8("SRGB8", Base.RGB),
        SRGB8_ALPHA8("SRGB8_ALPHA8", Base.RGBA),
        R16F("R16F", Base.RED),
        RG16F("RG16F", Base.RG),
        RGB16F("RGB16F", Base.RGB),
        RGBA16F("RGBA16F", Base.RGBA),
        R32F("R32F", Base.RED),
        RG32F("RG32F", Base.RG),
        RGB32F("RGB32F", Base.RGB),
        RGBA32F("RGBA32F", Base.RGBA),
        R11F_G11F_B10F("R11F_G11F_B10F", Base.RGB),
        RGB9_E5("RGB9_E5", Base.RGB),
        R8I("R8I", Base.RED),
        R8UI("R8UI", Base.RED),
        R16I("R16I", Base.RED),
        R16UI("R16UI", Base.RED),
        R32I("R32I", Base.RED),
        R32UI("R32UI", Base.RED),
        RG8I("RG8I", Base.RG),
        RG8UI("RG8UI", Base.RG),
        RG16I("RG16I", Base.RG),
        RG16UI("RG16UI", Base.RG),
        RG32I("RG32I", Base.RG),
        RG32UI("RG32UI", Base.RG),
        RGB8I("RGB8I", Base.RGB),
        RGB8UI("RGB8UI", Base.RGB),
        RGB16I("RGB16I", Base.RGB),
        RGB16UI("RGB16UI", Base.RGB),
        RGB32I("RGB32I", Base.RGB),
        RGB32UI("RGB32UI", Base.RGB),
        RGBA8I("RGBA8I", Base.RGBA),
        RGBA8UI("RGBA8UI", Base.RGBA),
        RGBA16I("RGBA16I", Base.RGBA),
        RGBA16UI("RGBA16UI", Base.RGBA),
        RGBA32I("RGBA32I", Base.RGBA),
        RGBA32UI("RGBA32UI", Base.RGBA);
        private final int value;
        private final Base baseFormat;

        Sized(String id, Base baseFormat) {
            this.value = MetaSystem.Graphics.queryInt(id);
            this.baseFormat = baseFormat;
        }

        @Override
        public int value() {
            return value;
        }

        public Base baseFormat() {
            return baseFormat;
        }
    }

    enum Compressed implements InternalPixelFormat {
        RED("COMPRESSED_RED", Base.RED),
        RG("COMPRESSED_RG", Base.RG),
        RGB("COMPRESSED_RGB", Base.RGB),
        RGBA("COMPRESSED_RGBA", Base.RGBA),
        SRGB("COMPRESSED_SRGB", Base.RGB),
        SRGB_ALPHA("COMPRESSED_SRGB_ALPHA", Base.RGBA),
        RED_RGTC1("COMPRESSED_RED_RGTC1", Base.RED),
        SIGNED_RED_RGTC1("COMPRESSED_SIGNED_RED_RGTC1", Base.RED),
        RG_RGTC2("COMPRESSED_RG_RGTC2", Base.RG),
        SIGNED_RG_RGTC2("COMPRESSED_SIGNED_RG_RGTC2", Base.RG),
        RGBA_BPTC_UNORM("COMPRESSED_RGBA_BPTC_UNORM", Base.RGBA),
        SRGB_ALPHA_BPTC_UNORM("COMPRESSED_SRGB_ALPHA_BPTC_UNORM", Base.RGBA),
        RGB_BPTC_SIGNED_FLOAT("COMPRESSED_RGB_BPTC_SIGNED_FLOAT", Base.RGB),
        RGB_BPTC_UNSIGNED_FLOAT("COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT", Base.RGB);

        private final int value;
        private final Base baseFormat;

        Compressed(String id, Base baseFormat) {
            this.value = MetaSystem.Graphics.queryInt(id);
            this.baseFormat = baseFormat;
        }

        @Override
        public int value() {
            return value;
        }

        public Base baseFormat() {
            return baseFormat;
        }
    }

}
