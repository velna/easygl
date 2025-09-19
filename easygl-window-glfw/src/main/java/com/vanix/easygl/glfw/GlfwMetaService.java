package com.vanix.easygl.glfw;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.meta.*;
import com.vanix.easygl.window.Monitor;
import com.vanix.easygl.window.Window;
import com.vanix.easygl.window.WindowException;
import com.vanix.easygl.window.WindowHint;
import com.vanix.easygl.window.event.MonitorListener;
import com.vanix.easygl.window.input.Cursor;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.util.function.Function;

import static org.lwjgl.glfw.GLFW.*;

@Slf4j
@SystemName("Window")
public class GlfwMetaService extends AbstractMetaService {
    private final int platform;
    static final BindableMeta<BindTarget.Default<Window>, Window> WindowMeta = new LongBindableMeta<>(
            args -> new GlWindow((Integer) args[0], (Integer) args[1], (String) args[2]),
            (target, h) -> glfwMakeContextCurrent(h),
            (target, h) -> glfwMakeContextCurrent(h),
            0L
    );

    public GlfwMetaService() {
        if (!glfwInit()) {
            throw new WindowException("glfwInit error.");
        }
        platform = glfwGetPlatform();
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_API);
        glfwWindowHint(GLFW_CONTEXT_CREATION_API, GLFW_NATIVE_CONTEXT_API);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwSetErrorCallback((error, description) ->
                log.error("GLFW error({}): ", error, new WindowException(GLFWErrorCallback.getDescription(description))));
        GLFW.glfwSetMonitorCallback(GlMonitor::fireMonitorOnConnection);
        register(Window.class, WindowMeta);
        register(WindowHint.IntHint.class,
                (Function<Object[], ?>) args -> new GlWindowHint.GlIntHint((Integer) args[0]));
        register(WindowHint.BooleanHint.class,
                (Function<Object[], ?>) args -> new GlWindowHint.GlBooleanHint((Integer) args[0]));
        register(WindowHint.StringHint.class,
                (Function<Object[], ?>) args -> new GlWindowHint.GlStringHint((Integer) args[0]));
        register(MonitorListener.class, (Function<Object[], ?>) args -> GlMonitor.MonitorListeners.listen());
        register(Monitor[].class, (Function<Object[], ?>) args -> GlMonitor.monitors());
        register(Monitor.class, (Function<Object[], ?>) args -> GlMonitor.primary());
        register(Cursor.class, (Function<Object[], ?>) args -> new GlCursor((Image) args[0], (Integer) args[1], (Integer) args[2]));
        register(Cursor.StandardShape.class, (Function<Object[], ?>) args -> new GlCursor((Cursor.StandardShape) args[0]));
    }

    @Override
    public int queryInt(String id) {
        return queryStaticIntField(GLFW.class, "GLFW_", id).orElseThrow();
    }

    @Override
    public Object[] queryArray(String id) {
        if (id.startsWith("KEY_")) {
            int keyCode = queryInt(id);
            int scancode = GLFW.glfwGetKeyScancode(keyCode);
            // glfwGetKeyName crashes in wayland environment
            String keyName = platform == GLFW_PLATFORM_WAYLAND ? "" : GLFW.glfwGetKeyName(keyCode, scancode);
            return new Object[]{keyCode, scancode, keyName};
        }
        return new Object[0];
    }

    @Override
    public ErrorCode getError() {
        return Cache.errorCodeOf(GLFW.glfwGetError(null));
    }

    @Override
    public void close() {
        GLFW.glfwTerminate();
    }
}
