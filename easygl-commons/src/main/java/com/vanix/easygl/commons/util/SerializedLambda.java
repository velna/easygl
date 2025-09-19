package com.vanix.easygl.commons.util;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SerializedLambda implements Serializable {
    /**
     * The capturing class.
     */
    private final Class<?> capturingClass;
    /**
     * The functional interface class.
     */
    private final String functionalInterfaceClass;
    /**
     * The functional interface method name.
     */
    private final String functionalInterfaceMethodName;
    /**
     * The functional interface method signature.
     */
    private final String functionalInterfaceMethodSignature;
    /**
     * The implementation class.
     */
    private final String implClass;
    /**
     * The implementation method name.
     */
    private final String implMethodName;
    /**
     * The implementation method signature.
     */
    private final String implMethodSignature;
    /**
     * The implementation method kind.
     */
    private final int implMethodKind;
    /**
     * The instantiated method type.
     */
    private final String instantiatedMethodType;
    /**
     * The captured arguments.
     */
    private final Object[] capturedArgs;

    public SerializedLambda(Class<?> capturingClass,
                            String functionalInterfaceClass,
                            String functionalInterfaceMethodName,
                            String functionalInterfaceMethodSignature,
                            String implClass, String implMethodName,
                            String implMethodSignature,
                            int implMethodKind,
                            String instantiatedMethodType,
                            Object[] capturedArgs) {
        this.capturingClass = capturingClass;
        this.functionalInterfaceClass = functionalInterfaceClass;
        this.functionalInterfaceMethodName = functionalInterfaceMethodName;
        this.functionalInterfaceMethodSignature = functionalInterfaceMethodSignature;
        this.implClass = implClass;
        this.implMethodName = implMethodName;
        this.implMethodSignature = implMethodSignature;
        this.implMethodKind = implMethodKind;
        this.instantiatedMethodType = instantiatedMethodType;
        this.capturedArgs = capturedArgs;
    }

}
