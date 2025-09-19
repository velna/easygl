package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

import java.util.concurrent.TimeUnit;

@Support(since = Version.GL32)
public interface Sync extends Handle {

    @Support(since = Version.GL32)
    Sync awaitServer();

    @Support(since = Version.GL32)
    default Result awaitClient(int timeout, TimeUnit timeUnit) {
        return awaitClient(timeout, timeUnit, false);
    }

    @Support(since = Version.GL32)
    Result awaitClient(int timeout, TimeUnit timeUnit, boolean flush);

    boolean isSignaled();

    enum Result implements IntEnum {
        ALREADY_SIGNALED("ALREADY_SIGNALED"),
        TIMEOUT_EXPIRED("TIMEOUT_EXPIRED"),
        CONDITION_SATISFIED("CONDITION_SATISFIED"),
        WAIT_FAILED("WAIT_FAILED");
        private final int value;

        Result(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    static Sync of() {
        return MetaHolder.Sync.create();
    }
}
