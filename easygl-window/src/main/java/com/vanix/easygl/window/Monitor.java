package com.vanix.easygl.window;

import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.primitives.Positionic;
import com.vanix.easygl.commons.primitives.Rectanglei;
import com.vanix.easygl.commons.primitives.Rectangleic;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.window.event.MonitorListener;
import org.joml.Vector2f;

import javax.annotation.Nullable;
import java.util.List;

public interface Monitor extends Rectangleic<Monitor>, Handle {

    String name();

    Vector2f contentScale();

    Rectanglei<?> workArea();

    Positionic<?> getPosition();

    @Nullable
    VideoMode videoMode();

    List<VideoMode> supportedVideoModes();

    Monitor gamma(float value);

    Monitor gamma(GammaRamp gammaRamp);

    @Nullable
    GammaRamp gamma();

    static Monitor primary() {
        return MetaSystem.Window.of(Monitor.class);
    }

    static List<Monitor> list() {
        return MetaSystem.Window.of(Monitor[].class);
    }

    static ListenerOperation<MonitorListener> onConnection() {
        return MetaSystem.Window.of(MonitorListener.class);
    }
}
