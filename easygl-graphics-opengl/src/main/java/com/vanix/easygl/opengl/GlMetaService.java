package com.vanix.easygl.opengl;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.core.meta.*;
import com.vanix.easygl.graphics.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
@SystemName("Graphics")
public class GlMetaService extends AbstractMetaService {
    static final BindableMeta<Buffer.Target, Buffer> BufferMeta = new IntBindableMeta<>(
            args -> new GlBuffer((DataType) args[0]),
            GLX::glBindBuffer,
            GLX::glBindBuffer,
            0,
            GLX::glDeleteBuffers,
            (handle, args) -> new GlBuffer(handle, (DataType) args[0]),
            GLX::glGenBuffers,
            GLX::glDeleteBuffers,
            GlBufferArray::new);

    static final BindableMeta<BindTarget.Default<VertexArray>, VertexArray> VertexArrayMeta = new IntBindableMeta<>(
            args -> new GlVertexArray(),
            (target, handle) -> GLX.glBindVertexArray(handle),
            (target, handle) -> GLX.glBindVertexArray(handle),
            0,
            GLX::glDeleteVertexArrays,
            (handle, args) -> new GlVertexArray(handle),
            GLX::glGenVertexArrays,
            GLX::glDeleteVertexArrays);

    static final BindableMeta<BindTarget.Default<TransformFeedback>, TransformFeedback> TransformFeedbackMeta = new IntBindableMeta<>(
            args -> new GlTransformFeedback(GLX.glGenTransformFeedbacks()),
            GLX::glBindTransformFeedback,
            GLX::glBindTransformFeedback,
            0,
            GLX::glDeleteTransformFeedbacks,
            (handle, args) -> new GlTransformFeedback(handle),
            GLX::glGenTransformFeedbacks,
            GLX::glDeleteTransformFeedbacks);

    static final BindableMeta<BaseFrameBuffer.Target<FrameBuffer>, FrameBuffer> FrameBufferMeta = new IntBindableMeta<>(
            args -> new GlFrameBuffer(),
            GLX::glBindFramebuffer,
            GLX::glBindFramebuffer,
            0,
            GLX::glDeleteFramebuffers,
            (handle, args) -> new GlFrameBuffer(handle),
            GLX::glGenFramebuffers,
            GLX::glDeleteFramebuffers);

    static final HandleMeta<Shader> ShaderMeta = new IntHandleMeta<>(
            args -> new GlShader((Shader.Type) args[0]),
            GLX::glDeleteShader,
            (handle, args) -> new GlShader(handle, (Shader.Type) args[0]),
            null,
            null);

    static final BindableMeta<BindTarget.Default<Program>, Program> ProgramMeta = new IntBindableMeta<>(
            args -> new GlProgram(),
            (target, handle) -> GLX.glUseProgram(handle),
            (target, handle) -> GLX.glUseProgram(handle),
            0,
            GLX::glDeleteProgram,
            (handle, args) -> new GlProgram(handle));

    static final BindableMeta<BindTarget.Default<Pipeline>, Pipeline> PipelineMeta = new IntBindableMeta<>(
            args -> new GlPipeline(),
            (target, handle) -> GLX.glBindProgramPipeline(handle),
            (target, handle) -> GLX.glBindProgramPipeline(handle),
            0,
            GLX::glDeleteProgramPipelines,
            (handle, args) -> new GlPipeline(handle),
            GLX::glGenProgramPipelines,
            GLX::glDeleteProgramPipelines);

    static final BindableMeta<BindTarget.Default<RenderBuffer>, RenderBuffer> RenderBufferMeta = new IntBindableMeta<>(
            args -> new GlRenderBuffer(),
            GLX::glBindRenderbuffer,
            GLX::glBindRenderbuffer,
            0,
            GLX::glDeleteRenderbuffers,
            (handle, args) -> new GlRenderBuffer(handle),
            GLX::glGenRenderbuffers,
            GLX::glDeleteRenderbuffers);

    static final BindableMeta<BindTarget.Default<TextureUnit>, TextureUnit> TextureUnitMeta = new IntBindableMeta<>(
            args -> new GlTextureUnit((int) args[0]),
            (target, h) -> GLX.glActiveTexture(h),
            (target, h) -> GLX.glActiveTexture(h),
            GLX.GL_TEXTURE0,
            null,
            null
    );

