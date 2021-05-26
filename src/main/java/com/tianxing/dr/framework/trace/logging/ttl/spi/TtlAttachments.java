package com.tianxing.dr.framework.trace.logging.ttl.spi;

public interface TtlAttachments extends TtlEnhanced {

    void setTtlAttachment(String key, Object value);

    <T> T getTtlAttachment(String key);

    String KEY_IS_AUTO_WRAPPER = "ttl.is.auto.wrapper";
}
