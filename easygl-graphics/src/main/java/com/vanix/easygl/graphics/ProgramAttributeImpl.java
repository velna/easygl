package com.vanix.easygl.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

enum ProgramAttributeImpl implements ProgramAttribute.Bool, ProgramAttribute.Int {
    DeleteStatus("DELETE_STATUS"),
    LinkStatus("LINK_STATUS"),
    ValidateStatus("VALIDATE_STATUS"),
    InfoLogLength("INFO_LOG_LENGTH"),
    AttachedShaders("ATTACHED_SHADERS"),
    ActiveAtomicCounterBuffers("ACTIVE_ATOMIC_COUNTER_BUFFERS"),
    ActiveAttributes("ACTIVE_ATTRIBUTES"),
    ActiveAttributeMaxLength("ACTIVE_ATTRIBUTE_MAX_LENGTH"),
    ActiveUniforms("ACTIVE_UNIFORMS"),
    ActiveUniformBlocks("ACTIVE_UNIFORM_BLOCKS"),
    ActiveUniformBlockMaxNameLength("ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH"),
    ActiveUniformMaxLength("ACTIVE_UNIFORM_MAX_LENGTH"),
    ComputeWorkGroupSize("COMPUTE_WORK_GROUP_SIZE"),
    ProgramBinaryLength("PROGRAM_BINARY_LENGTH"),
    TransformFeedbackBufferMode("TRANSFORM_FEEDBACK_BUFFER_MODE"),
    TransformFeedbackVaryings("TRANSFORM_FEEDBACK_VARYINGS"),
    TransformFeedbackVaryingMaxLength("TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH"),
    GeometryVerticesOut("GEOMETRY_VERTICES_OUT"),
    GeometryInputType("GEOMETRY_INPUT_TYPE"),
    GeometryOutputType("GEOMETRY_OUTPUT_TYPE");
    private final int value;

    ProgramAttributeImpl(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
