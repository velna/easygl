package com.vanix.easygl.commons;

public interface Chain<O, T> extends Chained<O> {
    void initChain(T data);
}
