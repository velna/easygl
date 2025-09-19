package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.commons.SimpleIndexedIntEnum;
import com.vanix.easygl.commons.util.IndexedEnumCache;
import com.vanix.easygl.core.meta.MetaSystem;

public abstract class FrameInnerBuffer {

    public sealed interface Attachment extends IntEnum permits ColorAttachment, Constants {
        int MAX_COLOR_ATTACHMENTS = MetaSystem.Graphics.queryInt("GET.MAX_COLOR_ATTACHMENTS");
        Attachment Depth = Constants.DepthAttachment;
        Attachment Stencil = Constants.StencilAttachment;
        Attachment DepthStencil = Constants.DepthStencilAttachment;

        static ColorAttachment ofColor(int i) {
            return ColorAttachmentImpl.of(i);
        }
    }

    public sealed interface ColorAttachment extends Attachment permits ColorAttachmentImpl {
        int index();
    }

    public sealed interface MultiSelectableDrawBuffer extends IntEnum permits Constants, ColorAttachmentImpl {
        MultiSelectableDrawBuffer None = Constants.None;
        MultiSelectableDrawBuffer FrontLeft = Constants.FrontLeft;
        MultiSelectableDrawBuffer FrontRight = Constants.FrontRight;
        MultiSelectableDrawBuffer BackLeft = Constants.BackLeft;
        MultiSelectableDrawBuffer BackRight = Constants.BackRight;

        static MultiSelectableDrawBuffer ofColor(int i) {
            return ColorAttachmentImpl.of(i);
        }
    }

    public sealed interface ReadBuffer extends IntEnum permits Constants, ColorAttachmentImpl {
        ReadBuffer FrontLeft = Constants.FrontLeft;
        ReadBuffer FrontRight = Constants.FrontRight;
        ReadBuffer BackLeft = Constants.BackLeft;
        ReadBuffer BackRight = Constants.BackRight;
        ReadBuffer Front = Constants.Front;
        ReadBuffer Back = Constants.Back;
        ReadBuffer Left = Constants.Left;
        ReadBuffer Right = Constants.Right;

        static ReadBuffer ofColor(int i) {
            return ColorAttachmentImpl.of(i);
        }
    }

    public sealed interface ColorBuffer extends IntEnum permits Constants, ColorAttachmentImpl {
        ColorBuffer None = Constants.None;

        static ColorBuffer ofColor(int i) {
            return ColorAttachmentImpl.of(i);
        }
    }


    public static final class DrawBuffer extends SimpleIndexedIntEnum {
        public static final int MAX = MetaSystem.Graphics.queryInt("GET.GL_MAX_DRAW_BUFFERS");
        private static final IndexedEnumCache<DrawBuffer> cache = new IndexedEnumCache<>(MAX, DrawBuffer::new);

        private DrawBuffer(int index) {
            super(MetaSystem.Graphics.queryInt("DRAW_BUFFER" + index), index);
        }

        public static DrawBuffer of(int i) {
            return cache.valueOf(i);
        }
    }

    public enum Mask {
        Color("COLOR_BUFFER_BIT"), //
        Depth("DEPTH_BUFFER_BIT"), //
        Stencil("STENCIL_BUFFER_BIT"),
        ColorAndDepth(Color, Depth),
        ColorAndStencil(Color, Stencil),
        DepthAndStencil(Depth, Stencil),
        All(Color, Depth, Stencil);

        private final int value;

        Mask(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        Mask(Mask... buffers) {
            int v = 0;
            for (var buf : buffers) {
                v |= buf.value;
            }
            this.value = v;
        }

        public int value() {
            return value;
        }
    }

    static final class ColorAttachmentImpl extends SimpleIndexedIntEnum implements
            ColorAttachment, ReadBuffer, ColorBuffer, MultiSelectableDrawBuffer {
        private static final IndexedEnumCache<ColorAttachmentImpl> cache = new IndexedEnumCache<>(MAX_COLOR_ATTACHMENTS, ColorAttachmentImpl::new);

        private ColorAttachmentImpl(int index) {
            super(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT" + index), index);
        }

        static ColorAttachmentImpl of(int i) {
            return cache.valueOf(i);
        }
    }

    enum Constants implements
            Attachment,
            ReadBuffer,
            MultiSelectableDrawBuffer,
            DefaultFrameBuffer.DrawBuffer,
            DefaultFrameBuffer.Invalidatable,
            ColorBuffer {
        None("NONE"),
        FrontLeft("FRONT_LEFT"),
        FrontRight("FRONT_RIGHT"),
        BackLeft("BACK_LEFT"),
        BackRight("BACK_RIGHT"),
        Front("FRONT"),
        Back("BACK"),
        Left("LEFT"),
        Right("RIGHT"),
        FrontAndBack("FRONT_AND_BACK"),
        Color("COLOR"),
        Depth("DEPTH"),
        Stencil("STENCIL"),
        DepthAttachment("DEPTH_ATTACHMENT"),
        StencilAttachment("STENCIL_ATTACHMENT"),
        DepthStencilAttachment("DEPTH_STENCIL_ATTACHMENT");
        private final int value;

        Constants(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

}
