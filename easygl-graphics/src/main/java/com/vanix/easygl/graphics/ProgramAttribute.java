package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Support;

public sealed interface ProgramAttribute permits ProgramAttribute.Bool, ProgramAttribute.Int {
    Bool DeleteStatus = ProgramAttributeImpl.DeleteStatus;
    Bool LinkStatus = ProgramAttributeImpl.LinkStatus;
    Bool ValidateStatus = ProgramAttributeImpl.ValidateStatus;
    Int InfoLogLength = ProgramAttributeImpl.InfoLogLength;
    Int AttachedShaders = ProgramAttributeImpl.AttachedShaders;
    Int ActiveAtomicCounterBuffers = ProgramAttributeImpl.ActiveAtomicCounterBuffers;
    Int ActiveAttributes = ProgramAttributeImpl.ActiveAttributes;
    Int ActiveAttributeMaxLength = ProgramAttributeImpl.ActiveAttributeMaxLength;
    Int ActiveUniforms = ProgramAttributeImpl.ActiveUniforms;
    @Support(since = Version.GL31)
    Int ActiveUniformBlocks = ProgramAttributeImpl.ActiveUniformBlocks;
    @Support(since = Version.GL31)
    Int ActiveUniformBlockMaxNameLength = ProgramAttributeImpl.ActiveUniformBlockMaxNameLength;
    Int ActiveUniformMaxLength = ProgramAttributeImpl.ActiveUniformMaxLength;
    @Support(since = Version.GL43)
    Int ComputeWorkGroupSize = ProgramAttributeImpl.ComputeWorkGroupSize;
    Int ProgramBinaryLength = ProgramAttributeImpl.ProgramBinaryLength;
    Int TransformFeedbackBufferMode = ProgramAttributeImpl.TransformFeedbackBufferMode;
    Int TransformFeedbackVaryings = ProgramAttributeImpl.TransformFeedbackVaryings;
    Int TransformFeedbackVaryingMaxLength = ProgramAttributeImpl.TransformFeedbackVaryingMaxLength;
    @Support(since = Version.GL32)
    Int GeometryVerticesOut = ProgramAttributeImpl.GeometryVerticesOut;
    @Support(since = Version.GL32)
    Int GeometryInputType = ProgramAttributeImpl.GeometryInputType;
    @Support(since = Version.GL32)
    Int GeometryOutputType = ProgramAttributeImpl.GeometryOutputType;

    static ProgramAttribute[] values() {
        return ProgramAttributeImpl.values();
    }

    sealed interface Bool extends ProgramAttribute, IntEnum permits ProgramAttributeImpl {

    }

    sealed interface Int extends ProgramAttribute, IntEnum permits ProgramAttributeImpl {

    }
}
