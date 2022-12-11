package com.tianxing.dr.framework.utils;

/**
 * @author tianxing
 */
@FunctionalInterface
public interface Function<T,R> {
    R call(T t);
}
