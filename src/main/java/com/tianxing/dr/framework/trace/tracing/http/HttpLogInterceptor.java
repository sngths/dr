package com.tianxing.dr.framework.trace.tracing.http;

import com.tianxing.dr.framework.trace.logging.LogInfo;
import com.tianxing.dr.framework.trace.runtime.Runtime;
import com.tianxing.dr.framework.trace.runtime.context.Type;
import com.tianxing.dr.framework.util.UID;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @author tianxing
 */
@Component
public class HttpLogInterceptor implements WebRequestInterceptor {

    @Override
    public void preHandle(WebRequest request) {
        String traceId = UID.longBase62();
        LogInfo.setAppName("jwms");
        LogInfo.setTraceId(traceId);
        LogInfo.setSpanId(traceId);//rootSpan  spanId = traceId
        LogInfo.setExportable(false);
        Runtime.initialize(Type.http);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) {
        Runtime.clear();
    }
}
