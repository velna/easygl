package com.vanix.easygl.graphics.feature;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.graphics.Version;
import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.IntBuffer;
import java.util.List;

@Support(since = Version.GL43)
public interface Debug extends GraphicsFeature<Debug> {
    Debug setMessageCallback(Callback callback, long userParam);

    Debug setMessageControl(Source source, Type type, Severity severity, int id, boolean enable);

    Debug setMessageControl(Source source, Type type, Severity severity, int[] ids, boolean enable);

    Debug setMessageControl(Source source, Type type, Severity severity, IntBuffer ids, boolean enable);

    Debug insertMessage(UserSource source, Type type, int id, Severity severity, String message);

    Debug pushGroup(UserSource source, int id, String message);

    Debug popGroup();

    Debug setSynchronized(boolean syn);

    boolean isSynchronized();

    List<MessageLog> getMessageLog(int count);

    interface Callback {
        void debugOnMessage(Source source, Type type, int id, Severity severity, String message, long userParam);
    }

    record MessageLog(Source source, Type type, int id, Severity severity, String message) {
    }


    enum Source implements IntEnum {
        API("DEBUG_SOURCE_API"),
        WINDOW_SYSTEM("DEBUG_SOURCE_WINDOW_SYSTEM"),
        SHADER_COMPILER("DEBUG_SOURCE_SHADER_COMPILER"),
        THIRD_PARTY("DEBUG_SOURCE_THIRD_PARTY"),
        APPLICATION("DEBUG_SOURCE_APPLICATION"),
        OTHER("DEBUG_SOURCE_OTHER");
        private final int value;

        Source(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum UserSource implements IntEnum {
        THIRD_PARTY(Source.THIRD_PARTY),
        APPLICATION(Source.APPLICATION);
        private final int value;

        UserSource(Source source) {
            this.value = source.value;
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum Type implements IntEnum {
        ERROR("DEBUG_TYPE_ERROR"),
        DEPRECATED_BEHAVIOR("DEBUG_TYPE_DEPRECATED_BEHAVIOR"),
        UNDEFINED_BEHAVIOR("DEBUG_TYPE_UNDEFINED_BEHAVIOR"),
        PORTABILITY("DEBUG_TYPE_PORTABILITY"),
        PERFORMANCE("DEBUG_TYPE_PERFORMANCE"),
        OTHER("DEBUG_TYPE_OTHER"),
        MARKER("DEBUG_TYPE_MARKER"),
        PUSH_GROUP("DEBUG_TYPE_PUSH_GROUP"),
        POP_GROUP("DEBUG_TYPE_POP_GROUP");
        private final int value;

        Type(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum Severity implements IntEnum {
        HIGH("DEBUG_SEVERITY_HIGH"),
        MEDIUM("DEBUG_SEVERITY_MEDIUM"),
        LOW("DEBUG_SEVERITY_LOW"),
        NOTIFICATION("DEBUG_SEVERITY_NOTIFICATION");
        private final int value;

        Severity(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }
}
