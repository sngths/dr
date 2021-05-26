package com.tianxing.dr.framework.util;

/**
 * @author tianxing
 */
@FunctionalInterface
public interface Function<T,R> {
    R call(T t);
}
