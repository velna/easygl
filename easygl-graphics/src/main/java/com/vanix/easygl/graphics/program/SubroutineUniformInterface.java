package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramInterface;

import java.util.List;

public interface SubroutineUniformInterface extends ProgramInterface.Named<SubroutineUniform> {
    int getMaxNumCompatibleSubroutines();

    SubroutineUniformInterface load(List<Subroutine> subroutines);

}
