package com.tianxing.dr.framework.trace.logging.ttl.spi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class TtlAttachmentsDelegate implements TtlAttachments {
    private final ConcurrentMap<String, Object> attachments = new ConcurrentHashMap<>();

    @Override
    public void setTtlAttachment(String key, Object value) {
        attachments.put(key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getTtlAttachment(String key) {
        return (T) attachments.get(key);
    }
}
