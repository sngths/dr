package com.tianxing.dr.framework.trace.logging.ttl;

@FunctionalInterface
public interface TtlCopier<T> {

    T copy(T parentValue);
}
