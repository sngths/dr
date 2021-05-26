package com.tianxing.dr.framework.trace.tracing;

/**
 * @author tianxing
 */
public class TraceId {
    private String serviceName = "JWMS";
    /**
     * 生成条件 HTTP请求  Job捞取
     * */
    private String traceId = "";//包含本机IP
    private String spanId = "";//包含时间戳

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }
}
