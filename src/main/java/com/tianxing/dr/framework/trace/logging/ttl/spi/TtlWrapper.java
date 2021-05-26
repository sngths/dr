package com.tianxing.dr.framework.trace.logging.ttl.spi;

public interface TtlWrapper<T> extends TtlEnhanced {

    T unwrap();
}
