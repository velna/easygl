package com.vanix.easygl.core.meta;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractMetaService implements MetaService {
    private final Map<Class<?>, Object> registry = new HashMap<>();

    protected void register(Class<?> type, Object meta) {
        if (registry.put(type, meta) != null) {
            throw new IllegalArgumentException(type.getName());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <T> T of(Class<?> key, Object... args) {
        T ret = switch (registry.get(key)) {
            case Function function -> (T) function.apply(args);
            case Object obj -> (T) obj;
            case null -> throw new IllegalArgumentException("Unknown type: " + key.getName());
        };
        var error = getError();
        if (error != null && error.isError()) {
            throw new IllegalStateException(String.format("Error get meta of key(%s[%s]): %s(%d)",
                    key, StringUtils.join(args), error.description(), error.code()));
        }
        return ret;
    }

    protected Optional<Integer> queryStaticIntField(Class<?> type, String fieldPrefix, String id) {
        try {
            return Optional.of(type.getField(fieldPrefix + id).getInt(null));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return Optional.empty();
        }
    }
}
