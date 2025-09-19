package com.vanix.easygl.window;

import com.vanix.easygl.core.meta.ErrorCode;
import com.vanix.easygl.core.meta.MetaSystem;

public enum WindowErrorCode implements ErrorCode {
    UnKnown(-1),
    NoError("NO_ERROR"),
    NotInitialized("NOT_INITIALIZED"),
    NoCurrentContext("NO_CURRENT_CONTEXT"),
    InvalidEnum("INVALID_ENUM"),
    InvalidValue("INVALID_VALUE"),
    OutOfMemory("OUT_OF_MEMORY"),
    ApiUnavailable("API_UNAVAILABLE"),
    VersionUnavailable("VERSION_UNAVAILABLE"),
    PlatformError("PLATFORM_ERROR"),
    FormatUnavailable("FORMAT_UNAVAILABLE"),
    NoWindowContext("NO_WINDOW_CONTEXT"),
    CursorUnavailable("CURSOR_UNAVAILABLE"),
    FeatureUnavailable("FEATURE_UNAVAILABLE"),
    FeatureUnimplemented("FEATURE_UNIMPLEMENTED"),
    PlatformUnavailable("PLATFORM_UNAVAILABLE");

    private final int code;

    WindowErrorCode(int code) {
        this.code = code;
    }

    WindowErrorCode(String id) {
        this(MetaSystem.Window.queryInt(id));
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public boolean isError() {
        return this != NoError;
    }

    @Override
    public String description() {
        return name();
    }

    public static WindowErrorCode get() {
        return (WindowErrorCode) MetaSystem.Window.getError();
    }
}
