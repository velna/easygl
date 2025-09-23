package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramInterface;

public interface SubroutineUniformInterface extends ProgramInterface.Named<SubroutineUniform> {
    int getMaxNumCompatibleSubroutines();
}
