package com.tianxing.dr.framework.trace.tracing.http;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 对HTTP请求添加链路追踪
 * @author tianxing
 */

public class HttpLogListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        //移除链路信息
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //添加链路信息
    }
}