    static final HandleMeta<Sampler> SamplerMeta = new IntHandleMeta<>(
            args -> new GlSampler(GLX.glGenSamplers()),
            GLX::glDeleteSamplers,
            (handle, args) -> new GlSampler(handle),
            GLX::glGenSamplers,
            GLX::glDeleteSamplers);

    static final BindableMeta<Texture.TexTarget<Texture1D>, Texture1D> Texture1DMeta = createTextureMeta(
            args -> new GlTexture1D(),
            (handle, args) -> new GlTexture1D(handle));

    static final BindableMeta<Texture.TexTarget<Texture1DArray>, Texture1DArray> Texture1DArrayMeta = createTextureMeta(
            args -> new GlTexture1DArray(),
            (handle, args) -> new GlTexture1DArray(handle));

    static final BindableMeta<Texture.TexTarget<TextureRectangle>, TextureRectangle> TextureRectangleMeta = createTextureMeta(
            args -> new GlTextureRectangle(),
            (handle, args) -> new GlTextureRectangle(handle));

    static final BindableMeta<Texture.TexTarget<Texture2D>, Texture2D> Texture2DMeta = createTextureMeta(
            args -> new GlTexture2D(),
            (handle, args) -> new GlTexture2D(handle));

    static final BindableMeta<Texture.TexTarget<Texture2DArray>, Texture2DArray> Texture2DArrayMeta = createTextureMeta(
            args -> new GlTexture2DArray(),
            (handle, args) -> new GlTexture2DArray(handle));

    static final BindableMeta<Texture.TexTarget<Texture3D>, Texture3D> Texture3DMeta = createTextureMeta(
            args -> new GlTexture3D(),
            (handle, args) -> new GlTexture3D(handle));

    static final BindableMeta<Texture.TexTarget<TextureBuffer>, TextureBuffer> TextureBufferMeta = createTextureMeta(
            args -> new GlTextureBuffer(),
            (handle, args) -> new GlTextureBuffer(handle));

    static final BindableMeta<Texture.TexTarget<TextureCubeMap>, TextureCubeMap> TextureCubeMeta = createTextureMeta(
            args -> new GlTextureCubeMap(),
            (handle, args) -> new GlTextureCubeMap(handle));

    static final BindableMeta<Texture.TexTarget<TextureCubeMapArray>, TextureCubeMapArray> TextureCubeMapArrayMeta = createTextureMeta(
            args -> new GlTextureCubeMapArray(),
            (handle, args) -> new GlTextureCubeMapArray(handle));

    static final BindableMeta<Texture.TexTarget<Texture2DMultiSample>, Texture2DMultiSample> Texture2DMultiSampleMeta = createTextureMeta(
            args -> new GlTexture2DMultiSample(),
            (handle, args) -> new GlTexture2DMultiSample(handle));

    static final BindableMeta<Texture.TexTarget<Texture2DMultiSampleArray>, Texture2DMultiSampleArray> Texture2DMultiSampleArrayMeta = createTextureMeta(
            args -> new GlTexture2DMultiSampleArray(),
            (handle, args) -> new GlTexture2DMultiSampleArray(handle));

    static final Meta<Sync> SyncMeta = new DefaultMeta<>(args -> new GlSync());
    static final Meta<ImageUnit> ImageUnitMeta = new DefaultMeta<>(args -> new GlImageUnit((int) args[0]));

    static final HandleMeta<Query.SampleQuery> SampleQueryMeta = new IntHandleMeta<>(
            args -> new GlQuery.GlSampleQuery((Query.SampleType) args[0]),
            GLX::glDeleteQueries,
            (handle, args) -> new GlQuery.GlSampleQuery(handle, (Query.SampleType) args[0]),
            GLX::glGenQueries,
            GLX::glDeleteQueries);

    static final HandleMeta<Query.IndexQuery> IndexQueryMeta = new IntHandleMeta<>(
            args -> new GlQuery.GlIndexQuery((Query.IndexType) args[0]),
            GLX::glDeleteQueries,
            (handle, args) -> new GlQuery.GlIndexQuery(handle, (Query.IndexType) args[0]),
            GLX::glGenQueries,
            GLX::glDeleteQueries);

    static final HandleMeta<Query.TimerQuery> TimerQueryMeta = new IntHandleMeta<>(
            args -> new GlQuery.GlTimerQuery(),
            GLX::glDeleteQueries,
            (handle, args) -> new GlQuery.GlTimerQuery(handle),
            GLX::glGenQueries,
            GLX::glDeleteQueries);

