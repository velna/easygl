package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

public enum MapAccessBits implements IntEnum {
    Read("MAP_READ_BIT"),
    Write("MAP_WRITE_BIT"),
    @Support(since = Version.GL44)
    PersistentRead("MAP_PERSISTENT_BIT", Read),
    @Support(since = Version.GL44)
    PersistentWrite("MAP_PERSISTENT_BIT", Write),
    @Support(since = Version.GL44)
    Coherent("MAP_COHERENT_BIT", "MAP_PERSISTENT_BIT"),
    InvalidateRange("MAP_INVALIDATE_RANGE_BIT"),
    InvalidateBuffer("MAP_INVALIDATE_BUFFER_BIT"),
    FlushExplicit("MAP_FLUSH_EXPLICIT_BIT"),
    UnSynchronized("MAP_UNSYNCHRONIZED_BIT");
    private final int value;

    MapAccessBits(String id, MapAccessBits ma2) {
        this.value = MetaSystem.Graphics.queryInt(id) | ma2.value;
    }

    MapAccessBits(String id1, String id2) {
        this.value = MetaSystem.Graphics.queryInt(id1) | MetaSystem.Graphics.queryInt(id2);
    }

    MapAccessBits(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
