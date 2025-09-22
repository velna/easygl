package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramResource;
import com.vanix.easygl.graphics.Shader;

public interface SubroutineUniform<T extends SubroutineUniform<T>> extends
        ProgramResource.Named<T>,
        ProgramResource.ArraySize<T>,
        ProgramResource.NumCompatibleSubroutines<T>,
        ProgramResource.CompatibleSubroutines<T>,
        ProgramResource.Location<T> {
    Shader.Type shaderStage();
}
