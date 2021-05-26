package com.tianxing.dr.framework.trace.tracing;

/**
 * 用于跨服务器传输trace信息
 * @author tianxing
 */
public class TraceContext {

    private String traceId;
    private String parentSpanId;//rootSpan 不存在parentSpanID
    private String SpanId;//rootSpan：spanId = traceId
    private boolean sampled;//当前Span是否需要采样

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public String getSpanId() {
        return SpanId;
    }

    public void setSpanId(String spanId) {
        SpanId = spanId;
    }

    public boolean isSampled() {
        return sampled;
    }

    public void setSampled(boolean sampled) {
        this.sampled = sampled;
    }
}
