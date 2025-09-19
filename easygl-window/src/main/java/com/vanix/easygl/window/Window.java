package com.vanix.easygl.window;

import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.primitives.Rectanglei;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.window.event.*;
import com.vanix.easygl.window.input.InputController;
import org.joml.Vector2f;

public interface Window extends Bindable<BindTarget.Default<Window>, Window>, Rectanglei<Window> {
    BindableMeta<BindTarget.Default<Window>, Window> Meta = MetaSystem.Window.of(Window.class);
    BindTarget.Default<Window> Target = new BindTarget.Default<>("Window", Meta);

    String getTitle();

    void setTitle(String title);

    boolean shouldClose();

    void shouldClose(boolean close);

    InputController inputs();

    ListenerOperation<WindowResizeListener> onResize();

    ListenerOperation<WindowRefreshListener> onRefresh();

    ListenerOperation<WindowPositionListener> onPosition();

    ListenerOperation<WindowCloseListener> onClose();

    ListenerOperation<WindowContentScaleListener> onContentScale();

    ListenerOperation<WindowFocusListener> onFocus();

    ListenerOperation<WindowIconifyListener> onIconify();

    ListenerOperation<WindowMaximizeListener> onMaximize();

    ListenerOperation<WindowFpsListener> onFpsUpdate();

    Window swapBuffers();

    Window pollEvents();

    static Window of(int width, int height, String title) {
        return Meta.create((Object) width, height, title);
    }

    int frameBufferWidth();

    int frameBufferHeight();

    Window setX(int x);

    Window setY(int x);

    default float getAspect() {
        return getWidth() * 1.0f / getHeight();
    }

    Window focus();

    Window hide();

    Window show();

    float opacity();

    Window opacity(float value);

    Window iconify();

    Window maximize();

    Window requestAttention();

    Window restore();

    Vector2f contentScale();

    Monitor monitor();

    Window monitor(Monitor monitor, int x, int y, int width, int height, int refreshRate);

    String clipboard();

    Window clipboard(String value);

    WindowAttributes attributes();

}
