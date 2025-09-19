package com.vanix.easygl.commons.bufferio;

import com.vanix.easygl.commons.collection.eclipse.*;
import com.vanix.easygl.commons.util.TypeReference;
import com.vanix.easygl.commons.util.TypeReferenceBean;
import org.joml.*;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntFunction;

@SuppressWarnings({"unchecked", "rawtypes"})
public class BufferIORegistry {
    private static final Map<Object, BufferIO<?>> REGISTRY = new ConcurrentHashMap<>();
    private static final Map<Class<?>, IntFunction<BufferIO>> COLLECTION_TYPES = new ConcurrentHashMap<>();

    static {
        register(byte.class, new ByteBufferIO());
        register(short.class, new ShortBufferIO());
        register(int.class, new IntBufferIO());
        register(float.class, new FloatBufferIO());
        register(long.class, new LongBufferIO());
        register(double.class, new DoubleBufferIO());
        register(Vector2f.class, new Vector2fBufferIO());
        register(Vector3i.class, new Vector3iBufferIO());
        register(Vector3f.class, new Vector3fBufferIO());
        register(Vector3d.class, new Vector3dBufferIO());
        register(Matrix4f.class, new Matrix4fBufferIO());
        register(Matrix4d.class, new Matrix4dBufferIO());

        registerCollection(byte[].class, ByteArrayBufferIO::new);
        registerCollection(short[].class, ShortArrayBufferIO::new);
        registerCollection(int[].class, IntArrayBufferIO::new);
        registerCollection(float[].class, FloatArrayBufferIO::new);
        registerCollection(long[].class, LongArrayBufferIO::new);
        registerCollection(double[].class, DoubleArrayBufferIO::new);
        registerCollection(ByteBufferArrayList.class, count -> new BufferListBufferIO(count, Byte.BYTES, c -> new ByteBufferArrayList(c, (byte) 0)));
        registerCollection(ShortBufferArrayList.class, count -> new BufferListBufferIO(count, Short.BYTES, c -> new ShortBufferArrayList(c, (short) 0)));
        registerCollection(IntBufferArrayList.class, count -> new BufferListBufferIO(count, Integer.BYTES, c -> new IntBufferArrayList(c, 0)));
        registerCollection(FloatBufferArrayList.class, count -> new BufferListBufferIO(count, Float.BYTES, c -> new FloatBufferArrayList(c, 0f)));
        registerCollection(LongBufferArrayList.class, count -> new BufferListBufferIO(count, Long.BYTES, c -> new LongBufferArrayList(c, 0L)));
        registerCollection(DoubleBufferArrayList.class, count -> new BufferListBufferIO(count, Double.BYTES, c -> new DoubleBufferArrayList(c, 0d)));
    }

    public static <T> void register(Class<T> type, BufferIO<T> bufferIO) {
        if (REGISTRY.containsKey(type)) {
            throw new IllegalArgumentException("Duplicated type: " + type);
        }
        REGISTRY.put(type, bufferIO);
    }

    public static <T> void registerCollection(Class<T> type, IntFunction<BufferIO> factory) {
        if (COLLECTION_TYPES.containsKey(type)) {
            throw new IllegalArgumentException("Duplicated type: " + type);
        }
        COLLECTION_TYPES.put(type, factory);
    }

    static <T> BufferIO<T> get(T bean) {
        Class<T> type = (Class<T>) bean.getClass();
        if (type.isArray()) {
            return getByType(type, Array.getLength(bean));
        } else if (bean instanceof BufferList bufferList) {
            return getByType(type, bufferList.size());
        } else {
            return getByType(type);
        }
    }

    static <T> BufferIO<T> getByType(TypeReference<T> typeReference, int... lengths) {
        if (lengths.length == 0 && typeReference instanceof TypeReferenceBean<T> typeReferenceBean) {
            if (typeReferenceBean.getBean() instanceof List list) {
                return getByType(typeReferenceBean.getType(), list.size());
            }
        }
        return getByType(typeReference.getType(), lengths);
    }

    static <T> BufferIO<T> getByType(Type type, int... lengths) {
        if (lengths == null || lengths.length == 0) {
            var bufferIO = REGISTRY.get(type);
            if (bufferIO == null) {
                bufferIO = createByType(type, 0);
                REGISTRY.put(type, bufferIO);
            }
            return (BufferIO<T>) bufferIO;
        } else {
            var key = new RegistryKey(type, lengths);
            var bufferIO = REGISTRY.get(key);
            if (bufferIO == null) {
                bufferIO = createByType(key.type, 0, key.lengths);
                REGISTRY.put(key, bufferIO);
            }
            return (BufferIO<T>) bufferIO;
        }
    }

    private static <T> BufferIO<T> createByType(Type type, int i, int... lengths) {
        BufferIO bufferIO = null;
        if (type instanceof Class<?> clazz) {
            bufferIO = createByClass(clazz, i, lengths);
        } else if (type instanceof ParameterizedType parameterizedType) {
            if (parameterizedType.getRawType().equals(List.class)) {
                var paramType = parameterizedType.getActualTypeArguments()[0];
                if (paramType instanceof ParameterizedType innerParamType) {
                    bufferIO = createByType(innerParamType, i + 1, lengths);
                } else if (paramType instanceof Class<?> clazz) {
                    bufferIO = createByClass(clazz, i + 1, lengths);
                } else {
                    throw new BufferIOException("Unsupported List parameter type: " + paramType);
                }
                bufferIO = new ListBufferIO(bufferIO, lengths[i]);
            }
        }
        if (bufferIO == null) {
            throw new BufferIOException("Unsupported type: " + type);
        }
        return bufferIO;
    }

    private static BufferIO createByClass(Class type, int i, int[] lengths) {
        BufferIO bufferIO;
        IntFunction<BufferIO> factory;
        if ((factory = COLLECTION_TYPES.get(type)) != null) {
            bufferIO = factory.apply(lengths[i++]);
        } else if (type.isArray()) {
            bufferIO = createByClass(type.getComponentType(), i + 1, lengths);
        } else {
            bufferIO = REGISTRY.get(type);
            if (bufferIO == null) {
                bufferIO = new StructBufferIO(type);
                REGISTRY.put(type, bufferIO);
            }
        }
        if (i == lengths.length) {
            return bufferIO;
        } else if (i == lengths.length - 1) {
            return new ArrayBufferIO(bufferIO, type, lengths[i]);
        } else {
            throw new BufferIOException("Expect length of " + i + " but found " + lengths.length);
        }
    }

    record RegistryKey(Type type, int... lengths) {

    }

}
