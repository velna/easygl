package com.vanix.easygl.commons.bufferio;

import com.vanix.easygl.commons.util.LambdaUtils;
import com.vanix.easygl.commons.util.SerializableBiConsumer;
import com.vanix.easygl.commons.util.SerializableFunction;
import org.apache.commons.beanutils2.PropertyUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class StructBufferIO<T> implements BufferIO<T> {
    private final Constructor<T> defaultConstructor;
    private final Map<String, BindingMeta<?>> metaMap;
    private final int size;

    @SuppressWarnings("unchecked")
    public StructBufferIO(Class<T> type) {
        Constructor<T> structConstructor = null;
        Constructor<T> defaultConstructor = null;
        for (var constructor : type.getConstructors()) {
            if (constructor.isAnnotationPresent(BufferStruct.class)) {
                if (structConstructor != null) {
                    throw new BufferIOException("Multiple BufferStruct constructor found in class: " + type);
                }
                structConstructor = (Constructor<T>) constructor;
            } else if (constructor.getParameters().length == 0) {
                defaultConstructor = (Constructor<T>) constructor;
            }
        }
        if (structConstructor == null) {
            throw new BufferIOException("No BufferStruct constructor found in class: " + type);
        }
        if (defaultConstructor == null) {
            throw new BufferIOException("No default constructor found in class: " + type);
        }
        this.defaultConstructor = defaultConstructor;
        this.metaMap = new LinkedHashMap<>();
        int size = 0;
        var descriptors = PropertyUtils.getPropertyDescriptors(structConstructor.getDeclaringClass());
        for (var param : structConstructor.getParameters()) {
            var paramType = (Class<Object>) param.getType();
            var bufferField = param.getAnnotation(BufferField.class);
            BufferIO<Object> bufferIo;
            if (bufferField == null || bufferField.count() <= 0) {
                bufferIo = BufferIO.ofType(paramType);
            } else {
                bufferIo = BufferIO.ofType(paramType, bufferField.count());
            }
            int dataSize = bufferIo.size();
            String fieldName = bufferField != null && !bufferField.name().isEmpty() ? bufferField.name() : param.getName();
            var descriptor = findDescriptor(descriptors, fieldName, paramType);
            if (descriptor == null) {
                throw new BufferIOException("Can not find property of constructor param " + param + " for class " + structConstructor.getDeclaringClass());
            }
            metaMap.put(fieldName, new StructBufferIO.BindingMeta<>(size, new StructBufferIO.PropertyIO(descriptor), bufferIo));
            size += dataSize;
        }
        this.size = size;
    }

    private StructBufferIO(Constructor<T> defaultConstructor, Map<String, BindingMeta<?>> metaMap, int size) {
        this.defaultConstructor = defaultConstructor;
        this.metaMap = metaMap;
        this.size = size;
    }

    private PropertyDescriptor findDescriptor(PropertyDescriptor[] descriptors, String name, Class<?> type) {
        for (var descriptor : descriptors) {
            if (descriptor.getName().equals(name) && descriptor.getPropertyType().equals(type)) {
                return descriptor;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void write(@Nonnull T object, ByteBuffer buffer) {
        for (BindingMeta meta : metaMap.values()) {
            var value = meta.propertyIO.getProperty(object);
            if (value != null) {
                meta.bufferIO.write(value, buffer);
            } else {
                buffer.position(buffer.position() + meta.bufferIO.size());
            }
        }
    }

    public <F> void writeField(@Nullable T object, ByteBuffer buffer, SerializableFunction<T, F> fieldGetter) {
        String name = LambdaUtils.resolvePropertyName(fieldGetter);
        BindingMeta<F> meta = getBindingMeta(name);
        meta.bufferIO.write(fieldGetter.apply(object), buffer);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public T read(@Nullable T object, ByteBuffer buffer, @Nullable Consumer<T> setter) {
        boolean setFlag = false;
        if (object == null) {
            try {
                object = defaultConstructor.newInstance();
                setFlag = true;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new BufferIOException("Error invoke default construct: ", e);
            }
        }
        for (BindingMeta meta : metaMap.values()) {
            Object value = meta.propertyIO.getProperty(object);
            meta.bufferIO.read(value, buffer, v -> meta.propertyIO.setProperty(value, v));
        }
        if (setFlag && setter != null) {
            setter.accept(object);
        }
        return object;
    }

    @SuppressWarnings("unchecked")
    public <F> F readField(@Nullable T object, ByteBuffer buffer, SerializableBiConsumer<T, F> fieldSetter) {
        String name = LambdaUtils.resolvePropertyName(fieldSetter);
        BindingMeta<F> meta = getBindingMeta(name);
        var originValue = meta.propertyIO.getProperty(object);
        F value = meta.bufferIO.read((F) originValue, buffer, null);
        if (originValue != value) {
            fieldSetter.accept(object, value);
        }
        return value;
    }

    public <F> FieldBufferIO<F> getFieldBufferIO(SerializableFunction<T, F> fieldGetter) {
        String name = LambdaUtils.resolvePropertyName(fieldGetter);
        return getBindingMeta(name);
    }

    @SuppressWarnings("unchecked")
    private <F> BindingMeta<F> getBindingMeta(String fieldName) {
        var meta = metaMap.get(fieldName);
        if (meta == null) {
            throw new BufferIOException("Can not find field of field " + fieldName + " within type " + defaultConstructor.getDeclaringClass());
        }
        return (BindingMeta<F>) meta;
    }

    public static class Builder<T> {
        private final StructBufferIO<T> origin;
        private final int size;
        private final Map<String, BindingMeta<?>> metaMap = new LinkedHashMap<>();
        private int offset = -1;

        public Builder(StructBufferIO<T> origin, int size) {
            this.origin = origin;
            this.size = size;
        }

        public Builder<T> withField(String name, int offset) {
            var meta = origin.getBindingMeta(name);
            if (offset <= this.offset) {
                throw new BufferIOException(String.format("Invalid data offset of field %s within type %s: current=%d, new=%d",
                        name, origin.defaultConstructor.getDeclaringClass(), this.offset, offset));
            }
            metaMap.put(name, new BindingMeta<>(offset, meta.propertyIO, meta.bufferIO));
            return this;
        }

        public StructBufferIO<T> build() {
            return new StructBufferIO<>(origin.defaultConstructor, metaMap, size);
        }
    }

    private record PropertyIO(PropertyDescriptor propertyDescriptor) {

        Object getProperty(Object bean) {
            try {
                return propertyDescriptor.getReadMethod().invoke(bean);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new BufferIOException("Error get property of " + propertyDescriptor.getName() + " on bean " + bean.getClass(), e);
            }
        }

        void setProperty(Object bean, Object value) {
            try {
                propertyDescriptor.getWriteMethod().invoke(bean, value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new BufferIOException("Error set property of " + propertyDescriptor.getName() + " on bean " + bean.getClass(), e);
            }
        }
    }

    private record BindingMeta<T>(int offset, PropertyIO propertyIO, BufferIO<T> bufferIO) implements FieldBufferIO<T> {
        @Override
        public int size() {
            return bufferIO.size();
        }

        @Override
        public void write(@Nonnull T object, ByteBuffer buffer) {
            bufferIO.write(object, buffer);
        }

        @Override
        public T read(@Nullable T object, ByteBuffer buffer, @Nullable Consumer<T> setter) {
            return bufferIO.read(object, buffer, setter);
        }

        @Override
        public String getName() {
            return propertyIO.propertyDescriptor.getName();
        }

        @Override
        public int getOffset() {
            return offset;
        }
    }
}
