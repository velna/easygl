package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.commons.util.IntEnumCache;
import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.InternalPixelFormat;
import com.vanix.easygl.graphics.PixelFormat;
import com.vanix.easygl.graphics.*;
import com.vanix.easygl.graphics.feature.Blending;
import com.vanix.easygl.graphics.feature.Debug;
import com.vanix.easygl.graphics.feature.LogicalOperation;
import com.vanix.easygl.graphics.feature.StencilTest;
import org.eclipse.collections.api.factory.primitive.IntObjectMaps;
import org.eclipse.collections.api.map.primitive.IntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import java.lang.ref.Cleaner;

public class Cache {
    public static final Cleaner CLEANER = Cleaner.create();
    public static final IntEnumCache<Sync.Result> SyncResult = new IntEnumCache<>(Sync.Result.class, 0xf);
    public static final IntEnumCache<LogicalOperation.Op> LogicalOp = new IntEnumCache<LogicalOperation.Op>(LogicalOperation.Op.class, 0xf);
    public static final IntEnumCache<Blending.Function> BlendingFunction = new IntEnumCache<>(Blending.Function.class, 2, 0xf);
    public static final IntEnumCache<Blending.Equation> BlendingEquation = new IntEnumCache<>(Blending.Equation.class, 0xf);
    public static final IntEnumCache<PolygonMode> PolygonMode = new IntEnumCache<>(PolygonMode.class, 0xf);
    public static final IntEnumCache<CompareFunction> CompareFunction = new IntEnumCache<>(CompareFunction.class, 0xf);
    public static final IntEnumCache<StencilTest.Op> StencilTestOp = new IntEnumCache<>(StencilTest.Op.class, 1, 0xf);
    public static final IntEnumCache<Debug.Source> DebugSource = new IntEnumCache<>(Debug.Source.class, 0xf);
    public static final IntEnumCache<Debug.Type> DebugType = new IntEnumCache<>(Debug.Type.class, 0xf);
    public static final IntEnumCache<Buffer.DataUsage> BufferDataUsage = new IntEnumCache<>(Buffer.DataUsage.class, 0xf);
    public static final IntEnumCache<Debug.Severity> DebugSeverity = new IntEnumCache<>(Debug.Severity.class, 0xf);
    public static final IntObjectMap<FrameInnerBuffer.ReadBuffer> FrameReadBuffer = intObjectMapOf(FrameInnerBuffer.ReadBuffer.class);
    public static final IntObjectMap<BaseFrameBuffer.Status> FrameBufferStatus = intObjectMapOf(BaseFrameBuffer.Status.class);
    public static final IntObjectMap<DataType> DataType = intObjectMapOf(DataType.class);
    public static final IntObjectMap<MinFilter> MinFilter = intObjectMapOf(MinFilter.class);
    public static final IntEnumCache<MagFilter> MagFilter = new IntEnumCache<>(MagFilter.class, 0xf);
    public static final IntEnumCache<Texture.Wrap> TextureWrap = new IntEnumCache<>(Texture.Wrap.class, 0xf);
    public static final IntObjectMap<PixelFormat> PixelFormat = intObjectMapOf(PixelFormat.class);
    public static final IntObjectMap<InternalPixelFormat> InternalPixelFormatCache = internalPixelFormatCache();
    public static final IntEnumCache<Access> Access = new IntEnumCache<>(Access.class, 0xf);

    private static <T extends IntEnum> IntObjectMap<T> intObjectMapOf(Class<T> type) {
        T[] array = IntEnum.values(type);
        MutableIntObjectMap<T> map = IntObjectMaps.mutable.of();
        for (var e : array) {
            map.put(e.value(), e);
        }
        return map;
    }

    private static IntObjectMap<InternalPixelFormat> internalPixelFormatCache() {
        MutableIntObjectMap<InternalPixelFormat> map = IntObjectMaps.mutable.of();
        for (var format : InternalPixelFormat.Compressed.values()) {
            map.put(format.value(), format);
        }
        for (var format : InternalPixelFormat.Sized.values()) {
            map.put(format.value(), format);
        }
        for (var format : InternalPixelFormat.Base.values()) {
            map.put(format.value(), format);
        }
        return map;
    }
}
