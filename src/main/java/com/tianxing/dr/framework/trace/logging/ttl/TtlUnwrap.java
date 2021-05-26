package com.tianxing.dr.framework.trace.logging.ttl;


import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlWrapper;

public class TtlUnwrap {

    @SuppressWarnings("unchecked")
    public static <T> T unwrap(T obj) {
        if (!isWrapper(obj)) return obj;
        else return ((TtlWrapper<T>) obj).unwrap();
    }

    public static <T> boolean isWrapper(T obj) {
        return obj instanceof TtlWrapper;
    }

    private TtlUnwrap() {
        throw new InstantiationError("Must not instantiate this class");
    }
}
