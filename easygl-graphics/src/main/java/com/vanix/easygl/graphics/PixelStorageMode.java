package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.attr.UpdatableBooleanAttribute;
import com.vanix.easygl.commons.attr.UpdatableIntAttribute;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.core.meta.MetaSystemAttributesEnum;

public abstract class PixelStorageMode extends MetaSystemAttributesEnum<PixelStorageMode, Graphics> {
    public final UpdatableBooleanAttribute<PixelStorageMode> PackSwapBytes = ofUpdatableBoolean("PACK_SWAP_BYTES");
    public final UpdatableBooleanAttribute<PixelStorageMode> PackLsbFirst = ofUpdatableBoolean("PACK_LSB_FIRST");
    public final UpdatableIntAttribute<PixelStorageMode> PackRowLength = ofUpdatableInt("PACK_ROW_LENGTH");
    public final UpdatableIntAttribute<PixelStorageMode> PackImageHeight = ofUpdatableInt("PACK_IMAGE_HEIGHT");
    public final UpdatableIntAttribute<PixelStorageMode> PackSkipPixels = ofUpdatableInt("PACK_SKIP_PIXELS");
    public final UpdatableIntAttribute<PixelStorageMode> PackSkipRows = ofUpdatableInt("PACK_SKIP_ROWS");
    public final UpdatableIntAttribute<PixelStorageMode> PackSkipImages = ofUpdatableInt("PACK_SKIP_IMAGES");
    public final UpdatableIntAttribute<PixelStorageMode> PackAlignment = ofUpdatableInt("PACK_ALIGNMENT");
    public final UpdatableIntAttribute<PixelStorageMode> UnpackSwapBytes = ofUpdatableInt("UNPACK_SWAP_BYTES");
    public final UpdatableBooleanAttribute<PixelStorageMode> UnpackLsbFirst = ofUpdatableBoolean("UNPACK_LSB_FIRST");
    public final UpdatableBooleanAttribute<PixelStorageMode> UnpackRowLength = ofUpdatableBoolean("UNPACK_ROW_LENGTH");
    public final UpdatableIntAttribute<PixelStorageMode> UnpackImageHeight = ofUpdatableInt("UNPACK_IMAGE_HEIGHT");
    public final UpdatableIntAttribute<PixelStorageMode> UnpackSkipPixels = ofUpdatableInt("UNPACK_SKIP_PIXELS");
    public final UpdatableIntAttribute<PixelStorageMode> UnpackSkipRows = ofUpdatableInt("UNPACK_SKIP_ROWS");
    public final UpdatableIntAttribute<PixelStorageMode> UnpackSkipImages = ofUpdatableInt("UNPACK_SKIP_IMAGES");
    public final UpdatableIntAttribute<PixelStorageMode> UnpackAlignment = ofUpdatableInt("UNPACK_ALIGNMENT");

    protected PixelStorageMode(Graphics graphics) {
        super(MetaSystem.Graphics, graphics);
    }

}
