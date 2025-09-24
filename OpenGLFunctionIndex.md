# API Index

所有未实现的方法使用【X】标记，绝大部分DSA（Direct State Access）都还没有实现。

| OpenGL函数                                       | EasyGL实现                                                                           |
|------------------------------------------------|------------------------------------------------------------------------------------|
| glActiveShaderProgram                          | Pipeline.activeShaderProgram()                                                     |
| glActiveTexture                                | TextureUnit.bind()                                                                 |
| glAttachShader                                 | Program.attach()                                                                   |
| glBeginConditionalRender                       | Query.SampleQuery.beginConditionalRender()                                         |
| glBeginQuery                                   | Query.begin()                                                                      |
| glBeginQueryIndexed                            | Query.IndexQuery.begin()                                                           |
| glBeginTransformFeedback                       | TransformFeedback.begin()                                                          |
| glBindAttribLocation                           | Program.bindVertexAttribute()                                                      |
| glBindBuffer                                   | Buffer.bind()                                                                      |
| glBindBufferBase                               | Buffer.bindAt()                                                                    |
| glBindBufferRange                              | Buffer.bindAt()                                                                    |
| glBindBuffersBase                              | BufferArray.bindBase()                                                             |
| glBindBuffersRange                             | BufferArray.bindBase()                                                             |
| glBindFragDataLocation                         | Program.bindFragData()                                                             |
| glBindFragDataLocationIndexed                  | Program.bindFragData()                                                             |
| glBindFramebuffer                              | BaseFrameBuffer.bind()                                                             |
| glBindImageTexture                             | ImageUnit.bindTexture()                                                            |
| glBindImageTextures                            | ImageUnit bindTextures()                                                           |
| glBindProgramPipeline                          | Pipeline.bind()                                                                    |
| glBindRenderbuffer                             | RenderBuffer.bind()                                                                |
| glBindSampler                                  | TextureUnit.bindSampler()                                                          |
| glBindSamplers                                 | TextureUnit.bindSamplers()                                                         |
| glBindTexture                                  | Texture.bind()                                                                     |
| glBindTextures                                 | TextureUnit.binTextures()                                                          |
| glBindTextureUnit                              | X DSA                                                                              |
| glBindTransformFeedback                        | TransformFeedback.bind()                                                           |
| glBindVertexArray                              | VertexArray.bind()                                                                 |
| glBindVertexBuffer                             | VertexArray.BindingPoint bind()                                                    |
| glBindVertexBuffers                            | VertexArray.bindBuffers()                                                          |
| glBlendColor                                   | Blending.setColor()                                                                |
| glBlendEquation                                | Blending.setEquation()                                                             |
| glBlendEquationi                               | Blending.setEquation()                                                             |
| glBlendEquationSeparate                        | Blending.setEquation()                                                             |
| glBlendEquationSeparatei                       | Blending.setEquation()                                                             |
| glBlendFunc                                    | Blending.setFunction()                                                             |
| glBlendFunci                                   | Blending.setFunction()                                                             |
| glBlendFuncSeparate                            | Blending.setFunction()                                                             |
| glBlendFuncSeparatei                           | Blending.setFunction()                                                             |
| glBlitFramebuffer                              | BaseFrameBuffer.blit()                                                             |
| glBlitNamedFramebuffer                         | X DSA                                                                              |
| glBufferData                                   | Buffer.realloc()                                                                   |
| glBufferStorage                                | Buffer.storage()                                                                   |
| glBufferSubData                                | Buffer setSubData()                                                                |
| glCheckFramebufferStatus                       | BaseFrameBuffer.getStatus()                                                        |
| glCheckNamedFramebufferStatus                  | X DSA                                                                              |
| glClampColor                                   | BaseFrameBuffer.setClampColor()                                                    |
| glClear                                        | BaseFrameBuffer.clear()                                                            |
| glClearBuffer                                  | BaseFrameBuffer.clearColorBuffer()                                                 |
| glClearBufferData                              | Buffer.clearData()                                                                 |
| glClearBufferfi                                | BaseFrameBuffer.clearDepthAndStencilBuffer()                                       |
| glClearBufferfv                                | BaseFrameBuffer.clearColorBuffer()                                                 |
| glClearBufferiv                                | BaseFrameBuffer.clearColorBuffer()                                                 |
| glClearBufferSubData                           | Buffer.clearSubData()                                                              |
| glClearBufferuiv                               | BaseFrameBuffer.clearColorBufferUnsigned()                                         |
| glClearColor                                   | BaseFrameBuffer.setClearColor()                                                    |
| glClearDepth                                   | BaseFrameBuffer.setClearDepth()                                                    |
| glClearDepthf                                  | X GL41                                                                             |
| glClearNamedBufferData                         | X DSA                                                                              |
| glClearNamedBufferSubData                      | X DSA                                                                              |
| glClearNamedFramebufferfi                      | X DSA                                                                              |
| glClearNamedFramebufferfv                      | X DSA                                                                              |
| glClearNamedFramebufferiv                      | X DSA                                                                              |
| glClearNamedFramebufferuiv                     | X DSA                                                                              |
| glClearStencil                                 | BaseFrameBuffer.setClearStencil()                                                  |
| glClearTexImage                                | TextureOps.ClearImage.clearImage()                                                 |
| glClearTexSubImage                             | TextureOps.ClearSubImage.clearSubImage()                                           |
| glClientWaitSync                               | Sync.awaitClient()                                                                 |
| glClipControl                                  | Clipping.control()                                                                 |
| glColorMask                                    | BaseFrameBuffer.setColorMask()                                                     |
| glColorMaski                                   | BaseFrameBuffer.setColorMask()                                                     |
| glCompileShader                                | Shader.compile()                                                                   |
| glCompressedTexImage1D                         | Texture1D.loadCompressed()                                                         |
| glCompressedTexImage2D                         | TextureOps.Load2DCompressed.loadCompressed()                                       |
| glCompressedTexImage3D                         | TextureOps.Load3DCompressed.loadCompressed()                                       |
| glCompressedTexSubImage1D                      | Texture1D.loadCompressedSub()                                                      |
| glCompressedTexSubImage2D                      | TextureOps.Load2DCompressedSub.loadCompressedSub()                                 |
| glCompressedTexSubImage3D                      | TextureOps.Load3DCompressedSub.loadCompressedSub()                                 |
| glCompressedTextureSubImage1D                  | X DSA                                                                              |
| glCompressedTextureSubImage2D                  | X DSA                                                                              |
| glCompressedTextureSubImage3D                  | X DSA                                                                              |
| glCopyBufferSubData                            | Buffer.copySubData()                                                               |
| glCopyImageSubData                             | TextureOps.CopyImageSubData.copyImageSubData()<br/>RenderBuffer.copyImageSubData() |
| glCopyNamedBufferSubData                       | X DSA                                                                              |
| glCopyTexImage1D                               | Texture1D.copy()                                                                   |
| glCopyTexImage2D                               | TextureOps.Copy2D.copy()                                                           |
| glCopyTexSubImage1D                            | Texture1D.copySub()                                                                |
| glCopyTexSubImage2D                            | TextureOps.Copy2DSub.copySub()                                                     |
| glCopyTexSubImage3D                            | TextureOps.Copy3DSub.copySub()                                                     |
| glCopyTextureSubImage1D                        | X DSA                                                                              |
| glCopyTextureSubImage2D                        | X DSA                                                                              |
| glCopyTextureSubImage3D                        | X DSA                                                                              |
| glCreateBuffers                                | X DSA                                                                              |
| glCreateFramebuffers                           | X DSA                                                                              |
| glCreateProgram                                | Program.of()                                                                       |
| glCreateProgramPipelines                       | X DSA                                                                              |
| glCreateQueries                                | X DSA                                                                              |
| glCreateRenderbuffers                          | X DSA                                                                              |
| glCreateSamplers                               | X DSA                                                                              |
| glCreateShader                                 | Shader.of()                                                                        |
| glCreateShaderProgram                          | X DSA                                                                              |
| glCreateShaderProgramv                         | X DSA                                                                              |
| glCreateTextures                               | X DSA                                                                              |
| glCreateTransformFeedbacks                     | X DSA                                                                              |
| glCreateVertexArrays                           | X DSA                                                                              |
| glCullFace                                     | Graphics.setCullFaceMode()                                                         |
| glDebugMessageCallback                         | Debug.setMessageCallback()                                                         |
| glDebugMessageControl                          | Debug.setMessageControl()                                                          |
| glDebugMessageInsert                           | Debug.insertMessage()                                                              |
| glDeleteBuffers                                | Buffer.close()                                                                     |
| glDeleteFramebuffers                           | FrameBuffer.close()                                                                |
| glDeleteProgram                                | Program.close()                                                                    |
| glDeleteProgramPipelines                       | Pipeline.close()                                                                   |
| glDeleteQueries                                | Query.close()                                                                      |
| glDeleteRenderbuffers                          | RenderBuffer.close()                                                               |
| glDeleteSamplers                               | Sampler.close()                                                                    |
| glDeleteShader                                 | Shader.close()                                                                     |
| glDeleteSync                                   | Sync.close()                                                                       |
| glDeleteTextures                               | Texture.close()                                                                    |
| glDeleteTransformFeedbacks                     | TransformFeedbacks.close()                                                         |
| glDeleteVertexArrays                           | VertexArrays.close()                                                               |
| glDepthFunc                                    | DepthTest.setFunction()                                                            |
| glDepthMask                                    | BaseFrameBuffer.setDepthMask()                                                     |
| glDepthRange                                   | Graphics.setDepthRange()                                                           |
| glDepthRangeArray                              | Viewport.setDepthRangeMulti()                                                      |
| glDepthRangeArrayv                             | Viewport.setDepthRangeMulti()                                                      |
| glDepthRangef                                  | Graphics.setDepthRange()                                                           |
| glDepthRangeIndexed                            | Viewport.setDepthRange()                                                           |
| glDetachShader                                 | Program.detach()                                                                   |
| glDisable                                      | Feature.disable()                                                                  |
| glDisablei                                     | IndexedFeature.disableAt()                                                         |
| glDisableVertexArrayAttrib                     | X DSA                                                                              |
| glDisableVertexAttribArray                     | VertexAttribute disable()                                                          |
| glDispatchCompute                              | Graphics.dispatchCompute()                                                         |
| glDispatchComputeIndirect                      | Graphics.dispatchComputeIndirect()                                                 |
| glDrawArrays                                   | ArraysDrawing.draw()                                                               |
| glDrawArraysIndirect                           | ArraysDrawing.Indirect.draw()                                                      |
| glDrawArraysInstanced                          | ArraysDrawing.Instanced.draw()                                                     |
| glDrawArraysInstancedBaseInstance              | ArraysDrawing.BaseInstanced.draw()                                                 |
| glDrawBuffer                                   | FrameBuffer.selectDrawBuffer()<br/>DefaultFrameBuffer.selectDrawBuffer()           |
| glDrawBuffers                                  | BaseFrameBuffer.selectDrawBuffers()                                                |
| glDrawElements                                 | ElementsDrawing.draw()                                                             |
| glDrawElementsBaseVertex                       | ElementsDrawing.BaseVertex.draw()                                                  |
| glDrawElementsIndirect                         | ElementsDrawing.Indirect.draw()                                                    |
| glDrawElementsInstanced                        | ElementsDrawing.Instanced.draw()                                                   |
| glDrawElementsInstancedBaseInstance            | ElementsDrawing.BaseInstanced.draw()                                               |
| glDrawElementsInstancedBaseVertex              | ElementsDrawing.InstancedBaseVertex.draw()                                         |
| glDrawElementsInstancedBaseVertexBaseInstance  | ElementsDrawing.BaseInstancedBaseVertex.draw()                                     |
| glDrawRangeElements                            | ElementsDrawing.Range.draw()                                                       |
| glDrawRangeElementsBaseVertex                  | ElementsDrawing.RangeBaseVertex.draw()                                             |
| glDrawTransformFeedback                        | TransformFeedbackDrawing.draw()                                                    |
| glDrawTransformFeedbackInstanced               | TransformFeedbackDrawing.Instanced.draw()                                          |
| glDrawTransformFeedbackStream                  | TransformFeedbackDrawing.Stream.draw()                                             |
| glDrawTransformFeedbackStreamInstanced         | TransformFeedbackDrawing.StreamInstanced.draw()                                    |
| glEnable                                       | Feature.enable()                                                                   |
| glEnablei                                      | IndexedFeature.enableAt()                                                          |
| glEnableVertexArrayAttrib                      | X DSA                                                                              |
| glEnableVertexAttribArray                      | VertexAttribute.enable()                                                           |
| glEndConditionalRender                         | SampleQuery.endConditionalRender()                                                 |
| glEndQuery                                     | Query.end()                                                                        |
| glEndQueryIndexed                              | IndexQuery end()                                                                   |
| glEndTransformFeedback                         | TransformFeedback end()                                                            |
| glFenceSync                                    | Sync.of()                                                                          |
| glFinish                                       | Graphics.awaitFinish()                                                             |
| glFlush                                        | Graphics.flush()                                                                   |
| glFlushMappedBufferRange                       | Buffer.flushMappedRange()                                                          |
| glFlushMappedNamedBufferRange                  | X DSA                                                                              |
| glFramebufferParameteri                        | FrameBuffer.setXXX()                                                               |
| glFramebufferRenderbuffer                      | BaseFrameBuffer.attach()                                                           |
| glFramebufferTexture                           | FrameBuffer.attachLayered()                                                        |
| glFramebufferTexture1D                         | FrameBuffer.attach()                                                               |
| glFramebufferTexture2D                         | FrameBuffer.attach()                                                               |
| glFramebufferTexture3D                         | FrameBuffer.attach()                                                               |
| glFramebufferTextureLayer                      | FrameBuffer.attachLayer()                                                          |
| glFrontFace                                    | Graphics setFrontFaceDirection()                                                   |
| glGenBuffers                                   | Buffer.of()                                                                        |
| glGenerateMipmap                               | TextureOps.GenerateMipmap.generateMipmap()                                         |
| glGenerateTextureMipmap                        | X DSA                                                                              |
| glGenFramebuffers                              | FrameBuffer.of()                                                                   |
| glGenProgramPipelines                          | Pipeline.of()                                                                      |
| glGenQueries                                   | Query.of()                                                                         |
| glGenRenderbuffers                             | Renderbuffer.of()                                                                  |
| glGenSamplers                                  | Sampler.of()                                                                       |
| glGenTextures                                  | TexturesXXX.of()                                                                   |
| glGenTransformFeedbacks                        | TransformFeedback.of()                                                             |
| glGenVertexArrays                              | VertexArray.of()                                                                   |
| glGet                                          |                                                                                    |
| glGetActiveAtomicCounterBufferiv               | X 建议使用OpenGL43的ProgramInterfaces                                                   |
| glGetActiveAttrib                              | Program.getActiveVertexAttribute()                                                 |
| glGetActiveSubroutineName                      | X 建议使用OpenGL43的ProgramInterfaces                                                   |
| glGetActiveSubroutineUniform                   | X 建议使用OpenGL43的ProgramInterfaces                                                   |
| glGetActiveSubroutineUniformiv                 | X 建议使用OpenGL43的ProgramInterfaces                                                   |
| glGetActiveSubroutineUniformName               | X 建议使用OpenGL43的ProgramInterfaces                                                   |
| glGetActiveUniform                             | Program.getUniform()                                                               |
| glGetActiveUniformBlock                        | * See sub interfaces of ProgramResource                                            |
| glGetActiveUniformBlockiv                      | * See sub interfaces of ProgramResource                                            |
| glGetActiveUniformBlockName                    | X 建议使用OpenGL43的ProgramInterfaces                                                   |
| glGetActiveUniformName                         | Program.uniformNames()                                                             |
| glGetActiveUniformsiv                          | Program.getUniform()                                                               |
| glGetAttachedShaders                           | Program.getAttachedShaders()                                                       |
| glGetAttribLocation                            | Program.getVertexAttributeLocation()                                               |
| glGetBooleani_v                                |                                                                                    |
| glGetBooleanv                                  |                                                                                    |
| glGetBufferParameter                           | Buffer.getXXX()                                                                    |
| glGetBufferParameteri64v                       | Buffer.getXXX()                                                                    |
| glGetBufferParameteriv                         | Buffer.getXXX()                                                                    |
| glGetBufferPointerv                            | Buffer.getMappedBuffer()                                                           |
| glGetBufferSubData                             | Buffer.getSubData()                                                                |
| glGetCompressedTexImage                        | TextureOps.GetCompressedImage.getCompressedImage()                                 |
| glGetCompressedTextureImage                    | X DSA                                                                              |
| glGetCompressedTextureSubImage                 | TextureOps.GetCompressedSubImage.getCompressedSubImage()                           |
| glGetDebugMessageLog                           | Debug.getMessageLog()                                                              |
| glGetDoublei_v                                 |                                                                                    |
| glGetDoublev                                   |                                                                                    |
| glGetError                                     |                                                                                    |
| glGetFloati_v                                  |                                                                                    |
| glGetFloatv                                    |                                                                                    |
| glGetFragDataIndex                             | Program.getFragDataIndex()                                                         |
| glGetFragDataLocation                          | Program.getFragData()                                                              |
| glGetFramebufferAttachmentParameter            | X                                                                                  |
| glGetFramebufferAttachmentParameteriv          | X                                                                                  |
| glGetFramebufferParameter                      | FrameBuffer.getXXX()                                                               |
| glGetFramebufferParameteriv                    | FrameBuffer.getXXX()                                                               |
| glGetGraphicsResetStatus                       | Graphics.getRestStatus()                                                           |
| glGetInteger64i_v                              |                                                                                    |
| glGetInteger64v                                |                                                                                    |
| glGetIntegeri_v                                |                                                                                    |
| glGetIntegerv                                  |                                                                                    |
| glGetInternalformat                            | X                                                                                  |
| glGetInternalformati64v                        | X                                                                                  |
| glGetInternalformativ                          | X                                                                                  |
| glGetMultisample                               | Graphics.getMultiSample()                                                          |
| glGetMultisamplefv                             | Graphics.getMultiSample()                                                          |
| glGetNamedBufferParameteri64v                  | X DSA                                                                              |
| glGetNamedBufferParameteriv                    | X DSA                                                                              |
| glGetNamedBufferPointerv                       | X DSA                                                                              |
| glGetNamedBufferSubData                        | X DSA                                                                              |
| glGetNamedFramebufferAttachmentParameteriv     | X DSA                                                                              |
| glGetNamedFramebufferParameteriv               | X DSA                                                                              |
| glGetNamedRenderbufferParameteriv              | X DSA                                                                              |
| glGetnCompressedTexImage                       | X                                                                                  |
| glGetnTexImage                                 | X                                                                                  |
| glGetnUniformdv                                | X                                                                                  |
| glGetnUniformfv                                | X                                                                                  |
| glGetnUniformiv                                | X                                                                                  |
| glGetnUniformuiv                               | X                                                                                  |
| glGetObjectLabel                               | XX                                                                                 |
| glGetObjectPtrLabel                            | XX                                                                                 |
| glGetPointerv                                  | XX                                                                                 |
| glGetProgram                                   | Program.getXXX()                                                                   |
| glGetProgramBinary                             | Program.getBinary()                                                                |
| glGetProgramInfoLog                            | Program.link()                                                                     |
| glGetProgramInterface                          | ProgramInterface.getXXX()                                                          |
| glGetProgramInterfaceiv                        | ProgramInterface.getXXX()                                                          |
| glGetProgramiv                                 | Program.getXXX()                                                                   |
| glGetProgramPipeline                           | Pipeline.getActiveProgram()<br/>Pipeline.getShaderProgram()                        |
| glGetProgramPipelineInfoLog                    | Pipeline.getInfoLog()                                                              |
| glGetProgramPipelineiv                         | Pipeline.getActiveProgram()<br/>Pipeline.getShaderProgram()                        |
| glGetProgramResource                           | ProgramResource.XXX.getXXX()                                                       |
| glGetProgramResourceIndex                      | ProgramInterface.Named.getResource()                                               |
| glGetProgramResourceiv                         | ProgramResource.XXX.getXXX()                                                       |
| glGetProgramResourceLocation                   | X Use ProgramResource.Location.getLocation()                                       |
| glGetProgramResourceLocationIndex              | X Use ProgramResource.LocationIndex.getLocationIndex()                             |
| glGetProgramResourceName                       | ProgramResource.Named.getName()                                                    |
| glGetProgramStage                              | SubroutineUniformInterface.getXXX()                                                |
| glGetProgramStageiv                            | SubroutineUniformInterface.getXXX()                                                |
| glGetQueryBufferObjecti64v                     | X DSA                                                                              |
| glGetQueryBufferObjectiv                       | X DSA                                                                              |
| glGetQueryBufferObjectui64v                    | X DSA                                                                              |
| glGetQueryBufferObjectuiv                      | X DSA                                                                              |
| glGetQueryIndexed                              | Query.State.getXXX()                                                               |
| glGetQueryIndexediv                            | Query.State.getXXX()                                                               |
| glGetQueryiv                                   | Query.State.getXXX()                                                               |
| glGetQueryObject                               | Query.getXXX()                                                                     |
| glGetQueryObjecti64v                           | Query.getXXX()                                                                     |
| glGetQueryObjectiv                             | Query.getXXX()                                                                     |
| glGetQueryObjectui64v                          | Query.getXXX()                                                                     |
| glGetQueryObjectuiv                            | Query.getXXX()                                                                     |
| glGetRenderbufferParameter                     | RenderBuffer.getXXX()                                                              |
| glGetRenderbufferParameteriv                   | RenderBuffer.getXXX()                                                              |
| glGetSamplerParameter                          | SamplerParameters.XXX()                                                            |
| glGetSamplerParameterfv                        | SamplerParameters.XXX()                                                            |
| glGetSamplerParameterIiv                       | SamplerParameters.XXX()                                                            |
| glGetSamplerParameterIuiv                      | SamplerParameters.XXX()                                                            |
| glGetSamplerParameteriv                        | SamplerParameters.XXX()                                                            |
| glGetShader                                    | Shader.XXX()                                                                       |
| glGetShaderInfoLog                             | Shader.compile()                                                                   |
| glGetShaderiv                                  | Shader.compile()                                                                   |
| glGetShaderPrecisionFormat                     | Graphics.getXXXPrecisionFormat()                                                   |
| glGetShaderSource                              | Shader.source()                                                                    |
| glGetString                                    | X                                                                                  |
| glGetStringi                                   | X                                                                                  |
| glGetSubroutineIndex                           | SubroutineUniformInterface.getResource()                                           |
| glGetSubroutineUniformLocation                 | SubroutineUniformInterface.getLocation()                                           |
| glGetSync                                      | Sync.isSignaled()                                                                  |
| glGetSynciv                                    | Sync.isSignaled()                                                                  |
| glGetTexImage                                  | TextureOps.GetImage.getImage()                                                     |
| glGetTexLevelParameter                         | Texture.XXX()                                                                      |
| glGetTexLevelParameterfv                       | Texture.XXX()                                                                      |
| glGetTexLevelParameteriv                       | Texture.XXX()                                                                      |
| glGetTexParameter                              | TextureOps.Parameters.XXX()                                                        |
| glGetTexParameterfv                            | TextureOps.Parameters.XXX()                                                        |
| glGetTexParameterIiv                           | TextureOps.Parameters.XXX()                                                        |
| glGetTexParameterIuiv                          | TextureOps.Parameters.XXX()                                                        |
| glGetTexParameteriv                            | TextureOps.Parameters.XXX()                                                        |
| glGetTextureImage                              | X DSA                                                                              |
| glGetTextureLevelParameterfv                   | X DSA                                                                              |
| glGetTextureLevelParameteriv                   | X DSA                                                                              |
| glGetTextureParameterfv                        | X DSA                                                                              |
| glGetTextureParameterIiv                       | X DSA                                                                              |
| glGetTextureParameterIuiv                      | X DSA                                                                              |
| glGetTextureParameteriv                        | X DSA                                                                              |
| glGetTextureSubImage                           | TextureOps.GetSubImage.getSubImage()                                               |
| glGetTransformFeedback                         | TransformFeedback.xxx()                                                            |
| glGetTransformFeedbacki64_v                    | TransformFeedback.xxx()                                                            |
| glGetTransformFeedbacki_v                      | TransformFeedback.xxx()                                                            |
| glGetTransformFeedbackiv                       | TransformFeedback.xxx()                                                            |
| glGetTransformFeedbackVarying                  | Program.getTransformFeedbackVarying()                                              |
| glGetUniform                                   | UniformOps.getXXX()                                                                |
| glGetUniformBlockIndex                         | Program.getUniformBlock()                                                          |
| glGetUniformdv                                 | UniformOps.getXXX()                                                                |
| glGetUniformfv                                 | UniformOps.getXXX()                                                                |
| glGetUniformIndices                            | X                                                                                  |
| glGetUniformiv                                 | UniformOps.getXXX()                                                                |
| glGetUniformLocation                           | Uniform.getLocation()                                                              |
| glGetUniformSubroutine                         | X                                                                                  |
| glGetUniformSubroutineuiv                      | X                                                                                  |
| glGetUniformuiv                                | UniformOps.getUnsignedXXX()                                                        |
| glGetVertexArrayIndexed                        | X DSA                                                                              |
| glGetVertexArrayIndexed64iv                    | X DSA                                                                              |
| glGetVertexArrayIndexediv                      | X DSA                                                                              |
| glGetVertexArrayiv                             | X DSA                                                                              |
| glGetVertexAttrib                              |                                                                                    |
| glGetVertexAttribdv                            |                                                                                    |
| glGetVertexAttribfv                            |                                                                                    |
| glGetVertexAttribIiv                           |                                                                                    |
| glGetVertexAttribIuiv                          |                                                                                    |
| glGetVertexAttribiv                            |                                                                                    |
| glGetVertexAttribLdv                           |                                                                                    |
| glGetVertexAttribPointerv                      |                                                                                    |
| glFramebufferParameteri                        | FrameBuffer.setXXX()                                                               |
| glNamedFramebufferParameteri                   | X DSA                                                                              |
| glHint                                         |                                                                                    |
| glInvalidateBufferData                         |                                                                                    |
| glInvalidateBufferSubData                      |                                                                                    |
| glInvalidateFramebuffer                        | FrameBuffer.invalidate()<br/>DefaultFrameBuffer.invalidate()                       |
| glInvalidateNamedFramebufferData               | X DSA                                                                              |
| glInvalidateNamedFramebufferSubData            | X DSA                                                                              |
| glInvalidateSubFramebuffer                     | FrameBuffer.invalidate()<br/>DefaultFrameBuffer.invalidate()                       |
| glInvalidateTexImage                           | Texture.invalidate()                                                               |
| glInvalidateTexSubImage                        | Texture.invalidateSubXXX()                                                         |
| glIsBuffer                                     | XX                                                                                 |
| glIsEnabled                                    | .                                                                                  |
| glIsEnabledi                                   | .                                                                                  |
| glIsFramebuffer                                | XX                                                                                 |
| glIsProgram                                    | XX                                                                                 |
| glIsProgramPipeline                            | XX                                                                                 |
| glIsQuery                                      | XX                                                                                 |
| glIsRenderbuffer                               | XX                                                                                 |
| glIsSampler                                    | XX                                                                                 |
| glIsShader                                     | XX                                                                                 |
| glIsSync                                       | XX                                                                                 |
| glIsTexture                                    | XX                                                                                 |
| glIsTransformFeedback                          | XX                                                                                 |
| glIsVertexArray                                | XX                                                                                 |
| glLineWidth                                    | Graphics.setLineWidth()                                                            |
| glLinkProgram                                  | Program.link()                                                                     |
| glLogicOp                                      | LogicalOperation.setOp()                                                           |
| glMapBuffer                                    | Buffer.map()                                                                       |
| glMapBufferRange                               | Buffer.mapRange()                                                                  |
| glMapNamedBuffer                               | X DSA                                                                              |
| glMapNamedBufferRange                          | X DSA                                                                              |
| glMemoryBarrier                                | Graphics.setMemoryBarrier()                                                        |
| glMemoryBarrierByRegion                        | Graphics.setMemoryBarrierByRegion()                                                |
| glMinSampleShading                             | Graphics.minSampleShading                                                          |
| glMultiDrawArrays                              |                                                                                    |
| glMultiDrawArraysIndirect                      |                                                                                    |
| glMultiDrawElements                            |                                                                                    |
| glMultiDrawElementsBaseVertex                  |                                                                                    |
| glMultiDrawElementsIndirect                    |                                                                                    |
| glNamedBufferData                              | X DSA                                                                              |
| glNamedBufferStorage                           | X DSA                                                                              |
| glNamedBufferSubData                           | X DSA                                                                              |
| glNamedFramebufferDrawBuffer                   | X DSA                                                                              |
| glNamedFramebufferDrawBuffers                  | X DSA                                                                              |
| glNamedFramebufferParameteri                   | X DSA                                                                              |
| glNamedFramebufferReadBuffer                   | X DSA                                                                              |
| glNamedFramebufferRenderbuffer                 | X DSA                                                                              |
| glNamedFramebufferTexture                      | X DSA                                                                              |
| glNamedFramebufferTextureLayer                 | X DSA                                                                              |
| glNamedRenderbufferStorage                     | X DSA                                                                              |
| glNamedRenderbufferStorageMultisample          | X DSA                                                                              |
| glObjectLabel                                  | XX                                                                                 |
| glObjectPtrLabel                               | XX                                                                                 |
| glPatchParameter                               | Graphics.setPathXXX()                                                              |
| glPatchParameterfv                             | Graphics.setPathXXX()                                                              |
| glPatchParameteri                              | Graphics.setPathXXX()                                                              |
| glPauseTransformFeedback                       | TransformFeedback.pause()                                                          |
| glPixelStore                                   | PixelStorageMode.XXX.setXXX()                                                      |
| glPixelStoref                                  | PixelStorageMode.XXX.setFloat()                                                    |
| glPixelStorei                                  | PixelStorageMode.XXX.setInt()                                                      |
| glPointParameter                               | Graphics.setPointXXX()                                                             |
| glPointParameterf                              | Graphics.setPointXXX()                                                             |
| glPointParameterfv                             | Graphics.setPointXXX()                                                             |
| glPointParameteri                              | Graphics.setPointXXX()                                                             |
| glPointParameteriv                             | Graphics.setPointXXX()                                                             |
| glPointSize                                    | Graphics.setPointSize()                                                            |
| glPolygonMode                                  | Graphics.setPolygonMode()                                                          |
| glPolygonOffset                                | Graphics.setPolygonOffset()                                                        |
| glPopDebugGroup                                | Debug.popGroup()                                                                   |
| glPrimitiveRestartIndex                        | PrimitiveRestart.setRestartIndex()                                                 |
| glProgramBinary                                | Program.loadBinary()                                                               |
| glProgramParameter                             | Program.setXXX()                                                                   |
| glProgramParameteri                            | Program.setXXX()                                                                   |
| glProgramUniform                               | X DSA                                                                              |
| glProgramUniform1f                             | X DSA                                                                              |
| glProgramUniform1fv                            | X DSA                                                                              |
| glProgramUniform1i                             | X DSA                                                                              |
| glProgramUniform1iv                            | X DSA                                                                              |
| glProgramUniform1ui                            | X DSA                                                                              |
| glProgramUniform1uiv                           | X DSA                                                                              |
| glProgramUniform2f                             | X DSA                                                                              |
| glProgramUniform2fv                            | X DSA                                                                              |
| glProgramUniform2i                             | X DSA                                                                              |
| glProgramUniform2iv                            | X DSA                                                                              |
| glProgramUniform2ui                            | X DSA                                                                              |
| glProgramUniform2uiv                           | X DSA                                                                              |
| glProgramUniform3f                             | X DSA                                                                              |
| glProgramUniform3fv                            | X DSA                                                                              |
| glProgramUniform3i                             | X DSA                                                                              |
| glProgramUniform3iv                            | X DSA                                                                              |
| glProgramUniform3ui                            | X DSA                                                                              |
| glProgramUniform3uiv                           | X DSA                                                                              |
| glProgramUniform4f                             | X DSA                                                                              |
| glProgramUniform4fv                            | X DSA                                                                              |
| glProgramUniform4i                             | X DSA                                                                              |
| glProgramUniform4iv                            | X DSA                                                                              |
| glProgramUniform4ui                            | X DSA                                                                              |
| glProgramUniform4uiv                           | X DSA                                                                              |
| glProgramUniformMatrix2fv                      | X DSA                                                                              |
| glProgramUniformMatrix2x3fv                    | X DSA                                                                              |
| glProgramUniformMatrix2x4fv                    | X DSA                                                                              |
| glProgramUniformMatrix3fv                      | X DSA                                                                              |
| glProgramUniformMatrix3x2fv                    | X DSA                                                                              |
| glProgramUniformMatrix3x4fv                    | X DSA                                                                              |
| glProgramUniformMatrix4fv                      | X DSA                                                                              |
| glProgramUniformMatrix4x2fv                    | X DSA                                                                              |
| glProgramUniformMatrix4x3fv                    | X DSA                                                                              |
| glProvokingVertex                              | Graphics.provokingVertex()                                                         |
| glPushDebugGroup                               | Debug.pushGroup()                                                                  |
| glQueryCounter                                 | Query.TimerQuery.count()                                                           |
| glReadBuffer                                   | BaseFrameBuffer.selectReadBuffer()                                                 |
| glReadnPixels                                  | X                                                                                  |
| glReadPixels                                   | BaseFrameBuffer.readPixels()                                                       |
| glReleaseShaderCompiler                        | Graphics.releaseShaderCompiler()                                                   |
| glRenderbufferStorage                          | RenderBuffer.storage()                                                             |
| glRenderbufferStorageMultisample               | RenderBuffer.storageMultiSample()                                                  |
| glResumeTransformFeedback                      | TransformFeedback.resume()                                                         |
| glSampleCoverage                               | X                                                                                  |
| glSampleMaski                                  | X                                                                                  |
| glSamplerParameter                             | Sampler.XXX()                                                                      |
| glSamplerParameterf                            | Sampler.XXX()                                                                      |
| glSamplerParameterfv                           | Sampler.XXX()                                                                      |
| glSamplerParameteri                            | Sampler.XXX()                                                                      |
| glSamplerParameterIiv                          | Sampler.XXX()                                                                      |
| glSamplerParameterIuiv                         | Sampler.XXX()                                                                      |
| glSamplerParameteriv                           | Sampler.XXX()                                                                      |
| glScissor                                      | ScissorTest.setBox()                                                               |
| glScissorArray                                 | ScissorTest.setBoxes()                                                             |
| glScissorArrayv                                | ScissorTest.setBoxes()                                                             |
| glScissorIndexed                               | ScissorTest.setBoxAt()                                                             |
| glScissorIndexedv                              | ScissorTest.setBoxAt()                                                             |
| glShaderBinary                                 | ShaderArray.binary()                                                               |
| glShaderSource                                 | Shader.source()                                                                    |
| glShaderStorageBlockBinding                    | ShaderStorageBlock.bind()                                                          |
| glStencilFunc                                  |                                                                                    |
| glStencilFuncSeparate                          |                                                                                    |
| glStencilMask                                  |                                                                                    |
| glStencilMaskSeparate                          |                                                                                    |
| glStencilOp                                    |                                                                                    |
| glStencilOpSeparate                            |                                                                                    |
| glTexBuffer                                    |                                                                                    |
| glTexBufferRange                               |                                                                                    |
| glTexImage1D                                   |                                                                                    |
| glTexImage2D                                   |                                                                                    |
| glTexImage2DMultisample                        |                                                                                    |
| glTexImage3D                                   |                                                                                    |
| glTexImage3DMultisample                        |                                                                                    |
| glTexParameter                                 |                                                                                    |
| glTexParameterf                                |                                                                                    |
| glTexParameterfv                               |                                                                                    |
| glTexParameteri                                |                                                                                    |
| glTexParameterIiv                              |                                                                                    |
| glTexParameterIuiv                             |                                                                                    |
| glTexParameteriv                               |                                                                                    |
| glTexStorage1D                                 |                                                                                    |
| glTexStorage2D                                 |                                                                                    |
| glTexStorage2DMultisample                      |                                                                                    |
| glTexStorage3D                                 |                                                                                    |
| glTexStorage3DMultisample                      |                                                                                    |
| glTexSubImage1D                                |                                                                                    |
| glTexSubImage2D                                |                                                                                    |
| glTexSubImage3D                                |                                                                                    |
| glTextureBarrier                               | Graphics.setTextureBarrier()                                                       |
| glTextureBuffer                                |                                                                                    |
| glTextureBufferRange                           | X DSA                                                                              |
| glTextureParameterf                            | X DSA                                                                              |
| glTextureParameterfv                           | X DSA                                                                              |
| glTextureParameteri                            | X DSA                                                                              |
| glTextureParameterIiv                          | X DSA                                                                              |
| glTextureParameterIuiv                         | X DSA                                                                              |
| glTextureParameteriv                           | X DSA                                                                              |
| glTextureStorage1D                             | X DSA                                                                              |
| glTextureStorage2D                             | X DSA                                                                              |
| glTextureStorage2DMultisample                  | X DSA                                                                              |
| glTextureStorage3D                             | X DSA                                                                              |
| glTextureStorage3DMultisample                  | X DSA                                                                              |
| glTextureSubImage1D                            | X DSA                                                                              |
| glTextureSubImage2D                            | X DSA                                                                              |
| glTextureSubImage3D                            | X DSA                                                                              |
| glTextureView                                  |                                                                                    |
| glTransformFeedbackBufferBase                  |                                                                                    |
| glTransformFeedbackBufferRange                 |                                                                                    |
| glTransformFeedbackVaryings                    |                                                                                    |
| glUniform                                      |                                                                                    |
| glUniform1f                                    |                                                                                    |
| glUniform1fv                                   |                                                                                    |
| glUniform1i                                    |                                                                                    |
| glUniform1iv                                   |                                                                                    |
| glUniform1ui                                   |                                                                                    |
| glUniform1uiv                                  |                                                                                    |
| glUniform2f                                    |                                                                                    |
| glUniform2fv                                   |                                                                                    |
| glUniform2i                                    |                                                                                    |
| glUniform2iv                                   |                                                                                    |
| glUniform2ui                                   |                                                                                    |
| glUniform2uiv                                  |                                                                                    |
| glUniform3f                                    |                                                                                    |
| glUniform3fv                                   |                                                                                    |
| glUniform3i                                    |                                                                                    |
| glUniform3iv                                   |                                                                                    |
| glUniform3ui                                   |                                                                                    |
| glUniform3uiv                                  |                                                                                    |
| glUniform4f                                    |                                                                                    |
| glUniform4fv                                   |                                                                                    |
| glUniform4i                                    |                                                                                    |
| glUniform4iv                                   |                                                                                    |
| glUniform4ui                                   |                                                                                    |
| glUniform4uiv                                  |                                                                                    |
| glUniformBlockBinding                          |                                                                                    |
| glUniformMatrix2fv                             |                                                                                    |
| glUniformMatrix2x3fv                           |                                                                                    |
| glUniformMatrix2x4fv                           |                                                                                    |
| glUniformMatrix3fv                             |                                                                                    |
| glUniformMatrix3x2fv                           |                                                                                    |
| glUniformMatrix3x4fv                           |                                                                                    |
| glUniformMatrix4fv                             |                                                                                    |
| glUniformMatrix4x2fv                           |                                                                                    |
| glUniformMatrix4x3fv                           |                                                                                    |
| glUniformSubroutines                           | Program loadSubroutineUniforms()                                                   |
| glUniformSubroutinesuiv                        | Program loadSubroutineUniforms()                                                   |
| glUnmapBuffer                                  |                                                                                    |
| glUnmapNamedBuffer                             | X DSA                                                                              |
| glUseProgram                                   |                                                                                    |
| glUseProgramStages                             |                                                                                    |
| glValidateProgram                              |                                                                                    |
| glValidateProgramPipeline                      |                                                                                    |
| glVertexArrayAttribBinding                     | X DSA                                                                              |
| glVertexArrayAttribFormat                      | X DSA                                                                              |
| glVertexArrayAttribIFormat                     | X DSA                                                                              |
| glVertexArrayAttribLFormat                     | X DSA                                                                              |
| glVertexArrayBindingDivisor                    | X DSA                                                                              |
| glVertexArrayElementBuffer                     | X DSA                                                                              |
| glVertexArrayVertexBuffer                      | X DSA                                                                              |
| glVertexArrayVertexBuffers                     | X DSA                                                                              |
| glVertexAttrib                                 |                                                                                    |
| glVertexAttrib1d                               |                                                                                    |
| glVertexAttrib1dv                              |                                                                                    |
| glVertexAttrib1f                               |                                                                                    |
| glVertexAttrib1fv                              |                                                                                    |
| glVertexAttrib1s                               |                                                                                    |
| glVertexAttrib1sv                              |                                                                                    |
| glVertexAttrib2d                               |                                                                                    |
| glVertexAttrib2dv                              |                                                                                    |
| glVertexAttrib2f                               |                                                                                    |
| glVertexAttrib2fv                              |                                                                                    |
| glVertexAttrib2s                               |                                                                                    |
| glVertexAttrib2sv                              |                                                                                    |
| glVertexAttrib3d                               |                                                                                    |
| glVertexAttrib3dv                              |                                                                                    |
| glVertexAttrib3f                               |                                                                                    |
| glVertexAttrib3fv                              |                                                                                    |
| glVertexAttrib3s                               |                                                                                    |
| glVertexAttrib3sv                              |                                                                                    |
| glVertexAttrib4bv                              |                                                                                    |
| glVertexAttrib4d                               |                                                                                    |
| glVertexAttrib4dv                              |                                                                                    |
| glVertexAttrib4f                               |                                                                                    |
| glVertexAttrib4fv                              |                                                                                    |
| glVertexAttrib4iv                              |                                                                                    |
| glVertexAttrib4Nbv                             |                                                                                    |
| glVertexAttrib4Niv                             |                                                                                    |
| glVertexAttrib4Nsv                             |                                                                                    |
| glVertexAttrib4Nub                             |                                                                                    |
| glVertexAttrib4Nubv                            |                                                                                    |
| glVertexAttrib4Nuiv                            |                                                                                    |
| glVertexAttrib4Nusv                            |                                                                                    |
| glVertexAttrib4s                               |                                                                                    |
| glVertexAttrib4sv                              |                                                                                    |
| glVertexAttrib4ubv                             |                                                                                    |
| glVertexAttrib4uiv                             |                                                                                    |
| glVertexAttrib4usv                             |                                                                                    |
| glVertexAttribBinding                          |                                                                                    |
| glVertexAttribDivisor                          |                                                                                    |
| glVertexAttribFormat                           |                                                                                    |
| glVertexAttribI1i                              |                                                                                    |
| glVertexAttribI1iv                             |                                                                                    |
| glVertexAttribI1ui                             |                                                                                    |
| glVertexAttribI1uiv                            |                                                                                    |
| glVertexAttribI2i                              |                                                                                    |
| glVertexAttribI2iv                             |                                                                                    |
| glVertexAttribI2ui                             |                                                                                    |
| glVertexAttribI2uiv                            |                                                                                    |
| glVertexAttribI3i                              |                                                                                    |
| glVertexAttribI3iv                             |                                                                                    |
| glVertexAttribI3ui                             |                                                                                    |
| glVertexAttribI3uiv                            |                                                                                    |
| glVertexAttribI4bv                             |                                                                                    |
| glVertexAttribI4i                              |                                                                                    |
| glVertexAttribI4iv                             |                                                                                    |
| glVertexAttribI4sv                             |                                                                                    |
| glVertexAttribI4ubv                            |                                                                                    |
| glVertexAttribI4ui                             |                                                                                    |
| glVertexAttribI4uiv                            |                                                                                    |
| glVertexAttribI4usv                            |                                                                                    |
| glVertexAttribIFormat                          |                                                                                    |
| glVertexAttribIPointer                         |                                                                                    |
| glVertexAttribL1d                              |                                                                                    |
| glVertexAttribL1dv                             |                                                                                    |
| glVertexAttribL2d                              |                                                                                    |
| glVertexAttribL2dv                             |                                                                                    |
| glVertexAttribL3d                              |                                                                                    |
| glVertexAttribL3dv                             |                                                                                    |
| glVertexAttribL4d                              |                                                                                    |
| glVertexAttribL4dv                             |                                                                                    |
| glVertexAttribLFormat                          |                                                                                    |
| glVertexAttribLPointer                         |                                                                                    |
| glVertexAttribP1ui                             |                                                                                    |
| glVertexAttribP2ui                             |                                                                                    |
| glVertexAttribP3ui                             |                                                                                    |
| glVertexAttribP4ui                             |                                                                                    |
| glVertexAttribPointer                          |                                                                                    |
| glVertexBindingDivisor                         |                                                                                    |
| glViewport                                     |                                                                                    |
| glViewportArray                                |                                                                                    |
| glViewportArrayv                               |                                                                                    |
| glViewportIndexed                              |                                                                                    |
| glViewportIndexedf                             |                                                                                    |
| glViewportIndexedfv                            |                                                                                    |
| glWaitSync                                     |                                                                                    |