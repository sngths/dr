package com.tianxing.dr.framework.trace.tracing;

import io.opentracing.Scope;
import io.opentracing.ScopeManager;
import io.opentracing.Span;

/**
 * @author tianxing
 */
public class MDCScopeManager implements ScopeManager {

    @Override
    public Scope activate(Span span) {
        return null;
    }

    @Override
    public Span activeSpan() {
        return null;
    }
}