    @SuppressWarnings("unchecked")
    public GlMetaService() {
        register(Buffer.class, BufferMeta);
        register(BaseFrameBuffer.class, FrameBufferMeta);
        register(FrameBuffer.class, FrameBufferMeta);
        register(VertexArray.class, VertexArrayMeta);
        register(TransformFeedback.class, TransformFeedbackMeta);
        register(RenderBuffer.class, RenderBufferMeta);
        register(Program.class, ProgramMeta);
        register(TextureUnit.class, TextureUnitMeta);
        register(Sampler.class, SamplerMeta);
        register(Texture1D.class, Texture1DMeta);
        register(Texture1DArray.class, Texture1DArrayMeta);
        register(TextureRectangle.class, TextureRectangleMeta);
        register(Texture2D.class, Texture2DMeta);
        register(Texture2DArray.class, Texture2DArrayMeta);
        register(Texture3D.class, Texture3DMeta);
        register(TextureBuffer.class, TextureBufferMeta);
        register(TextureCubeMap.class, TextureCubeMeta);
        register(TextureCubeMapArray.class, TextureCubeMapArrayMeta);
        register(Texture2DMultiSample.class, Texture2DMultiSampleMeta);
        register(Texture2DMultiSampleArray.class, Texture2DMultiSampleArrayMeta);
        register(Shader.class, ShaderMeta);
        register(ImageUnit.class, ImageUnitMeta);
        register(Sync.class, SyncMeta);
        register(Query.SampleQuery.class, SampleQueryMeta);
        register(Query.IndexQuery.class, IndexQueryMeta);
        register(Query.TimerQuery.class, TimerQueryMeta);
        register(Query.State.class, (Function<Object[], Query.State>) args -> new GlQuery.GlState());
        register(Pipeline.class, PipelineMeta);
        register(ShaderArray.class, (Function<Object[], ShaderArray>) args -> new GlShaderArray((List<Shader>) args[0]));
    }

    @Override
    public int queryInt(String id) {
        try {
            if (id.startsWith("GET.")) {
                String mid = id.substring(4);
                return GLX.glGetInteger(queryStaticIntField(GLX.class, "GL_", mid).orElseThrow(() -> new IllegalArgumentException(mid)));
            } else {
                return queryStaticIntField(GLX.class, "GL_", id).orElseThrow(() -> new IllegalArgumentException(id));
            }
        } finally {
            var error = getError();
            if (error != GraphicsErrorCode.NoError) {
                log.warn("Error query int {}: {}({})", id, error.description(), error.code());
            }
        }
    }

    @Override
    public ErrorCode getError() {
        int code = GLX.glGetError();
        if (code == GLX.GL_NO_ERROR) {
            return GraphicsErrorCode.NoError;
        }
        code = code & 0xff;
        return switch (code) {
            case GLX.GL_INVALID_ENUM & 0xf -> GraphicsErrorCode.InvalidEnum;
            case GLX.GL_INVALID_OPERATION & 0xf -> GraphicsErrorCode.InvalidOperation;
            case GLX.GL_INVALID_VALUE & 0xf -> GraphicsErrorCode.InvalidValue;
            case GLX.GL_STACK_OVERFLOW & 0xf -> GraphicsErrorCode.StackOverflow;
            case GLX.GL_STACK_UNDERFLOW & 0xf -> GraphicsErrorCode.StackUnderflow;
            case GLX.GL_INVALID_FRAMEBUFFER_OPERATION & 0xf -> GraphicsErrorCode.InvalidFramebufferOperation;
            case GLX.GL_OUT_OF_MEMORY & 0xf -> GraphicsErrorCode.OutOfMemory;
            default -> GraphicsErrorCode.Unknown;
        };
    }

    @Override
    public void close() {
        // no ops
    }

    private static <T extends Texture<T>>
    BindableMeta<Texture.TexTarget<T>, T> createTextureMeta(Function<Object[], T> factory,
                                                            BiFunction<Integer, Object[], T> init) {
        return new IntBindableMeta<>(
                factory,
                GLX::glBindTexture,
                GLX::glBindTexture,
                0,
                GLX::glDeleteTextures,
                init,
                GLX::glGenTextures,
                GLX::glDeleteTextures
        );
    }

}
