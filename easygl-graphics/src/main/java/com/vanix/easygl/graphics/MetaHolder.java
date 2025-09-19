package com.vanix.easygl.graphics;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.HandleMeta;
import com.vanix.easygl.core.meta.MetaSystem;

class MetaHolder {
    static final BindableMeta<BindTarget.Default<VertexArray>, VertexArray> VertexArray = MetaSystem.Graphics.of(VertexArray.class);
    static final BindableMeta<BindTarget.Default<TransformFeedback>, TransformFeedback> TransformFeedback = MetaSystem.Graphics.of(TransformFeedback.class);
    static final HandleMeta<Sampler> Sampler = MetaSystem.Graphics.of(Sampler.class);
    static final BindableMeta<Texture.TexTarget<Texture1DArray>, Texture1DArray> Texture1DArray = MetaSystem.Graphics.of(Texture1DArray.class);
    static final BindableMeta<Texture.TexTarget<TextureRectangle>, TextureRectangle> TextureRectangle = MetaSystem.Graphics.of(TextureRectangle.class);
    static final BindableMeta<Texture.TexTarget<Texture1D>, Texture1D> Texture1D = MetaSystem.Graphics.of(Texture1D.class);
    static final BindableMeta<Texture.TexTarget<TextureCubeMap>, TextureCubeMap> TextureCubeMap = MetaSystem.Graphics.of(TextureCubeMap.class);
    static final BindableMeta<Texture.TexTarget<TextureCubeMapArray>, TextureCubeMapArray> TextureCubeMapArray = MetaSystem.Graphics.of(TextureCubeMapArray.class);
    static final BindableMeta<Texture.TexTarget<Texture2DMultiSample>, Texture2DMultiSample> Texture2DMultiSample = MetaSystem.Graphics.of(Texture2DMultiSample.class);
    static final BindableMeta<Texture.TexTarget<Texture2DMultiSampleArray>, Texture2DMultiSampleArray> Texture2DMultiSampleArray = MetaSystem.Graphics.of(Texture2DMultiSampleArray.class);
    static final BindableMeta<Texture.TexTarget<Texture2D>, Texture2D> Texture2D = MetaSystem.Graphics.of(Texture2D.class);
    static final BindableMeta<Texture.TexTarget<Texture2DArray>, Texture2DArray> Texture2DArray = MetaSystem.Graphics.of(Texture2DArray.class);
    static final BindableMeta<Texture.TexTarget<Texture3D>, Texture3D> Texture3D = MetaSystem.Graphics.of(Texture3D.class);
    static final BindableMeta<Texture.TexTarget<TextureBuffer>, TextureBuffer> TextureBuffer = MetaSystem.Graphics.of(TextureBuffer.class);
    static final BindableMeta<BindTarget.Default<TextureUnit>, TextureUnit> TextureUnit = MetaSystem.Graphics.of(TextureUnit.class);
    static final com.vanix.easygl.core.meta.Meta<ImageUnit> ImageUnit = MetaSystem.Graphics.of(ImageUnit.class);
    static final com.vanix.easygl.core.meta.Meta<Sync> Sync = MetaSystem.Graphics.of(Sync.class);
    static final HandleMeta<Shader> Shader = MetaSystem.Graphics.of(Shader.class);
    static final BindableMeta<BindTarget.Default<RenderBuffer>, RenderBuffer> RenderBuffer = MetaSystem.Graphics.of(RenderBuffer.class);
    static final HandleMeta<Query.TimerQuery> TimerQuery = MetaSystem.Graphics.of(Query.TimerQuery.class);
    static final HandleMeta<Query.IndexQuery> IndexQuery = MetaSystem.Graphics.of(Query.IndexQuery.class);
    static final HandleMeta<Query.SampleQuery> SampleQuery = MetaSystem.Graphics.of(Query.SampleQuery.class);
    static final BindableMeta<BindTarget.Default<Program>, Program> Program = MetaSystem.Graphics.of(Program.class);
    @SuppressWarnings("rawtypes")
    static final BindableMeta BaseFrameBuffer = MetaSystem.Graphics.of(BaseFrameBuffer.class);
    static final BindableMeta<BaseFrameBuffer.Target<FrameBuffer>, FrameBuffer> FrameBuffer = MetaSystem.Graphics.of(FrameBuffer.class);
    static final BindableMeta<com.vanix.easygl.graphics.Buffer.Target, Buffer> Buffer = MetaSystem.Graphics.of(Buffer.class);
    static final BindableMeta<BindTarget.Default<Pipeline>, Pipeline> Pipeline = MetaSystem.Graphics.of(Pipeline.class);
}
