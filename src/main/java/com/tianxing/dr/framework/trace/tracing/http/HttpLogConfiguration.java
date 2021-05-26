package com.tianxing.dr.framework.trace.tracing.http;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author tianxing
 */
public class HttpLogConfiguration extends WebMvcConfigurerAdapter {


    private HttpLogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addWebRequestInterceptor(logInterceptor);
    }
}
