package com.vanix.easygl.commons;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class IntEnumCache {
    static final Map<Class<?>, Object> valuesMap = new ConcurrentHashMap<>();
}
