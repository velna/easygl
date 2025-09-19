package com.vanix.easygl.commons.bufferio;

import com.vanix.easygl.commons.util.TypeReference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public interface BufferIO<T> {
    /**
     * Get size in bytes of type T.
     *
     * @return size in bytes of type T
     */
    int size();

    /**
     * Write object into buffer's current position, position of buffer must be set properly.
     *
     * @param object object to write
     * @param buffer target buffer
     */
    void write(@Nonnull T object, ByteBuffer buffer);

    /**
     * Read object from current position of buffer, position of buffer must be set properly.
     *
     * @param object target object, maybe null
     * @param buffer source buffer
     * @param setter if object param is null, call setter to inject
     * @return result object
     */
    T read(@Nullable T object, ByteBuffer buffer, @Nullable Consumer<T> setter);

    static <T> BufferIO<T> ofBean(T object) {
        return BufferIORegistry.get(object);
    }

    static <T> BufferIO<T> ofType(Class<T> type, int... lengths) {
        return BufferIORegistry.getByType(type, lengths);
    }

    static <T> BufferIO<T> ofType(TypeReference<T> typeReference, int... lengths) {
        return BufferIORegistry.getByType(typeReference, lengths);
    }
}
