package com.tianxing.dr.framework.trace.tracing;

import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.tag.Tag;

import java.util.Map;

/**
 * @author tianxing
 */
public class DrSpan implements Span {

    @Override
    public SpanContext context() {
        return null;
    }

    @Override
    public Span setTag(String key, String value) {
        return null;
    }

    @Override
    public Span setTag(String key, boolean value) {
        return null;
    }

    @Override
    public Span setTag(String key, Number value) {
        return null;
    }

    @Override
    public <T> Span setTag(Tag<T> tag, T value) {
        return null;
    }

    @Override
    public Span log(Map<String, ?> fields) {
        return null;
    }

    @Override
    public Span log(long timestampMicroseconds, Map<String, ?> fields) {
        return null;
    }

    @Override
    public Span log(String event) {
        return null;
    }

    @Override
    public Span log(long timestampMicroseconds, String event) {
        return null;
    }

    @Override
    public Span setBaggageItem(String key, String value) {
        return null;
    }

    @Override
    public String getBaggageItem(String key) {
        return null;
    }

    @Override
    public Span setOperationName(String operationName) {
        return null;
    }

    @Override
    public void finish() {

    }

    @Override
    public void finish(long finishMicros) {

    }
}
