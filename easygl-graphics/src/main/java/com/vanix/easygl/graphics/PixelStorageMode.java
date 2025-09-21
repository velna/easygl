package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.attr.UpdatableAnyPrimitiveAttribute;
import com.vanix.easygl.commons.attr.UpdatableBooleanAttribute;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.core.meta.MetaSystemAttributesEnum;

public abstract class PixelStorageMode extends MetaSystemAttributesEnum<PixelStorageMode, Graphics> {
    public final UpdatableBooleanAttribute<PixelStorageMode> PackSwapBytes = ofUpdatableBoolean("PACK_SWAP_BYTES");
    public final UpdatableBooleanAttribute<PixelStorageMode> PackLsbFirst = ofUpdatableBoolean("PACK_LSB_FIRST");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> PackRowLength = ofUpdatableAny("PACK_ROW_LENGTH");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> PackImageHeight = ofUpdatableAny("PACK_IMAGE_HEIGHT");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> PackSkipPixels = ofUpdatableAny("PACK_SKIP_PIXELS");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> PackSkipRows = ofUpdatableAny("PACK_SKIP_ROWS");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> PackSkipImages = ofUpdatableAny("PACK_SKIP_IMAGES");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> PackAlignment = ofUpdatableAny("PACK_ALIGNMENT");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> UnpackSwapBytes = ofUpdatableAny("UNPACK_SWAP_BYTES");
    public final UpdatableBooleanAttribute<PixelStorageMode> UnpackLsbFirst = ofUpdatableBoolean("UNPACK_LSB_FIRST");
    public final UpdatableBooleanAttribute<PixelStorageMode> UnpackRowLength = ofUpdatableBoolean("UNPACK_ROW_LENGTH");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> UnpackImageHeight = ofUpdatableAny("UNPACK_IMAGE_HEIGHT");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> UnpackSkipPixels = ofUpdatableAny("UNPACK_SKIP_PIXELS");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> UnpackSkipRows = ofUpdatableAny("UNPACK_SKIP_ROWS");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> UnpackSkipImages = ofUpdatableAny("UNPACK_SKIP_IMAGES");
    public final UpdatableAnyPrimitiveAttribute<PixelStorageMode> UnpackAlignment = ofUpdatableAny("UNPACK_ALIGNMENT");

    protected PixelStorageMode(Graphics graphics) {
        super(MetaSystem.Graphics, graphics);
    }

}
