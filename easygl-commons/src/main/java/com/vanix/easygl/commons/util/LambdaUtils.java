package com.vanix.easygl.commons.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleProxies;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Executable;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LambdaUtils {
    private static final Map<Serializable, String> SERIALIZABLE_FUNCTION_CACHE = new ConcurrentHashMap<>();

    public static <T> String resolvePropertyName(SerializableFunction<T, ?> function) {
        return SERIALIZABLE_FUNCTION_CACHE.computeIfAbsent(function, LambdaUtils::doResolveMethodName);
    }

    public static <T> String resolvePropertyName(SerializableBiConsumer<T, ?> biConsumer) {
        return SERIALIZABLE_FUNCTION_CACHE.computeIfAbsent(biConsumer, LambdaUtils::doResolveMethodName);
    }

    private static <T> String doResolveMethodName(Serializable function) {
        String methodName;
        if (function instanceof Proxy proxy) {
            MethodHandle methodHandle = MethodHandleProxies.wrapperInstanceTarget(function);
            Executable executable = MethodHandles.reflectAs(Executable.class, methodHandle);
            methodName = executable.getName();
        } else {
            try {
                methodName = ((SerializedLambda) MethodUtils.invokeMethod(function, true, "writeReplace")).getImplMethodName();
            } catch (Exception e) {
                methodName = extract(function).getImplMethodName();
            }
        }
        String propertyName;
        if (methodName.startsWith("get") || methodName.startsWith("set")) {
            propertyName = methodName.substring(3);
        } else if (methodName.startsWith("is")) {
            propertyName = methodName.substring(2);
        } else {
            propertyName = methodName;
        }
        return StringUtils.uncapitalize(propertyName);
    }

    public static SerializedLambda extract(Serializable serializable) {
        try (var out = new ByteArrayOutputStream();
             var oos = new ObjectOutputStream(out)) {
            oos.writeObject(serializable);
            oos.flush();
            try (var ois = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray())) {
                @Override
                protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
                    var clazz = super.resolveClass(desc);
                    return clazz == java.lang.invoke.SerializedLambda.class ? SerializedLambda.class : clazz;
                }
            }) {
                return (SerializedLambda) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new UnsupportedOperationException();
        }
    }
}
