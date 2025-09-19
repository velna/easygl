package com.vanix.easygl.graphics;

import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.ErrorCode;
import com.vanix.easygl.core.meta.MetaSystem;

public enum GraphicsErrorCode implements ErrorCode {
    Unknown(-1),
    NoError("NO_ERROR"),
    InvalidEnum("INVALID_ENUM"),
    InvalidValue("INVALID_VALUE"),
    InvalidOperation("INVALID_OPERATION"),
    StackOverflow("STACK_OVERFLOW"),
    StackUnderflow("STACK_UNDERFLOW"),
    @Support(since = Version.GL30)
    InvalidFramebufferOperation("INVALID_FRAMEBUFFER_OPERATION"),
    OutOfMemory("OUT_OF_MEMORY");

    private final int code;

    GraphicsErrorCode(int code) {
        this.code = code;
    }

    GraphicsErrorCode(String id) {
        this(MetaSystem.Graphics.queryInt(id));
    }

    @Override
    public boolean isError() {
        return this != NoError;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String description() {
        return name();
    }

    public static GraphicsErrorCode get() {
        return (GraphicsErrorCode) MetaSystem.Graphics.getError();
    }
}
