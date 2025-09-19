package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractHandle;
import com.vanix.easygl.graphics.Sync;

import java.util.concurrent.TimeUnit;

public class GlSync extends AbstractHandle implements Sync {

    public GlSync() {
        this(GLX.glFenceSync(GLX.GL_SYNC_GPU_COMMANDS_COMPLETE, 0));
    }

    public GlSync(long handle) {
        super(handle, GLX::glDeleteSync);
    }

    @Override
    public Sync awaitServer() {
        GLX.glWaitSync(handle, 0, GLX.GL_TIMEOUT_IGNORED);
        return this;
    }

    @Override
    public Result awaitClient(int timeout, TimeUnit timeUnit, boolean flush) {
        int code = GLX.glClientWaitSync(handle, flush ? GLX.GL_SYNC_FLUSH_COMMANDS_BIT : 0, timeUnit.toNanos(timeout));
        return Cache.SyncResult.valueOf(code);
    }

    @Override
    public boolean isSignaled() {
        return GLX.glGetSynci(handle, GLX.GL_SYNC_STATUS, null) == GLX.GL_SIGNALED;
    }
}
