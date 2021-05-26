package com.tianxing.dr.framework.trace.logging;

import org.slf4j.MDC;

/**
 * @author tianxing
 */
public class LogInfo {
    private static final String APP_NAME = "appName";
    private static final String TRACE_ID = "traceId_";//加下划线避免和现有框架冲突
    private static final String SPAN_ID = "spanId_";//加下划线避免和现有框架冲突
    private static final String EXPORTABLE = "exportable";

    public static void setAppName(String appName) {
        MDC.put(APP_NAME, appName);
    }

    public static String getAppName() {
        return MDC.get(APP_NAME);
    }

    public static void setTraceId(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    public static void setSpanId(String spanId) {
        MDC.put(SPAN_ID, spanId);
    }

    public static String getSpanId() {
        return MDC.get(SPAN_ID);
    }


    public static void setExportable(boolean exportable) {
        MDC.put(EXPORTABLE, exportable ? "true" : "false");
    }

    public static Boolean isExportable() {
        return MDC.get(EXPORTABLE) != null && MDC.get(EXPORTABLE).equals("true");
    }
}
