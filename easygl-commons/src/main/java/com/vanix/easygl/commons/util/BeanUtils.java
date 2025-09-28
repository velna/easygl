package com.vanix.easygl.commons.util;

import com.vanix.easygl.commons.Chain;
import com.vanix.easygl.commons.Chained;
import org.apache.commons.beanutils2.PropertyUtils;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BeanUtils {
    public static <P> Map<String, Consumer<P>> findSettersOfType(Object bean, Class<P> propertyType) {
        Map<String, Consumer<P>> map = new HashMap<>();
        forEachSettersOfType(bean, propertyType, map::put);
        return map;
    }

    public static <P> void forEachSettersOfType(Object bean, Class<P> propertyType, BiConsumer<String, Consumer<P>> consumer) {
        try {
            findSettersOfType("", bean.getClass(), () -> bean, propertyType, consumer::accept);
        } catch (Exception e) {
            throw new IllegalStateException("Error bind resource of: " + bean.getClass(), e);
        }
    }

    private static <P> void findSettersOfType(String parentName,
                                              Class<?> beanType,
                                              ThrowingSupplier<Object, Exception> beanSupplier,
                                              Class<P> propertyType,
                                              ThrowingBiConsumer<String, Consumer<P>, Exception> consumer) throws Exception {
        var cacheSupplier = new CacheSupplier<>(beanSupplier);
        var descriptors = PropertyUtils.getPropertyDescriptors(beanType);
        for (var desc : descriptors) {
            if (desc.getWriteMethod() != null && desc.getReadMethod() != null && isChainType(desc.getWriteMethod().getGenericReturnType(), propertyType)) {
                consumer.accept(parentName + desc.getName(), param -> {
                    try {
                        var bean = cacheSupplier.get();
                        if (bean == null) {
                            return;
                        }
                        if (Chain.class.isAssignableFrom(desc.getPropertyType())) {
                            Chain<Object, P> chain = (Chain<Object, P>) desc.getReadMethod().invoke(bean);
                            if (chain == null) {
                                chain = (Chain) desc.getPropertyType().getConstructor(Object.class).newInstance(bean);
                                desc.getWriteMethod().invoke(bean, chain);
                            }
                            chain.initChain(param);
                        } else {
                            desc.getWriteMethod().invoke(bean, param);
                        }
                    } catch (Exception e) {
                        throw new IllegalStateException("Error bind resource of: " + desc.getName(), e);
                    }
                });
            } else {
                findSettersOfType(parentName + desc.getName() + ".", desc.getPropertyType(), () -> {
                            var bean = cacheSupplier.get();
                            if (bean == null) {
                                return null;
                            }
                            var value = desc.getReadMethod().invoke(bean);
                            if (value == null) {
                                value = checkChainedType(desc.getPropertyType(), desc.getWriteMethod().getGenericReturnType(), bean, desc.getWriteMethod()::invoke);
                            }
                            return value;
                        },
                        propertyType,
                        consumer);
            }
        }
        var fields = beanType.getFields();
        for (var field : fields) {
            if (isChainType(field.getGenericType(), propertyType)) {
                if (!Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
                    consumer.accept(parentName + field.getName(), param -> {
                        try {
                            var bean = cacheSupplier.get();
                            if (bean == null) {
                                return;
                            }
                            if (Chain.class.isAssignableFrom(field.getType())) {
                                Chain<Object, P> chain = (Chain<Object, P>) field.get(bean);
                                if (chain == null) {
                                    chain = (Chain) field.getType().getConstructor(Object.class).newInstance(bean);
                                    field.set(bean, chain);
                                }
                                chain.initChain(param);
                            } else {
                                field.set(bean, param);
                            }
                        } catch (Exception e) {
                            throw new IllegalStateException("Error bind resource of: " + field.getName(), e);
                        }
                    });
                }
            } else {
                findSettersOfType(parentName + field.getName() + ".", field.getType(), () -> {
                    var bean = cacheSupplier.get();
                    if (bean == null) {
                        return null;
                    }
                    var value = field.get(bean);
                    if (value == null) {
                        value = checkChainedType(field.getType(), field.getGenericType(), bean, field::set);
                    }
                    return value;
                }, propertyType, consumer);
            }
        }
    }

    private static Object checkChainedType(Class<?> type, Type genericType, Object bean, ThrowingBiConsumer<Object, Object, Exception> consumer) throws Exception {
        Object value = null;
        if (Chained.class.isAssignableFrom(type)) {
            var ownerType = TypeUtils.getTypeArguments(genericType, Chained.class).get(Chained.class.getTypeParameters()[0]);
            value = findConstructorOfParamType(type, TypeUtils.getRawType(ownerType, null)).newInstance(bean);
            consumer.accept(bean, value);
        }
        return value;
    }

    private static Constructor<?> findConstructorOfParamType(Class<?> type, Class<?> paramType) throws NoSuchMethodException {
        for (var constructor : type.getConstructors()) {
            if (constructor.getParameterCount() == 1 && constructor.getParameterTypes()[0].isAssignableFrom(paramType)) {
                return constructor;
            }
        }
        throw new NoSuchMethodException("Can not find method of param type " + paramType + " in class " + type);
    }

    private static boolean isChainType(Type type, Class<?> dataType) {
        if (type instanceof Class<?> classType) {
            return classType.equals(dataType);
        } else if (type instanceof ParameterizedType parameterizedType && parameterizedType.getRawType() instanceof Class<?> rawType) {
            return rawType.equals(dataType)
                    || (Chain.class.isAssignableFrom(rawType) && TypeUtils.getTypeArguments(type, Chain.class).get(Chain.class.getTypeParameters()[1]).equals(dataType));
        } else {
            return false;
        }
    }

    private static class CacheSupplier<T, E extends Exception> implements ThrowingSupplier<T, E> {
        private final ThrowingSupplier<T, E> supplier;
        private T bean;

        public CacheSupplier(ThrowingSupplier<T, E> supplier) {
            this.supplier = supplier;
        }

        @Override
        public T get() throws E {
            if (bean == null) {
                bean = supplier.get();
            }
            return bean;
        }
    }
}
