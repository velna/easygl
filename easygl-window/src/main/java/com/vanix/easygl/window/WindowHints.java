package com.vanix.easygl.window;

import com.vanix.easygl.core.meta.MetaSystem;

import static com.vanix.easygl.window.WindowHint.*;

public interface WindowHints {

    WindowHint.BooleanHint
            Resizable = ofBoolean("RESIZABLE"),
            Visible = ofBoolean("VISIBLE"),
            Decorated = ofBoolean("DECORATED"),
            Focused = ofBoolean("FOCUSED"),
            AutoIconify = ofBoolean("AUTO_ICONIFY"),
            Floating = ofBoolean("FLOATING"),
            Maximized = ofBoolean("MAXIMIZED"),
            CenterCursor = ofBoolean("CENTER_CURSOR"),
            TransparentFrameBuffer = ofBoolean("TRANSPARENT_FRAMEBUFFER"),
            FocusOnShow = ofBoolean("FOCUS_ON_SHOW"),
            ScaleToMonitor = ofBoolean("SCALE_TO_MONITOR"),
            MousePassThrough = ofBoolean("MOUSE_PASSTHROUGH"),
            Stereo = ofBoolean("STEREO"),
            SRGBCapable = ofBoolean("SRGB_CAPABLE"),
            DoubleBuffer = ofBoolean("DOUBLEBUFFER"),
            ContextNoError = ofBoolean("CONTEXT_NO_ERROR"),
            OpenglForwardCompat = ofBoolean("OPENGL_FORWARD_COMPAT"),
            OpenglDebugContext = ofBoolean("OPENGL_DEBUG_CONTEXT"),
            Win32KeyboardMenu = ofBoolean("WIN32_KEYBOARD_MENU"),
            CocoaRetinaFrameBuffer = ofBoolean("COCOA_RETINA_FRAMEBUFFER"),
            CocoaGraphicsSwitching = ofBoolean("COCOA_GRAPHICS_SWITCHING");

    WindowHint.IntHint
            RedBits = ofInt("RED_BITS"),
            GreenBits = ofInt("GREEN_BITS"),
            BlueBits = ofInt("BLUE_BITS"),
            AlphaBits = ofInt("ALPHA_BITS"),
            DepthBits = ofInt("DEPTH_BITS"),
            StencilBits = ofInt("STENCIL_BITS"),
            AccumRedBits = ofInt("ACCUM_RED_BITS"),
            AccumGreenBits = ofInt("ACCUM_GREEN_BITS"),
            AccumBlueBits = ofInt("ACCUM_BLUE_BITS"),
            AccumAlphaBits = ofInt("ACCUM_ALPHA_BITS"),
            AuxBuffers = ofInt("AUX_BUFFERS"),
            Samples = ofInt("SAMPLES"),
            RefreshRate = ofInt("REFRESH_RATE"),
            ContextCreationApi = ofInt("CONTEXT_CREATION_API"),
            ContextVersionMajor = ofInt("CONTEXT_VERSION_MAJOR"),
            ContextVersionMinor = ofInt("CONTEXT_VERSION_MINOR"),
            ContextRobustness = ofInt("CONTEXT_ROBUSTNESS"),
            ContextReleaseBehavior = ofInt("CONTEXT_RELEASE_BEHAVIOR");

    WindowHint.StringHint
            CocoaFrameName = ofString("COCOA_FRAME_NAME"),
            X11ClassName = ofString("X11_CLASS_NAME"),
            X11InstanceName = ofString("X11_INSTANCE_NAME"),
            WaylandAppId = ofString("WAYLAND_APP_ID");

    enum OpenGlProfile implements WindowHint {
        Any("OPENGL_ANY_PROFILE"),
        Core("OPENGL_CORE_PROFILE"),
        Compat("OPENGL_COMPAT_PROFILE");
        private static final IntHint HINT = ofInt("OPENGL_PROFILE");
        private final int value;

        OpenGlProfile(String id) {
            this.value = MetaSystem.Window.queryInt(id);
        }

        @Override
        public int key() {
            return HINT.key();
        }

        public void set() {
            HINT.set(value);
        }
    }

    enum ClientApi implements WindowHint {
        No("NO_API"),
        OpenGl("OPENGL_API"),
        OpenGLEs("OPENGL_ES_API");
        private static final IntHint HINT = ofInt("CLIENT_API");
        private final int value;

        ClientApi(String id) {
            this.value = MetaSystem.Window.queryInt(id);
        }

        @Override
        public int key() {
            return HINT.key();
        }

        public void set() {
            HINT.set(value);
        }
    }
}
