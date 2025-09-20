package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.graphics.*;
import com.vanix.easygl.graphics.feature.*;
import com.vanix.easygl.opengl.feature.*;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class GlGraphics implements Graphics {
    public static final GLCapabilities CAPABILITIES = GL.createCapabilities();
    private final DepthTest depthTest = new GlDepthTest(this);
    private final Blending blending = new GlBlending(this);
    private final StencilTest stencilTest = new GlStencilTest(this);
    private final ScissorTest scissorTest = new GlScissorTest(this);
    private final LogicalOperation logicalOperation = new GlLogicalOperation(this);
    private final DefaultFrameBuffer defaultFrameBuffer = new GlDefaultFrameBuffer();
    private final PixelStorageMode pixelStorageMode = new GlPixelStorageMode(this);
    private final PrimitiveRestart primitiveRestart = new GlPrimitiveRestart(this);
    private final Clipping clipping = new GlClipping(this);
    private final Debug debug;
    private final Viewport[] viewports = new Viewport[Viewport.MaxViewports];

    public GlGraphics() {
        if (CAPABILITIES.OpenGL43) {
            debug = new GlDebug(this);
        } else {
            debug = null;
        }
    }

    @Override
    public Graphics viewport(int x, int y, int width, int height) {
        GLX.glViewport(x, y, width, height);
        return this;
    }

    @Override
    public Viewport viewport(int index) {
        var viewport = viewports[index];
        if (viewport == null) {
            viewports[index] = viewport = new GlViewport(index);
        }
        return viewport;
    }

    @Override
    public Graphics setDepthRange(double near, double far) {
        GLX.glDepthRange(near, far);
        return this;
    }

    @Override
    public Graphics setDepthRange(float near, float far) {
        GLX.glDepthRangef(near, far);
        return this;
    }

    @Override
    public float[] getDepthRangeFloat() {
        var data = new float[2];
        GLX.glGetFloatv(GLX.GL_DEPTH_RANGE, data);
        return data;
    }

    @Override
    public double[] getDepthRangeDouble() {
        var data = new double[2];
        GLX.glGetDoublev(GLX.GL_DEPTH_RANGE, data);
        return data;
    }

    @Override
    public int[] getDepthRangeIntMapped() {
        var data = new int[2];
        GLX.glGetIntegerv(GLX.GL_DEPTH_RANGE, data);
        return data;
    }

    @Override
    public Graphics setMemoryBarrier(MemoryBarrier memoryBarrier) {
        GLX.glMemoryBarrier(memoryBarrier.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setMemoryBarrier(BitSet<MemoryBarrier> memoryBarriers) {
        GLX.glMemoryBarrier(memoryBarriers.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setMemoryBarrierByRegion(MemoryBarrier.Regional memoryBarrier) {
        GLX.glMemoryBarrierByRegion(memoryBarrier.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setMemoryBarrierByRegion(BitSet<MemoryBarrier.Regional> memoryBarriers) {
        GLX.glMemoryBarrierByRegion(memoryBarriers.value());
        GLX.checkError();
        return this;
    }

    @Override
    public PixelStorageMode pixelStorageMode() {
        return pixelStorageMode;
    }

    @Override
    public Graphics provokingVertex(boolean first) {
        GLX.glProvokingVertex(first ? GLX.GL_FIRST_VERTEX_CONVENTION : GLX.GL_LAST_VERTEX_CONVENTION);
        return this;
    }

    @Override
    public Graphics dispatchCompute(int numGroupsX, int numGroupsY, int numGroupsZ) {
        GLX.glDispatchCompute(numGroupsX, numGroupsY, numGroupsZ);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics dispatchComputeIndirect(int numGroupsX, int numGroupsY, int numGroupsZ) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocInt(3).put(numGroupsX).put(numGroupsY).put(numGroupsZ).clear();
            GLX.glDispatchComputeIndirect(MemoryUtil.memAddress(buffer));
            GLX.checkError();
            return this;
        }
    }

    @Override
    public int getMaxComputeWorkGroupCountX() {
        return GLX.glGetIntegeri(GLX.GL_MAX_COMPUTE_WORK_GROUP_COUNT, 0);
    }

    @Override
    public int getMaxComputeWorkGroupCountY() {
        return GLX.glGetIntegeri(GLX.GL_MAX_COMPUTE_WORK_GROUP_COUNT, 1);
    }

    @Override
    public int getMaxComputeWorkGroupCountZ() {
        return GLX.glGetIntegeri(GLX.GL_MAX_COMPUTE_WORK_GROUP_COUNT, 2);
    }

    @Override
    public Graphics hint(HintTarget target, HintMode mode) {
        GLX.glHint(target.value(), mode.value());
        return this;
    }

    @Override
    public ResetStatus getRestStatus() {
        return IntEnum.valueOf(ResetStatus.class, GLX.glGetGraphicsResetStatus());
    }

    private PrecisionFormat getPrecisionFormat(PrecisionFormat.Type type, int shaderType) {
        try (var stack = MemoryStack.stackPush()) {
            var range = stack.mallocInt(1);
            var precision = stack.mallocInt(1);
            GLX.glGetShaderPrecisionFormat(shaderType, type.value(), range, precision);
            return new PrecisionFormat(type, range.get(), precision.get());
        }
    }

    @Override
    public PrecisionFormat getVertexShaderPrecisionFormat(PrecisionFormat.Type type) {
        return getPrecisionFormat(type, GLX.GL_VERTEX_SHADER);
    }

    @Override
    public PrecisionFormat getFragmentShaderPrecisionFormat(PrecisionFormat.Type type) {
        return getPrecisionFormat(type, GLX.GL_FRAGMENT_SHADER);
    }

    @Override
    public Clipping clipDistances() {
        return clipping;
    }

    @Override
    public DepthTest depthTest() {
        return depthTest;
    }

    @Override
    public Blending blending() {
        return blending;
    }

    @Override
    public StencilTest stencilTest() {
        return stencilTest;
    }

    @Override
    public ScissorTest scissorTest() {
        return scissorTest;
    }

    @Override
    public LogicalOperation logicalOperation() {
        return logicalOperation;
    }

    @Override
    public DefaultFrameBuffer defaultFrameBuffer() {
        return defaultFrameBuffer;
    }

    @Override
    public Debug debug() {
        return debug;
    }

    @Override
    public PrimitiveRestart primitiveRestart() {
        return primitiveRestart;
    }

    @Override
    public Graphics flush() {
        GLX.glFlush();
        return this;
    }

    @Override
    public Graphics awaitFinish() {
        GLX.glFinish();
        return this;
    }

    @Override
    public Graphics releaseShaderCompiler() {
        GLX.glReleaseShaderCompiler();
        return this;
    }

    @Override
    public Vector2f getMultiSample(int index) {
        float[] data = new float[2];
        GLX.glGetMultisamplefv(GLX.GL_SAMPLE_POSITION, index, data);
        GLX.checkError();
        return new Vector2f(data);
    }

    @Override
    public Graphics minSampleShading(float value) {
        GLX.glMinSampleShading(value);
        return this;
    }

    @Override
    public Graphics setPointSize(float size) {
        GLX.glPointSize(size);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setPointFadeThresholdSize(float size) {
        GLX.glPointParameterf(GLX.GL_POINT_FADE_THRESHOLD_SIZE, size);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setPointSpriteCoordOrigin(Origin origin) {
        GLX.glPointParameteri(GLX.GL_POINT_SPRITE_COORD_ORIGIN, origin.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setLineWidth(float width) {
        GLX.glLineWidth(width);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setFrontFaceDirection(FrontFaceDirection direction) {
        GLX.glFrontFace(direction.value());
        return this;
    }

    @Override
    public Graphics setPolygonOffset(float factor, float units) {
        GLX.glPolygonOffset(factor, units);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setPolygonMode(Face face, PolygonMode mode) {
        GLX.glPolygonMode(face.value(), mode.value());
        return this;
    }

    @Override
    public float getPointSize() {
        return GLX.glGetFloat(GLX.GL_POINT_SIZE);
    }

    @Override
    public float getPointFadeThresholdSize() {
        return GLX.glGetFloat(GLX.GL_POINT_FADE_THRESHOLD_SIZE);
    }

    @Override
    public Origin getPointSpriteCoordOrigin() {
        return switch (GLX.glGetInteger(GLX.GL_POINT_SPRITE_COORD_ORIGIN)) {
            case GLX.GL_LOWER_LEFT -> Origin.LowerLeft;
            case GLX.GL_UPPER_LEFT -> Origin.UpperLeft;
            default -> {
                GLX.checkError();
                yield null;
            }
        };
    }

    @Override
    public float getLineWidth() {
        return GLX.glGetFloat(GLX.GL_LINE_WIDTH);
    }

    @Override
    public FrontFaceDirection getFrontFaceDirection() {
        return switch (GLX.glGetInteger(GLX.GL_FRONT_FACE)) {
            case GLX.GL_CW -> FrontFaceDirection.Clockwise;
            case GLX.GL_CCW -> FrontFaceDirection.Counterclockwise;
            default -> {
                GLX.checkError();
                yield null;
            }
        };
    }

    @Override
    public Graphics setCullFaceMode(CullFaceMode mode) {
        GLX.glCullFace(mode.value());
        return this;
    }

    @Override
    public CullFaceMode getCullFaceMode() {
        return switch (GLX.glGetInteger(GLX.GL_CULL_FACE_MODE)) {
            case GLX.GL_FRONT -> CullFaceMode.Front;
            case GLX.GL_BACK -> CullFaceMode.Back;
            case GLX.GL_FRONT_AND_BACK -> CullFaceMode.FrontAndBack;
            default -> {
                GLX.checkError();
                yield null;
            }
        };
    }

    @Override
    public PolygonMode[] getPolygonMode() {
        int[] values = new int[2];
        GLX.glGetIntegerv(GLX.GL_POLYGON_MODE, values);
        GLX.checkError();
        return new PolygonMode[]{Cache.PolygonMode.valueOf(values[0]), Cache.PolygonMode.valueOf(values[1])};
    }

    @Override
    public float getPolygonOffsetFactor() {
        return GLX.glGetFloat(GLX.GL_POLYGON_OFFSET_FACTOR);
    }

    @Override
    public float getPolygonOffsetUnits() {
        return GLX.glGetFloat(GLX.GL_POLYGON_OFFSET_UNITS);
    }

    @Override
    public Graphics enable(Capability feature) {
        GLX.glEnable(feature.value());
        return this;
    }

    @Override
    public Graphics disable(Capability feature) {
        GLX.glDisable(feature.value());
        return this;
    }

    @Override
    public boolean isEnable(Capability feature) {
        return GLX.glIsEnabled(feature.value());
    }

    @Override
    public Graphics then() {
        return this;
    }

    @Override
    public void close() {

    }

    @Override
    public Graphics setPathVertices(int count) {
        GLX.glPatchParameteri(GLX.GL_PATCH_VERTICES, count);
        return this;
    }

    @Override
    public Graphics setPathDefaultOuterLevel(float[] values) {
        GLX.glPatchParameterfv(GLX.GL_PATCH_DEFAULT_OUTER_LEVEL, values);
        return this;
    }

    @Override
    public Graphics setPathDefaultOuterLevel(FloatBuffer value) {
        GLX.glPatchParameterfv(GLX.GL_PATCH_DEFAULT_OUTER_LEVEL, value);
        return this;
    }

    @Override
    public Graphics setPathDefaultInnerLevel(float[] values) {
        GLX.glPatchParameterfv(GLX.GL_PATCH_DEFAULT_INNER_LEVEL, values);
        return this;
    }

    @Override
    public Graphics setPathDefaultInnerLevel(FloatBuffer value) {
        GLX.glPatchParameterfv(GLX.GL_PATCH_DEFAULT_INNER_LEVEL, value);
        return this;
    }
}
