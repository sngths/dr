package com.tianxing.dr.framework.trace.tracing;

import io.opentracing.SpanContext;

import java.util.Map;

/**
 * @author tianxing
 */
public class DrSpanContext implements SpanContext {

    private String traceId;
    private String spanId;
    private Map<String, String> baggage;

    @Override
    public String toTraceId() {
        return null;
    }

    @Override
    public String toSpanId() {
        return null;
    }

    @Override
    public Iterable<Map.Entry<String, String>> baggageItems() {
        return null;
    }
}
