package com.vanix.easygl.opengl.program;

import com.vanix.easygl.graphics.Program;

public class BaseGl31Resource {
    protected final Program program;
    protected final int index;
    protected final String name;

    public BaseGl31Resource(Program program, int index, String name) {
        this.program = program;
        this.index = index;
        this.name = name;
    }

    public Program program() {
        return program;
    }

    public int index() {
        return index;
    }

    public String getName() {
        return name;
    }
}
