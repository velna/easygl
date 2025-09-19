package com.vanix.easygl.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum DrawMode {
    Points("POINTS"),
    LineStrip("LINE_STRIP"),
    LineLoop("LINE_LOOP"),
    Lines("LINES"),
    LineStripAdjacency("LINE_STRIP_ADJACENCY"),
    LinesAdjacency("LINES_ADJACENCY"),
    TriangleStrip("TRIANGLE_STRIP"),
    TriangleFan("TRIANGLE_FAN"),
    Triangles("TRIANGLES"),
    TriangleStripAdjacency("TRIANGLE_STRIP_ADJACENCY"),
    TrianglesAdjacency("TRIANGLES_ADJACENCY"),
    Patches("PATCHES");
    private final int value;

    DrawMode(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
