package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Texture<T extends Texture<T>> extends Bindable<Texture.TexTarget<T>, T>,
        TextureOps.ClearImage<T>,
        TextureOps.ClearSubImage<T> {

    default T bind(TextureUnit unit) {
        unit.bind();
        return this.bind();
    }

    T proxy();

    // region Level Parameters
    int alphaSize(int level);

    DataType alphaType(int level);

    int blueSize(int level);

    DataType blueType(int level);

    int bufferOffset(int level);

    int bufferSize(int level);

    int compressedImageSize(int level);

    int depth(int level);

    int depthSize(int level);

    DataType depthType(int level);

    int greenSize(int level);

    DataType greenType(int level);

    int height(int level);

    InternalPixelFormat internalFormat(int level);

    boolean isCompressed(int level);

    int redSize(int level);

    DataType redType(int level);

    int width(int level);
    // endregion

    // region Invalidate Sub
    T invalidateSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth);

    default T invalidateSub1D(int level, int xOffset, int width) {
        return invalidateSub(level, xOffset, 0, 0, width, 1, 1);
    }

    default T invalidateSub1D(int xOffset, int width) {
        return invalidateSub(0, xOffset, 0, 0, width, 1, 1);
    }

    default T invalidateSub2D(int level, int xOffset, int yOffset, int width, int height) {
        return invalidateSub(level, xOffset, yOffset, 0, width, height, 1);
    }

    default T invalidateSub2D(int xOffset, int yOffset, int width, int height) {
        return invalidateSub(0, xOffset, yOffset, 0, width, height, 1);
    }
    // endregion

    class TexTarget<T extends Texture<T>> implements BindTarget<TexTarget<T>, T> {
        private final int value;
        private final Integer proxyValue;
        private final BindingState<TexTarget<T>, T> state;

        public TexTarget(int value, String name, BindableMeta<TexTarget<T>, T> meta) {
            this(value, null, meta.newBindingState(name));
        }

        private TexTarget(int value, Integer proxyValue, BindingState<TexTarget<T>, T> state) {
            this.value = value;
            this.proxyValue = value;
            this.state = state;
        }

        public int value(boolean proxy) {
            if (proxy && proxyValue != null) {
                return proxyValue;
            } else {
                return value;
            }
        }

        @Override
        public int value() {
            return value;
        }

        @Override
        public BindingState<TexTarget<T>, T> state() {
            return state;
        }

        public TexTarget<T> proxy(int proxyValue) {
            return new TexTarget<>(value, proxyValue, state);
        }
    }

    enum Swizzle {
        Red("RED"),
        Green("GREEN"),
        Blue("BLUE"),
        Alpha("ALPHA"),
        Zero("ZERO"),
        One("ONE");

        private final int value;

        Swizzle(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    enum Wrap implements IntEnum {
        ClampToEdge("CLAMP_TO_EDGE"),
        ClampToBorder("CLAMP_TO_BORDER"),
        MirroredRepeat("MIRRORED_REPEAT"),
        Repeat("REPEAT"),
        @Support(since = Version.GL44)
        MirrorClampToEdge("MIRROR_CLAMP_TO_EDGE");

        private final int value;

        Wrap(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum DepthStencilMode implements IntEnum {
        DepthComponent("DEPTH_COMPONENT"),
        StencilIndex("STENCIL_INDEX");

        private final int value;

        DepthStencilMode(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

}
