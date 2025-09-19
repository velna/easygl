package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.opengl.Cache;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.feature.Debug;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlDebug extends GlFeature<Debug> implements Debug {
    private static final int MAX_MESSAGE_LEN = GLX.glGetInteger(GLX.GL_MAX_DEBUG_MESSAGE_LENGTH);

    public GlDebug(Graphics graphics) {
        super(GLX.GL_DEBUG_OUTPUT, graphics);
    }

    @Override
    public Debug setMessageCallback(Callback callback, long userParam) {
        GLX.glDebugMessageCallback((source, type, id, severity, length, message, up) ->
                callback.debugOnMessage(
                        Cache.DebugSource.valueOf(source),
                        Cache.DebugType.valueOf(type),
                        id,
                        Cache.DebugSeverity.valueOf(severity),
                        MemoryUtil.memUTF8(message, length),
                        up), userParam);
        return this;
    }

    @Override
    public Debug setMessageControl(Source source, Type type, Severity severity, int id, boolean enable) {
        GLX.glDebugMessageControl(source.value(), type.value(), severity.value(), id, enable);
        return this;
    }

    @Override
    public Debug setMessageControl(Source source, Type type, Severity severity, int[] ids, boolean enable) {
        GLX.glDebugMessageControl(source.value(), type.value(), severity.value(), ids, enable);
        return this;
    }

    @Override
    public Debug setMessageControl(Source source, Type type, Severity severity, IntBuffer ids, boolean enable) {
        GLX.glDebugMessageControl(source.value(), type.value(), severity.value(), ids, enable);
        return this;
    }

    @Override
    public Debug insertMessage(UserSource source, Type type, int id, Severity severity, String message) {
        GLX.glDebugMessageInsert(source.value(), type.value(), id, severity.value(), message);
        return this;
    }

    @Override
    public Debug pushGroup(UserSource source, int id, String message) {
        GLX.glPushDebugGroup(source.value(), id, message);
        return this;
    }

    @Override
    public Debug popGroup() {
        GLX.glPopDebugGroup();
        return this;
    }

    @Override
    public Debug setSynchronized(boolean syn) {
        if (syn) {
            GLX.glEnable(GLX.GL_DEBUG_OUTPUT_SYNCHRONOUS);
        } else {
            GLX.glDisable(GLX.GL_DEBUG_OUTPUT_SYNCHRONOUS);
        }
        return this;
    }

    @Override
    public boolean isSynchronized() {
        return GLX.glIsEnabled(GLX.GL_DEBUG_OUTPUT_SYNCHRONOUS);
    }

    @Override
    public List<MessageLog> getMessageLog(int count) {
        try (var stack = MemoryStack.stackPush()) {
            int[] sources = new int[count];
            int[] types = new int[count];
            int[] ids = new int[count];
            int[] severities = new int[count];
            int[] lengths = new int[count];
            ByteBuffer buffer = stack.malloc(MAX_MESSAGE_LEN * count);
            int n = GLX.glGetDebugMessageLog(count, sources, types, ids, severities, lengths, buffer);
            if (n <= 0) {
                return Collections.emptyList();
            }
            var list = new ArrayList<MessageLog>(count);
            for (int i = 0; i < n; i++) {
                list.add(new MessageLog(
                        Cache.DebugSource.valueOf(sources[i]),
                        Cache.DebugType.valueOf(types[i]),
                        ids[i],
                        Cache.DebugSeverity.valueOf(severities[i]),
                        MemoryUtil.memUTF8(buffer, lengths[i])
                ));
                buffer.position(buffer.position() + lengths[i]);
            }
            return list;
        }
    }
}
