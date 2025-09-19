package com.vanix.easygl.window;

import com.vanix.easygl.commons.attr.BooleanAttribute;
import com.vanix.easygl.commons.attr.UpdatableBooleanAttribute;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.core.meta.MetaSystemAttributesEnum;

public abstract class WindowAttributes extends MetaSystemAttributesEnum<WindowAttributes, Window> {

    public final BooleanAttribute Focused = ofBoolean("FOCUSED");

    public final BooleanAttribute Iconified = ofBoolean("ICONIFIED");

    public final UpdatableBooleanAttribute<WindowAttributes> Resizable = ofUpdatableBoolean("RESIZABLE");

    public final BooleanAttribute Visible = ofBoolean("VISIBLE");

    public final UpdatableBooleanAttribute<WindowAttributes> Decorated = ofUpdatableBoolean("DECORATED");

    public final UpdatableBooleanAttribute<WindowAttributes> AutoIconify = ofUpdatableBoolean("AUTO_ICONIFY");

    public final UpdatableBooleanAttribute<WindowAttributes> Floating = ofUpdatableBoolean("FLOATING");

    public final BooleanAttribute Maximized = ofBoolean("MAXIMIZED");

    public final BooleanAttribute CenterCursor = ofBoolean("CENTER_CURSOR");

    public final BooleanAttribute TransparentFramebuffer = ofBoolean("TRANSPARENT_FRAMEBUFFER");

    public final BooleanAttribute Hovered = ofBoolean("HOVERED");

    public final UpdatableBooleanAttribute<WindowAttributes> FocusOnShow = ofUpdatableBoolean("FOCUS_ON_SHOW");

    public final BooleanAttribute MousePassThrough = ofBoolean("MOUSE_PASSTHROUGH");

    public final BooleanAttribute PositionX = ofBoolean("POSITION_X");

    public final BooleanAttribute PositionY = ofBoolean("POSITION_Y");

    protected WindowAttributes(Window window) {
        super(MetaSystem.Window, window);
    }
}
