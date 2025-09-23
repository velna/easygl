package com.vanix.easygl.graphics.program;

import com.vanix.easygl.graphics.ProgramResource;
import com.vanix.easygl.graphics.Shader;

public interface SubroutineUniform extends
        ProgramResource.Named<SubroutineUniform>,
        ProgramResource.ArraySize<SubroutineUniform>,
        ProgramResource.NumCompatibleSubroutines<SubroutineUniform>,
        ProgramResource.CompatibleSubroutines<SubroutineUniform>,
        ProgramResource.Location<SubroutineUniform> {
    Shader.Type shaderStage();
}
