package com.tianxing.dr.framework.trace.tracing;


import io.opentracing.Scope;
import io.opentracing.ScopeManager;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;


/**
 * @author tianxing
 */
public class DrTracer implements Tracer {

    private ScopeManager scopeManager;


    @Override
    public ScopeManager scopeManager() {
        return scopeManager;
    }

    @Override
    public Span activeSpan() {
        return null;
    }

    @Override
    public Scope activateSpan(Span span) {
        return null;
    }

    @Override
    public SpanBuilder buildSpan(String operationName) {
        return null;
    }

    @Override
    public <C> void inject(SpanContext spanContext, Format<C> format, C carrier) {

    }

    @Override
    public <C> SpanContext extract(Format<C> format, C carrier) {
        return null;
    }

    @Override
    public void close() {

    }
}
