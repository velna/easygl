package com.vanix.easygl.window.input;

import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Cursor extends Handle {

    static Cursor of(StandardShape standardShape) {
        return MetaSystem.Window.of(StandardShape.class, standardShape.value);
    }

    static Cursor of(Image image, int xHotspot, int yHotspot) {
        return MetaSystem.Window.of(Cursor.class, image, xHotspot, yHotspot);
    }

    enum StandardShape {
        ARROW("ARROW_CURSOR"),
        I_BEAM("IBEAM_CURSOR"),
        CROSS_HAIR("CROSSHAIR_CURSOR"),
        POINTING_HAND("POINTING_HAND_CURSOR"),
        RESIZE_EW("RESIZE_EW_CURSOR"),
        RESIZE_NS("RESIZE_NS_CURSOR"),
        RESIZE_NWSE("RESIZE_NWSE_CURSOR"),
        RESIZE_NESW("RESIZE_NESW_CURSOR"),
        RESIZE_ALL("RESIZE_ALL_CURSOR"),
        NOT_ALLOWED("NOT_ALLOWED_CURSOR");
        private final int value;

        StandardShape(String id) {
            value = MetaSystem.Window.queryInt(id);
        }

        public int value() {
            return value;
        }
    }
}
